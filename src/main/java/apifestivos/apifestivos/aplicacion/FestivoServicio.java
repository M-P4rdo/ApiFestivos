package apifestivos.apifestivos.aplicacion;

import apifestivos.apifestivos.core.interfaces.repositorios.IFestivoRepositorio;
import apifestivos.apifestivos.core.interfaces.servicios.IFestivoServicio;
import apifestivos.apifestivos.dominio.Festivo;
import apifestivos.apifestivos.dominio.Tipo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FestivoServicio implements IFestivoServicio{

    private final IFestivoRepositorio festivoRepositorio;

    public FestivoServicio(IFestivoRepositorio festivoRepositorio) {
        this.festivoRepositorio = festivoRepositorio;
    }

    public String validarFechaEsFestivo(int dia, int mes, int año) {

        // Validar mes
        if (mes < 1 || mes > 12) {
            return "Fecha incorrecta: mes invalido";
        } else if (dia < 1 || dia > diasEnMes(mes, año)) {
            return "Fecha incorrecta: dia invalido para el mes";
        } else {
            Optional<Festivo> festivo = festivoRepositorio.findByDiaAndMes(dia, mes);
            if (festivo.isPresent()) {
                Festivo festivoEncontrado = festivo.get();
                LocalDate fechaReal = calcularFechaFestivo(festivoEncontrado, año);
                
                return "Es Festivo: " + festivoEncontrado.getNombre()+ " " + fechaReal;
                
            }
            return "No es Festivo ";
        }
    }

    public int diasEnMes(int mes, int año) {
        if (mes == 2) {
            return esBisiesto(año) ? 29 : 28; // Febrero
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return 30; // Meses con 30 dias
        } else {
            return 31; // Meses con 31 dias
        }
    }

    public boolean esBisiesto(int año) {
        return (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
    }

    public LocalDate calcularFechaFestivo(Festivo festivo, int año) {
        Tipo tipo = festivo.getTipo();
        int dia = festivo.getDia();
        int mes = festivo.getMes();
        int numTipo = tipo.getId();

        switch (numTipo) {
            case 1: // Fijo
                return LocalDate.of(año, mes, dia);

            case 2: // Ley de "Puente festivo"
                LocalDate fechaOriginal = LocalDate.of(año, mes, dia);
                return fechaOriginal.getDayOfWeek().getValue() == 7 ? fechaOriginal : fechaOriginal.plusDays(8 - fechaOriginal.getDayOfWeek().getValue());

            case 3: // Basado en el domingo de pascua
                return calcularFechaDesdePascua(año, festivo.getDiasPascua());

            case 4: // Basado en el domingo de pascua y Ley de "Puente festivo"
                LocalDate fechaCalculada = calcularFechaDesdePascua(año, festivo.getDiasPascua());
                return fechaCalculada.getDayOfWeek().getValue() == 7 ? fechaCalculada : fechaCalculada.plusDays(8 - fechaCalculada.getDayOfWeek().getValue());

            default:
                throw new IllegalArgumentException("Tipo de festivo no reconocido");
        }
    }

    public LocalDate calcularFechaDesdePascua(int año, int diasDesdePascua) {
        LocalDate pascua = calcularFechaPascua(año);
        return pascua.plusDays(diasDesdePascua);
    }

    public LocalDate calcularFechaPascua(int año) {
        int a = año % 19;
        int b = año / 100;
        int c = año % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int mes = (h + l - 7 * m + 114) / 31;
        int dia = ((h + l - 7 * m + 114) % 31) + 1;

        return LocalDate.of(año, mes, dia);
    }
 
//---------------------------------------------------------------------------------------------------

    public List<Festivo> listarFestivos(){
        return festivoRepositorio.findAll();
    }

}