package org.stolen.example.repositorio;

import org.stolen.example.models.Pedido;
import org.stolen.example.models.Usuario;
import org.stolen.example.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPedidoImpl implements RepositorioPedido<Pedido> {

    @Override
    public List<Pedido> listaPedido() {
        List<Pedido> pedidoList = new ArrayList<>();

        String sqlList = "select  p.*, u.nombre as usuario from pedidos as p inner join usuarios as u on (p.usuario_id = u.id) order by p.id asc";
        try (Connection cn = ConexionBD.getConnection();
             Statement statement = cn.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlList)) {

            while (resultSet.next()) {
                Pedido pedido = getPedido(resultSet);
                pedidoList.add(pedido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidoList;
    }

    @Override
    public List<Pedido> listPedidoUser(int id) {
        List<Pedido> pedidoList = new ArrayList<>();
        String listUser = "select  p.*, u.nombre as usuario from pedidos as p inner join usuarios as u on (p.usuario_id = u.id) where u.id = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement(listUser)) {


            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Pedido p = getPedido(resultSet);
                    pedidoList.add(p);
                }
                if (pedidoList.isEmpty()) {
                    throw new IllegalArgumentException("El usuario no tiene pedidos");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidoList;
    }

    @Override
    public Pedido porIdPedido(int id) {
        Pedido pedido = null;
        String buscar = "select  p.*, u.nombre as usuario from pedidos as p inner join usuarios as u on (p.usuario_id = u.id) where p.id = ?";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement(buscar)) {
            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return getPedido(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedido;
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        String sqlCrear = "insert into pedidos (descripcion,total,usuario_id) values (?,?,?)";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement preparedStatement = cn.prepareStatement(sqlCrear, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pedido.getDescripcion());
            preparedStatement.setDouble(2, pedido.getTotal());
            preparedStatement.setInt(3, pedido.getUsuario().getId());

            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int idGenerado = resultSet.getInt(1);
                    pedido.setId(idGenerado);
                    return porIdPedido(idGenerado);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedido;
    }

    @Override
    public void actualizar(Pedido pedido) {
        String sqlAc = "update pedidos set descripcion = ?, total = ?, usuario_id = ? where id = ?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlAc)) {
            preparedStatement.setString(1, pedido.getDescripcion());
            preparedStatement.setDouble(2, pedido.getTotal());
            preparedStatement.setInt(3, pedido.getUsuario().getId());
            preparedStatement.setInt(4, pedido.getId());

            int filas = preparedStatement.executeUpdate();
            if (filas == 0) {
                throw new RuntimeException("No se encontro pedido para actualizar");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void eliminar(int id) {
        String delete = "delete from pedidos where id = ?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setInt(1, id);

            int filas = preparedStatement.executeUpdate();
            if (filas == 0) {
                throw new RuntimeException("No se encontró el pedido para eliminar");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /*--------------------------------------------------------------------*/
    private Pedido getPedido(ResultSet resultSet) throws SQLException {

        Usuario usuario = Usuario.builder()
                .id(resultSet.getInt("id"))
                .nombre(resultSet.getString("usuario"))
                .build();

        return Pedido.builder()
                .id(resultSet.getInt("id"))
                .descripcion(resultSet.getString("descripcion"))
                .total(resultSet.getDouble("total"))
                .usuario(usuario)
                .build();
    }
}
