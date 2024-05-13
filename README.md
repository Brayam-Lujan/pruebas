# API para tenis
La api posee endpints que ayudan a administrar los productos, en este caso los llamados "Tenis", Clientes y Ventas.

## Base de datos 

Se utiliza una base de datos (En este caso MySQL donde esta la dependencia importada aunque se puede cambiar a tu preferencia), Configurar y asegurar las crecenciales en el archivo `application.properties`.

Ejemplo donde sustituir la informacion en los espacios vacios:

### Propiedades

- spring.datasource.url=jdbc:________:mem:testdb
- spring.datasource.driverClassName=org._____________.Driver
- spring.datasource.username=usuario
- spring.datasource.password=contraseña
- spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Modelos (Cliente, Teni, Venta)

## Atributos Cliente

- `Long` (`id`)
- Identificador unico llamado id
- `String` (`nombre`)
- Nombre del cliente
- `String` (`apellido`)
- apellidos del cliente 
- `String` (`direccion`)
- direccion/hubicación del cliente
- `String` (`correo`)
- correo del cliente
- `String`(`numero`)
- numero del cliente 
- `String` (`infoPago`)
- Informacion del metodo de pago (tarjeta,efectivo, etc)

## Atributos Teni

- `Long` (`id`)
-  Identificador unico id
- `String` (`marca`)
- marca del ten
- `String` (`modelo`)
- modelo del ten 
- `double` (`precioCompra`)
- precio de compra del ten
- `Integer` (`precioVenta`)
- precio de venta del ten 
- `Boolean`(`Stock`)
- cantidad de tenis 
- `String` (`color`)
- Color de los tenis
- `String` (`descripcion`):
- brebe texto que decrive las caracteristicas del el ten

## Atributos Venta:

- `id` (`Long`)
- Identificador único de la venta.
- `clientId` (`Client`)
- Cliente asociado a la venta.
- `Teni` (Lista de `tenis`)
- tenis vendidos.
- `total` (`BigDecimal`)
- Total de la venta.
- `purchaseDate` (`LocalDateTime`)
- Fecha y hora de la compra.

# Controladores

## Base (`BaseController`)
El controlador base sirve para para definir una ruta para las relaciones en la apu atravez de la ruta:
- `/api/ventatenis`

## Clientes (`ClientController`)
Es el controlador que maneja las operaciones de cliente y su ruta es:
- `/Clientes`

## Métodos:

- **GET /clientes**
- Obtiene todos los clientes.
- **GET /clientes/{Id}**
- Obtiene un cliente por su ID.
- **POST /clientes**
- Crea un nuevo cliente.
- **PUT /clientes/{Id}**
- Actualiza un cliente existente por su ID.
- **DELETE /clientes/{Id}**
- Elimina un cliente por su ID.

## Productos (`TeniCOntroller`)

Es el controlador que maneja las operaciones de Teni y su ruta es:
- `/tenis`

## Métodos:

- **GET /tenis**
- Obtiene todos los productos o filtra por código si se proporciona.
- **GET /tenis/{id}**
- Obtiene un producto por su ID.
- **POST /tenis**
- Crea un nuevo producto.
- **PUT /tenis/{id}**
- Actualiza un producto existente por su ID.
- **DELETE /tenis/{id}**
- Elimina un producto por su ID.

## Ventas (`VentaController`)
Es el controlador que maneja las operaciones de Venta y su ruta es:
- `/ventas`

### Métodos:
- **POST /venta**
- Crea una nueva venta.
- **GET /venta/{id}**
- Obtiene una venta por su ID.
- **GET /venta**
- Obtiene todas las ventas.
- **PUT /venta/{id}**
- Actualiza una venta existente por su ID.
- **DELETE /venta/{id}**
- Elimina una venta por su ID.

