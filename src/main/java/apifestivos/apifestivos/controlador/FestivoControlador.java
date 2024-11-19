package apifestivos.apifestivos.controlador;

import apifestivos.apifestivos.core.interfaces.servicios.IFestivoServicio;
import apifestivos.apifestivos.dominio.Festivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

@Autowired
    private IFestivoServicio festivoServicio;

    public FestivoControlador(IFestivoServicio festivoServicio) {
        this.festivoServicio = festivoServicio;
    }

    @RequestMapping(value="/validar/{dia}/{mes}/{año}", method=RequestMethod.GET)
    public String validarFecha(@PathVariable int dia, @PathVariable int mes, @PathVariable int año) {
        return festivoServicio.validarFechaEsFestivo(dia, mes, año);
    }

    @RequestMapping(value="/pascua/{año}", method=RequestMethod.GET)
    public String verificarPascua(@PathVariable int año) {
        return festivoServicio.validarFechaPascua(año);
    }

    @RequestMapping(value="/listar", method=RequestMethod.GET)
    public List<Festivo> listarFestivos() {
        return festivoServicio.listarFestivos();
    }
}

