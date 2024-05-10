# API de Supermercado

## Descripción
Esta API proporciona endpoints para administrar productos, clientes y ventas de un supermercado.

# Ejecución del Proyecto

A continuación se detallan los pasos necesarios para ejecutar el proyecto localmente:

## 1. Clonar el Repositorio desde GitHub

Clone el repositorio desde GitHub a su máquina local utilizando el siguiente comando:

## 2. Configurar la Base de Datos

El proyecto utiliza una base de datos para almacenar la información. Asegúrese de tener una base de datos disponible y configure las credenciales de conexión en el archivo `application.properties`.

Ejemplo de configuración para una base de datos H2 en memoria:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

# Modelos

## Cliente (`Client`)

### Atributos:
- `clientId` (`Long`): Identificador único del cliente.
- `names` (`String`): Nombres del cliente.
- `email` (`String`): Correo electrónico del cliente.
- `phone` (`String`): Número de teléfono del cliente.
- `address` (`String`): Dirección del cliente.
- `password` (`String`): Contraseña del cliente.
- `id_card` (`Long`): Número de identificación del cliente.

## Producto (`Product`)

### Atributos:
- `id` (`Long`): Identificador único del producto.
- `codigo` (`String`): Código único del producto.
- `precio` (`Integer`): Precio del producto.
- `nombre` (`String`): Nombre del producto.
- `categoria` (`String`): Categoría del producto.
- `peso` (`String`): Peso del producto.
- `marca` (`String`): Marca del producto.
- `descripcion` (`String`): Descripción del producto.
- `imagen` (`String`): URL de la imagen del producto.

## Venta (`Sale`)

### Atributos:
- `id` (`Long`): Identificador único de la venta.
- `clientId` (`Client`): Cliente asociado a la venta.
- `products` (Lista de `Product`): Productos vendidos.
- `total` (`BigDecimal`): Total de la venta.
- `purchaseDate` (`LocalDateTime`): Fecha y hora de la compra.

# Controladores

## Controlador Base (`BaseController`)

Este es el controlador base que define la ruta base para todos los controladores relacionados con la tienda de comestibles.

- Ruta base: `/api/grocerystore`

## Controlador de Clientes (`ClientController`)

Este controlador maneja las operaciones relacionadas con los clientes.

- Ruta base: `/clients`

### Métodos:

- **GET /clients**: Obtiene todos los clientes.
- **GET /clients/{clientId}**: Obtiene un cliente por su ID.
- **POST /clients**: Crea un nuevo cliente.
- **PUT /clients/{clientId}**: Actualiza un cliente existente por su ID.
- **DELETE /clients/{clientId}**: Elimina un cliente por su ID.

## Controlador de Productos (`ProductController`)

Este controlador maneja las operaciones relacionadas con los productos.

- Ruta base: `/products`

### Métodos:

- **GET /products**: Obtiene todos los productos o filtra por código si se proporciona.
- **GET /products/{id}**: Obtiene un producto por su ID.
- **POST /products**: Crea un nuevo producto.
- **PUT /products/{id}**: Actualiza un producto existente por su ID.
- **DELETE /products/{id}**: Elimina un producto por su ID.

## Controlador de Ventas (`SaleController`)

Este controlador maneja las operaciones relacionadas con las ventas.

- Ruta base: `/sales`

### Métodos:

- **POST /sales**: Crea una nueva venta.
- **GET /sales/{id}**: Obtiene una venta por su ID.
- **GET /sales**: Obtiene todas las ventas.
- **PUT /sales/{id}**: Actualiza una venta existente por su ID.
- **DELETE /sales/{id}**: Elimina una venta por su ID.

---

Este es el final de la documentación de la API de la tienda de comestibles. Si necesitas más información o tienes alguna pregunta, no dudes en ponerte en contacto conmigo.

¡Gracias por utilizar mi API!

