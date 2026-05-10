# Java JDBC CRUD Application

Aplicación de consola desarrollada en Java utilizando JDBC y MySQL para la gestión de usuarios y pedidos.

## 🚀 Tecnologías utilizadas

* Java
* JDBC
* MySQL
* Git & GitHub

## 📌 Características

* Crear usuarios
* Listar usuarios
* Crear pedidos
* Listar pedidos
* Buscar pedidos por usuario
* Actualizar Pedido
* Eliminar Pedido
* Manejo de relaciones entre tablas
* Validaciones básicas
* Manejo de excepciones

## 🏗️ Arquitectura y patrones utilizados

El proyecto implementa una arquitectura por capas aplicando buenas prácticas de desarrollo backend.

### Patrones usados

* Repository Pattern
* Facade Pattern
* Builder Pattern

## 🗄️ Base de datos

El sistema utiliza MySQL con dos tablas relacionadas:

### Usuarios

* id
* nombre
* correo

### Pedidos

* id
* descripcion
* total
* usuario_id

Relación:

* Un usuario puede tener muchos pedidos (1:N)

## ⚙️ Funcionalidades JDBC utilizadas

* Connection
* PreparedStatement
* ResultSet
* JOIN entre tablas
* RETURN_GENERATED_KEYS
* Try-With-Resources

## 📂 Estructura del proyecto

```bash
src
 ├── app
 ├── facade
 ├── models
 ├── repositorio
 └── util
```

## ▶️ Cómo ejecutar el proyecto

1. Clonar repositorio

```bash
git clone https://github.com/TU-USUARIO/java-jdbc-crud.git
```

2. Configurar MySQL

Crear la base de datos y tablas correspondientes.

3. Configurar conexión JDBC

Modificar credenciales en:

```java
ConexionBD.java
```

4. Ejecutar aplicación

Correr:

```java
AppPrincipal.java
```

## 📖 Objetivo del proyecto

Este proyecto fue desarrollado como práctica para reforzar conceptos de:

* JDBC
* Arquitectura multicapa
* Persistencia de datos
* Relaciones SQL
* Patrones de diseño
* Backend con Java

## 👨‍💻 Autor

Jacqueline Flores
