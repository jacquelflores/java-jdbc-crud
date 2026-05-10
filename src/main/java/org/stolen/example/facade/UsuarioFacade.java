package org.stolen.example.facade;

import org.stolen.example.models.Usuario;
import org.stolen.example.repositorio.RepositorioUsuario;

import java.util.List;

public class UsuarioFacade {
    private RepositorioUsuario<Usuario> repositorioUsuario;

    public UsuarioFacade(RepositorioUsuario<Usuario> repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public List<Usuario> list() {
        return repositorioUsuario.listUsuario();

    }

    public Usuario insertar(Usuario usuario){
        return repositorioUsuario.insertarUsuario(usuario);
    }

}
