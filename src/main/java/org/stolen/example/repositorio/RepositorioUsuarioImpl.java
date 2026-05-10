package org.stolen.example.repositorio;

import org.stolen.example.models.Usuario;
import org.stolen.example.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarioImpl implements RepositorioUsuario<Usuario> {

    @Override
    public Usuario insertarUsuario(Usuario usuario) {
        String sql = "insert into usuarios(nombre,correo) values (?,?)";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getCorreo());

            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int idGeneradoUsuario = resultSet.getInt(1);
                    usuario.setId(idGeneradoUsuario);
                    return consultar(idGeneradoUsuario);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    @Override
    public List<Usuario> listUsuario() {
        List<Usuario> usuarios = new ArrayList<>();
        String list = "select * from usuarios as u order by u.id asc";
        try (Connection connection = ConexionBD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(list)) {
            while (resultSet.next()) {
                Usuario u = getUsuario(resultSet);
                usuarios.add(u);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    @Override
    public Usuario consultar(int id) {
        Usuario u = null;
        String cnlr = "select * from usuarios where id = ?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(cnlr)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    return getUsuario(resultSet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }

    private static Usuario getUsuario(ResultSet resultSet) throws SQLException {
        return Usuario.builder()
                .id(resultSet.getInt("id"))
                .nombre(resultSet.getString("nombre"))
                .correo(resultSet.getString("correo"))
                .build();
    }
}
