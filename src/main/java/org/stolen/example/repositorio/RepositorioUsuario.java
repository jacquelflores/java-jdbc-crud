package org.stolen.example.repositorio;

import org.stolen.example.models.Usuario;

import java.util.List;

public interface RepositorioUsuario<T> {

    Usuario insertarUsuario(T t);

    List<Usuario> listUsuario();

    Usuario consultar(int id);
}
