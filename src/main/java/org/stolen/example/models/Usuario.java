package org.stolen.example.models;

public class Usuario {
    private int id;
    private String nombre;
    private String correo;

    public Usuario(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.correo = builder.correo;
    }


    public static class Builder {
        private int id;
        private String nombre;
        private String correo;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder correo(String correo) {
            this.correo = correo;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getId() {
        return id;
    }

    public Usuario setId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return id +
                " | " +
                nombre +
                " | " +
                correo;
    }
}
