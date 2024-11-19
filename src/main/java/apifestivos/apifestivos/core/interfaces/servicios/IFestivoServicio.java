package apifestivos.apifestivos.core.interfaces.servicios;

import java.util.List;
import apifestivos.apifestivos.dominio.Festivo;

public interface IFestivoServicio {

    public List<Festivo> listarFestivos();

    public String validarFechaEsFestivo(int dia, int mes, int año);

    public String validarFechaPascua(int año);
}
