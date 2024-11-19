package apifestivos.apifestivos.aplicacion;

import apifestivos.apifestivos.core.interfaces.repositorios.IFestivoRepositorio;
import apifestivos.apifestivos.core.interfaces.servicios.IFestivoServicio;
import apifestivos.apifestivos.dominio.Festivo;

import org.springframework.stereotype.Service;

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
        }
        else if (dia < 1 || dia > diasEnMes(mes, año)) {
            return "Fecha incorrecta: dia invalido para el mes";
        }
        else {
            Optional<Festivo> festivo = festivoRepositorio.findByDiaAndMes(dia, mes);
            if (festivo.isPresent()) {
                return("Es Festivo ");
            } else {
                return("No es Festivo ");
            }
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
    
    private boolean esBisiesto(int año) {
        return (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0);
    }

    public List<Festivo> listarFestivos(){
        return festivoRepositorio.findAll();
    }

    public String validarFechaPascua(int año){
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
    
        return("Pascua es el " + dia + "/" + mes +"/" + año);
    }
}