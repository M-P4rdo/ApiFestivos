package apifestivos.apifestivos.aplicacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apifestivos.apifestivos.aplicacion.seguridad.SeguridadServicio;
import apifestivos.apifestivos.core.interfaces.repositorios.IUsuarioRepositorio;
import apifestivos.apifestivos.core.interfaces.servicios.IUsuarioServicio;
import apifestivos.apifestivos.dominio.Usuario;
import apifestivos.apifestivos.dominio.DTOs.UsuarioLoginDto;

@Service
public class UsuarioServicio implements IUsuarioServicio {

    private IUsuarioRepositorio repositorio;
    @Autowired
    private SeguridadServicio servicioSeguridad;

    public UsuarioServicio(IUsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public UsuarioLoginDto login(String nombreUsuario, String clave) {
        Usuario usuarioObtenido = repositorio.validarUsuario(nombreUsuario, clave);
        UsuarioLoginDto userLoginDto = new UsuarioLoginDto(usuarioObtenido);
        if (usuarioObtenido != null) {
            userLoginDto.setToken(servicioSeguridad.generarToken(nombreUsuario));
        }
        return userLoginDto;
    }

    @Override
    public List<Usuario> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public Usuario obtener(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtener'");
    }

    @Override
    public List<Usuario> buscar(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscar'");
    }

    @Override
    public Usuario agregar(Usuario Usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregar'");
    }

    @Override
    public Usuario modificar(Usuario Usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public boolean eliminar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

}
