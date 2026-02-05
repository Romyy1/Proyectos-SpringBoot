Este proyecto es una **aplicación de ticketing** desarrollada con **Spring Boot**, cuyo objetivo es gestionar tickets de compra y los productos asociados a cada ticket.

El proyecto está enfocado en **practicar y consolidar el desarrollo backend**, trabajando con entidades relacionadas, APIs REST y persistencia en base de datos.


## Descripción general

La aplicación permite:

- Crear, modificar o eliminar tickets de compra.
- Asociar múltiples productos a un ticket
- Indicar cantidades y precios por producto
- Calcular el total del ticket
- Consultar tickets y su detalle

A nivel de modelo, un ticket puede contener **varios productos**, y un mismo producto puede aparecer en distintos tickets, por lo que se utiliza una **tabla intermedia** para gestionar la relación.

---

Modelo de datos

El sistema se basa principalmente en las siguientes entidades:

**Ticket**
  - Fecha
  - Total
  - Lista de productos asociados

**Product**
  - Nombre
  - Precio

**TicketProduct**
  - Relación entre Ticket y Product
  - Cantidad

Esta entidad intermedia permite representar correctamente una relación **muchos a muchos con atributos**, algo habitual en sistemas reales de facturación o ventas.

---

## API REST

La aplicación expone una API REST desarrollada con Spring Boot, siguiendo una arquitectura en capas:

- Controller
- Service
- Repository
- Models/DTO

Los endpoints permiten crear y consultar tickets, enviando y recibiendo datos en formato JSON.

---

## Conexión a la base de datos

La persistencia se gestiona mediante **Spring Data JPA** y **Hibernate**, utilizando una base de datos relacional.

La configuración de esta se encuentra en el archivo **application.properties**, donde habrá que cambiar los siguientes valores:

spring.datasource.url=jdbc:mysql://localhost:3306/
spring.datasource.username=
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update

Hay que poner el nombre de la base de datos que se va a utilizar, username y password para la conexión y muy importante primero poner **create** en ddl-auto para crear la base de datos, y una vez ya se haya creado, cambiarlo a update para asegurarnos de que no se nos borra la base de datos que acabamos de crear.

El proyecto cuenta con un archivo **import.sql** el cual cuando se crea la base de datos, mete un listado de productos para tener con que probar la API al principio.

---

## Estado del proyecto

Este proyecto está en una primera versión con funcionalidad básica de la API por lo que solo cuenta con funcionalidad dentro de **Backend**, más adelante iré añadiendo:

- Autenticación y autorización con JWT

- Documentación con Swagger

- Frontend para interactuar de forma más sencilla con la API

- Control de roles, para que una persona solo vea sus propios tickets y que solo los usuarios con rol admin puedan modificar tickets.

---

## Ejemplo de uso

**Todos estos ejemplos se hacen en postman y una vez tengamos la base de datos ya creada, y todo en base a la carpeta "Backend" que es donde se encuentra la API**

### Crear ticket

Si queremos crear un ticket, tenemos que hacer una petición **POST** a **localhost:8080/ticket/create** poniendo lo que queramos con la siguiente estructura en el **body**:

{
  "ticketProduct": [
    { "product_id": { "id": 5 }, "quantity": 12 }
  ]
}

La API una vez se haga esta petición:

- Asignará un id al nuevo ticket
- Asignará un id a cada producto dentro de TicketProduct para reconocerlo
- Asociará el id del ticket que se acaba de crear con cada producto dentro de TicketProduct
- Obtendrá la fecha y hora en la que se ha creado el ticket
- Calculará el monto total del ticket

### Ver un ticket

En el caso de querer consultar el ticket que acabamos de crear, haremos una petición **GET** a **localhost:8080/ticket/{ID}**, donde {ID} será el id del ticket que acabamos de crear.

### Actualizar un ticket

Ahora veremos el ejemplo de como modificarlo. Para ello haremos una consulta **PUT** a **localhost:8080/ticket/update/{ID}**, y le pasaremos un **body** con la información que queramos cambiar. Pongamos que queremos cambiar la cantidad que habíamos puesto antes:

{
  "ticketProduct": [
    { "product_id": { "id": 5 }, "quantity": 8 }
  ]
}

Una vez ejecutemos, si volvemos a consultar el ticket, veremos que ha cambiado tanto el total como la cantidad.

### Eliminar un ticket

Par eliminar el ticket simplemente haremos una petición **DELETE** a **localhost:8080/ticket/delete/{ID}**

### Ver todos los tickets

En este caso, veremos todos los tickets que hay en la base de datos, haremos una petición **GET** a **localhost:8080/ticket/list**.


### Crear producto

Para crear un producto, haremos una petición **POST** a **localhost:8080/product/create**, la cual solo llevará en el **body** lo siguiente:

{
  "nombre" : "Pollo asado",
  "precio" : 8.5
}

### Ver todos los productos

Si queremos ver el listado de los productos haremos una petición **GET** a **localhost:8080/product/list**.

### Ver un solo producto

En este caso haremos una petición **GET** a **localhost:8080/product/{ID}**.


### Actualizar un producto

Para actualizar un producto, haremos una petición **PUT** a **localhost:8080/product/update/{ID}**, y en el body pondremos la información nueva que queramos:

{
  "nombre" : "Pollo asado",
  "precio" : 10.3
}

### Eliminar un producto

En caso de querer eliminar un producto, haremos una petición **DELETE** a **localhost:8080/product/delete/{ID}**.
