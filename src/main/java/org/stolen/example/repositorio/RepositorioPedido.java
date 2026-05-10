package org.stolen.example.repositorio;

import org.stolen.example.models.Pedido;

import java.util.List;

public interface RepositorioPedido<T> {

    List<T> listaPedido();

    List<Pedido> listPedidoUser(int id);

    Pedido crearPedido(Pedido pedido);

    Pedido porIdPedido(int id);

     void actualizar(T t);

     void eliminar(int id);

}
