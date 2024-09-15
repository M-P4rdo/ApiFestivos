package apifestivos.apifestivos.aplicacion;

import apifestivos.apifestivos.core.interfaces.repositorios.IFestivoRepositorio;
import apifestivos.apifestivos.dominio.Festivo;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FestivoServicio {

    private final IFestivoRepositorio festivoRepositorio;

    public FestivoServicio(IFestivoRepositorio festivoRepositorio) {
        this.festivoRepositorio = festivoRepositorio;
    }

    public String validarFechaEsFestivo(int dia, int mes) {
        Optional<Festivo> festivo = festivoRepositorio.findByDiaAndMes(dia, mes);
        return festivo.isPresent() ? "Es Festivo: " + festivo.get().getNombre() : "No es Festivo";
    }
}