package org.stolen.example.app;

import org.stolen.example.facade.PedidoFacade;
import org.stolen.example.facade.UsuarioFacade;
import org.stolen.example.models.Pedido;
import org.stolen.example.models.Usuario;
import org.stolen.example.repositorio.RepositorioPedido;
import org.stolen.example.repositorio.RepositorioPedidoImpl;
import org.stolen.example.repositorio.RepositorioUsuario;
import org.stolen.example.repositorio.RepositorioUsuarioImpl;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AppPrincipal {
    public static void main(String[] args) {


        RepositorioPedido<Pedido> repositorioPedido = new RepositorioPedidoImpl();
        RepositorioUsuario<Usuario> repositorioUsuario = new RepositorioUsuarioImpl();
        PedidoFacade pedidoFacade = new PedidoFacade(repositorioPedido);
        UsuarioFacade usuarioFacade = new UsuarioFacade(repositorioUsuario);

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            try {
                System.out.println(
                        "1. Crear usuario\n" +
                                "2. Listar usuarios\n" +
                                "3. Crear pedido\n" +
                                "4. Listar pedidos (con usuario)\n" +
                                "5. Listar Pedidos\n" +
                                "6. Actualizar Pedido\n" +
                                "7. Eliminar Pedido\n" +
                                "8. Salir"
                );
                opcion = scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {
                    case 1 -> {
                        System.out.println("Crear Usuario");

                        System.out.println("Nombre");
                        String nombre = scanner.nextLine();

                        System.out.println("Correo");
                        String correo = scanner.nextLine();

                        if (!correo.contains("@")) {
                            System.out.println("Correo invalido");
                            break;
                        }

                        if (nombre.isBlank() || correo.isBlank()) {
                            System.out.println("Campos vacios");
                            break;
                        }
                        Usuario usuario = Usuario.builder()
                                .nombre(nombre)
                                .correo(correo)
                                .build();

                        System.out.println(usuarioFacade.insertar(usuario));
                        System.out.println("--------------------------------");
                    }

                    case 2 -> {
                        System.out.println("Listado de Usuarios");
                        imprimirUsuario(usuarioFacade.list());
                        System.out.println("--------------------------------");
                    }
                    case 3 -> {
                        System.out.println("Crear Pedido");

                        System.out.println("Descripcion");
                        String descripcion = scanner.nextLine();

                        System.out.println("Total");
                        double total = scanner.nextDouble();

                        System.out.println("Usuario");
                        int id = scanner.nextInt();

                        scanner.nextLine();

                        if (descripcion.isBlank() || total <= 0) {
                            System.out.println("Campos invalidos");
                            break;
                        }

                        Usuario u = Usuario.builder()
                                .id(id)
                                .build();

                        Pedido p = Pedido.builder()
                                .descripcion(descripcion)
                                .total(total)
                                .usuario(u)
                                .build();
                        System.out.println(pedidoFacade.crearPedido(p));
                        System.out.println("--------------------------------");
                    }
                    case 4 -> {
                        System.out.println("Listar Pedido por un Usuario");
                        System.out.println("Codigo de Usuario");
                        int codigo = scanner.nextInt();
                        scanner.nextLine();
                        imprimir(pedidoFacade.listUsurioP(codigo));
                        System.out.println("--------------------------------");
                    }
                    case 5 -> {
                        System.out.println("Listar Pedidos");
                        imprimir(pedidoFacade.listPedido());
                        System.out.println("--------------------------------");
                    }
                    case 6 -> {
                        System.out.println("Actualizar Pedido");


                        System.out.println("Codigo");
                        int codigo = scanner.nextInt();

                        scanner.nextLine();

                        System.out.println("Descripcion");
                        String descripcion = scanner.nextLine();

                        System.out.println("Total");
                        double total = scanner.nextDouble();

                        System.out.println("Usuario");
                        int id = scanner.nextInt();

                        scanner.nextLine();

                        Usuario u = Usuario.builder()
                                .id(id)
                                .build();

                        Pedido p = Pedido.builder()
                                .id(codigo)
                                .descripcion(descripcion)
                                .total(total)
                                .usuario(u)
                                .build();
                        pedidoFacade.actualizarPedido(p);
                    }
                    case 7 -> {
                        System.out.println("Eliminar un Pedido");
                        System.out.println("Codigo de Pedido");
                        int cd = scanner.nextInt();
                        scanner.nextLine();
                        pedidoFacade.eliminarPedido(cd);
                    }
                    case 8 -> {
                        System.out.println("Saliendo.....");
                        System.out.println("--------------------------------");
                    }

                    default -> {
                        System.out.println("Opcion invalida");
                        System.out.println("--------------------------------");
                    }
                }
            } catch (InputMismatchException i) {
                System.out.println("Ingrese solo numeros validos");
                scanner.nextLine();
            }
        } while (opcion != 8);

    }

    public static void imprimir(List<Pedido> pedidoList) {
        pedidoList.forEach(System.out::println);
    }

    public static void imprimirUsuario(List<Usuario> usuarioList) {
        usuarioList.forEach(System.out::println);
    }
}
