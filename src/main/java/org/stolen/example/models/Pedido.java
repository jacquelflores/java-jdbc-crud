package org.stolen.example.models;

public class Pedido {
    private Integer id;
    private String descripcion;
    private double total;
    private Usuario usuario;


    public Pedido(Builder builder) {
        this.id = builder.id;
        this.descripcion = builder.descripcion;
        this.total = builder.total;
        this.usuario = builder.usuario;
    }

    public static class Builder {
        private Integer id;
        private String descripcion;
        private double total;
        private Usuario usuario;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder total(double total) {
            this.total = total;
            return this;
        }

        public Builder usuario(Usuario usuario) {
            this.usuario = usuario;
            return this;
        }

        public Pedido build() {
            return new Pedido(this);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public Pedido setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getTotal() {
        return total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return id +
                " | " +
                descripcion +
                " | " +
                total +
                " | " +
                usuario.getNombre();
    }
}
