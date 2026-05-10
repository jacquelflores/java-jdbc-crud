package org.stolen.example.facade;

import org.stolen.example.models.Pedido;
import org.stolen.example.repositorio.RepositorioPedido;

import java.util.List;

public class PedidoFacade {
    private RepositorioPedido<Pedido> repositorioPedido;

    public PedidoFacade(RepositorioPedido<Pedido> repositorioPedido) {
        this.repositorioPedido = repositorioPedido;
    }

    public List<Pedido> listPedido() {
        return repositorioPedido.listaPedido();
    }

    public List<Pedido> listUsurioP(int id) {
        return repositorioPedido.listPedidoUser(id);
    }

    public Pedido crearPedido(Pedido p) {
        return repositorioPedido.crearPedido(p);
    }

    public void actualizarPedido(Pedido pedido){
        repositorioPedido.actualizar(pedido);
    }

    public void eliminarPedido(int id){
        repositorioPedido.eliminar(id);
    }
}
