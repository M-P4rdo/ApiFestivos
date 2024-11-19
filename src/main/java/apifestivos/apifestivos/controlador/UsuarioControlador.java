package apifestivos.apifestivos.controlador;
//Autenticar al usuario y devolver un JWT

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apifestivos.apifestivos.core.interfaces.servicios.IUsuarioServicio;
import apifestivos.apifestivos.dominio.DTOs.UsuarioLoginDto;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

@Autowired
    private IUsuarioServicio servicio;

    @RequestMapping(value="/login/{nombreUsuario}/{clave}", method=RequestMethod.GET)    
    public UsuarioLoginDto login(@PathVariable String nombreUsuario, @PathVariable String clave) {
        return servicio.login(nombreUsuario, clave);
    }

}
