package apifestivos.apifestivos.controlador;

import apifestivos.apifestivos.aplicacion.FestivoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

@Autowired
    private final FestivoServicio festivoServicio;

    public FestivoControlador(FestivoServicio festivoServicio) {
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
}

