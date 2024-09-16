package apifestivos.apifestivos.controlador;

import apifestivos.apifestivos.aplicacion.FestivoServicio;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

    private final FestivoServicio festivoServicio;

    public FestivoControlador(FestivoServicio festivoServicio) {
        this.festivoServicio = festivoServicio;
    }

    @GetMapping("/validar")
    public String validarFecha(@RequestParam int dia, @RequestParam int mes) {
        return festivoServicio.validarFechaEsFestivo(dia, mes);
    }
}
