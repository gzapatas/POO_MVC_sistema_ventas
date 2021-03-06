/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  gzapata
 * Created: Jun 10, 2022
 */
DROP DATABASE IF EXISTS SistemaVentasDB;
CREATE DATABASE SistemaVentasDB;

USE SistemaVentasDB;

CREATE TABLE SistemaVentasDB.Usuarios (
  idUsuario INTEGER UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'Id de usuario',
  username CHAR(20) NOT NULL COMMENT 'Usuario',
  pass CHAR(50) NOT NULL COMMENT 'Contraseña',
  fecha DATE DEFAULT NULL COMMENT 'Fecha registro',
  fechaHora DATETIME DEFAULT NULL COMMENT 'Fecha Hora registro',
  timestamp BIGINT DEFAULT NULL COMMENT 'Epoch registro',
  PRIMARY KEY (idUsuario)
)
ENGINE = INNODB,
COMMENT='Tabla de usuarios';


CREATE TABLE SistemaVentasDB.Empleados (
  idEmpleado INTEGER UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'Id de empleado',
  nombres VARCHAR(50) NOT NULL COMMENT 'Nombres',
  apellidos VARCHAR(50) NOT NULL COMMENT 'Apellidos',
  telefono CHAR(15) NOT NULL COMMENT 'Telefono',
  celular CHAR(15) NOT NULL COMMENT 'Celular',
  documento CHAR(15) NOT NULL COMMENT 'Documento de identidad',
  tipoEmpleado CHAR(20) NOT NULL COMMENT 'Tipo empleado CAJERO/ADMINISTRADOR',
  idUsuario INTEGER UNSIGNED NOT NULL,
  fechaNacimiento DATE DEFAULT NULL COMMENT 'Fecha de nacimiento',
  fecha DATE DEFAULT NULL COMMENT 'Fecha registro',
  fechaHora DATETIME DEFAULT NULL COMMENT 'Fecha Hora registro',
  timestamp BIGINT DEFAULT NULL COMMENT 'Epoch registro',
  PRIMARY KEY (idEmpleado),
  FOREIGN KEY (idUsuario) REFERENCES SistemaVentasDB.Usuarios(idUsuario)
)
ENGINE = INNODB,
COMMENT='Tabla de empleados';

ALTER TABLE SistemaVentasDB.Empleados ADD INDEX idxDocumento (documento);
ALTER TABLE SistemaVentasDB.Empleados ADD INDEX idxFechaNacimiento (fechaNacimiento);
ALTER TABLE SistemaVentasDB.Empleados ADD INDEX idxFecha (fecha);
ALTER TABLE SistemaVentasDB.Empleados ADD INDEX idxFechaHora (fechaHora);
ALTER TABLE SistemaVentasDB.Empleados ADD INDEX idxTimestamp (timestamp);



CREATE TABLE SistemaVentasDB.Categorias (
  idCategoria INTEGER UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'ID de categoria',
  nombre VARCHAR(100) NOT NULL COMMENT 'Nombre de la categoria',
  fecha DATE DEFAULT NULL COMMENT 'Fecha registro',
  fechaHora DATETIME DEFAULT NULL COMMENT 'Fecha Hora registro',
  timestamp BIGINT DEFAULT NULL COMMENT 'Epoch registro',
  PRIMARY KEY (idCategoria)
)
ENGINE = INNODB,
COMMENT='Tabla de categorias de los productos';

ALTER TABLE SistemaVentasDB.Categorias ADD INDEX idxFecha (fecha);
ALTER TABLE SistemaVentasDB.Categorias ADD INDEX idxFechaHora (fechaHora);
ALTER TABLE SistemaVentasDB.Categorias ADD INDEX idxTimestamp (timestamp);



CREATE TABLE SistemaVentasDB.Productos (
  idProducto INTEGER UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'ID de orden',
  sku CHAR(15) NOT NULL COMMENT 'Identificador de producto global',
  nombre VARCHAR(100) NOT NULL COMMENT 'Nombre del producto',
  idCategoria INTEGER UNSIGNED NOT NULL COMMENT 'ID de categoria',
  descripcion TEXT NOT NULL COMMENT 'Descripcion del producto',
  fecha DATE DEFAULT NULL COMMENT 'Fecha registro',
  fechaHora DATETIME DEFAULT NULL COMMENT 'Fecha Hora registro',
  timestamp BIGINT DEFAULT NULL COMMENT 'Epoch registro',
  PRIMARY KEY (idProducto),
  FOREIGN KEY (idCategoria) REFERENCES SistemaVentasDB.Categorias(idCategoria)
)
ENGINE = INNODB,
COMMENT='Tabla de productos';

ALTER TABLE SistemaVentasDB.Productos ADD INDEX idxSku (sku);
ALTER TABLE SistemaVentasDB.Productos ADD INDEX idxIdCategoria (idCategoria);
ALTER TABLE SistemaVentasDB.Productos ADD INDEX idxFecha (fecha);
ALTER TABLE SistemaVentasDB.Productos ADD INDEX idxFechaHora (fechaHora);
ALTER TABLE SistemaVentasDB.Productos ADD INDEX idxTimestamp (timestamp);



CREATE TABLE SistemaVentasDB.Clientes (
  idCliente INTEGER UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'ID de cliente',
  nombres VARCHAR(50) NOT NULL COMMENT 'Nombres',
  apellidos VARCHAR(50) NOT NULL COMMENT 'Apellidos',
  telefono CHAR(15) NOT NULL COMMENT 'Telefono',
  celular CHAR(15) NOT NULL COMMENT 'Celular',
  tipoDocumento CHAR(10) NOT NULL COMMENT 'RUC/DNI/PASAPORTE',
  documento CHAR(15) NOT NULL COMMENT 'Documento de identidad',
  fechaNacimiento DATE DEFAULT NULL COMMENT 'Fecha de nacimiento',
  fecha DATE DEFAULT NULL COMMENT 'Fecha registro',
  fechaHora DATETIME DEFAULT NULL COMMENT 'Fecha Hora registro',
  timestamp BIGINT DEFAULT NULL COMMENT 'Epoch registro',
  PRIMARY KEY (idCliente)
)
ENGINE = INNODB,
COMMENT='Tabla de clientes';

ALTER TABLE SistemaVentasDB.Clientes ADD INDEX idxTipoDocumento (tipoDocumento);
ALTER TABLE SistemaVentasDB.Clientes ADD INDEX idxDocumento (documento);
ALTER TABLE SistemaVentasDB.Clientes ADD INDEX idxFechaNacimiento (fechaNacimiento);
ALTER TABLE SistemaVentasDB.Clientes ADD INDEX idxFecha (fecha);
ALTER TABLE SistemaVentasDB.Clientes ADD INDEX idxFechaHora (fechaHora);
ALTER TABLE SistemaVentasDB.Clientes ADD INDEX idxTimestamp (timestamp);



CREATE TABLE SistemaVentasDB.Inventario (
  idInventario INTEGER UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'ID de inventario',
  idProducto INTEGER UNSIGNED NOT NULL COMMENT 'ID de producto',
  cantidad INTEGER UNSIGNED NOT NULL COMMENT 'Cantidad disponible',
  precioUnitario DOUBLE NOT NULL COMMENT 'Precio unitario del producto',
  fecha DATE DEFAULT NULL COMMENT 'Fecha registro',
  fechaHora DATETIME DEFAULT NULL COMMENT 'Fecha Hora registro',
  timestamp BIGINT DEFAULT NULL COMMENT 'Epoch registro',
  PRIMARY KEY (idInventario),
  FOREIGN KEY (idProducto) REFERENCES SistemaVentasDB.Productos(idProducto)
)
ENGINE = INNODB,
COMMENT='Tabla de inventario';

ALTER TABLE SistemaVentasDB.Inventario ADD INDEX idxIdProducto (idProducto);
ALTER TABLE SistemaVentasDB.Inventario ADD INDEX idxFecha (fecha);
ALTER TABLE SistemaVentasDB.Inventario ADD INDEX idxFechaHora (fechaHora);
ALTER TABLE SistemaVentasDB.Inventario ADD INDEX idxTimestamp (timestamp);



CREATE TABLE SistemaVentasDB.Ordenes (
  idOrden INTEGER UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'ID de orden',
  idCliente INTEGER UNSIGNED NOT NULL COMMENT 'ID de cliente',
  idEmpleado INTEGER UNSIGNED NOT NULL COMMENT 'ID de empleado',
  montoTotal DOUBLE NOT NULL COMMENT 'Monto total de la compra',
  descuento DOUBLE NOT NULL COMMENT 'Descuento aplicado al monto',
  fecha DATE DEFAULT NULL COMMENT 'Fecha registro',
  fechaHora DATETIME DEFAULT NULL COMMENT 'Fecha Hora registro',
  timestamp BIGINT DEFAULT NULL COMMENT 'Epoch registro',
  PRIMARY KEY (idOrden),
  FOREIGN KEY (idCliente) REFERENCES SistemaVentasDB.Clientes(idCliente),
  FOREIGN KEY (idEmpleado) REFERENCES SistemaVentasDB.Empleados(idEmpleado)
)
ENGINE = INNODB,
COMMENT='Tabla de ordenes de compra';

ALTER TABLE SistemaVentasDB.Ordenes ADD INDEX idxIdCliente (idCliente);
ALTER TABLE SistemaVentasDB.Ordenes ADD INDEX idxIdEmpleado (idEmpleado);
ALTER TABLE SistemaVentasDB.Ordenes ADD INDEX idxFecha (fecha);
ALTER TABLE SistemaVentasDB.Ordenes ADD INDEX idxFechaHora (fechaHora);
ALTER TABLE SistemaVentasDB.Ordenes ADD INDEX idxTimestamp (timestamp);



CREATE TABLE SistemaVentasDB.Comprobantes (
  idComprobante INTEGER UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'ID de comprobante',
  idOrden INTEGER UNSIGNED NOT NULL COMMENT 'ID de orden asociada',
  tipo CHAR(10) NOT NULL COMMENT 'BOLETA/FACTURA',
  codigo CHAR(15) NOT NULL COMMENT 'Nro asociado al tipo de comprobante',
  subTotal DOUBLE NOT NULL COMMENT 'Monto sin el impuesto',
  impuesto DOUBLE NOT NULL COMMENT 'Monto del impuesto',
  fecha DATE DEFAULT NULL COMMENT 'Fecha registro',
  fechaHora DATETIME DEFAULT NULL COMMENT 'Fecha Hora registro',
  timestamp BIGINT DEFAULT NULL COMMENT 'Epoch registro',
  PRIMARY KEY (idComprobante),
  FOREIGN KEY (idOrden) REFERENCES SistemaVentasDB.Ordenes(idOrden)
)
ENGINE = INNODB,
COMMENT='Tabla de comprobantes';

ALTER TABLE SistemaVentasDB.Comprobantes ADD INDEX idxIdOrden (idOrden);
ALTER TABLE SistemaVentasDB.Comprobantes ADD INDEX idxTipo (tipo);
ALTER TABLE SistemaVentasDB.Comprobantes ADD INDEX idxCodigo (codigo);
ALTER TABLE SistemaVentasDB.Comprobantes ADD INDEX idxFecha (fecha);
ALTER TABLE SistemaVentasDB.Comprobantes ADD INDEX idxFechaHora (fechaHora);
ALTER TABLE SistemaVentasDB.Comprobantes ADD INDEX idxTimestamp (timestamp);



CREATE TABLE SistemaVentasDB.OrdenDetalles (
  idDetalle INTEGER UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'ID de detalle',
  idOrden INTEGER UNSIGNED NOT NULL COMMENT 'ID de orden asociada',
  idProducto INTEGER UNSIGNED NOT NULL COMMENT 'ID del producto asociado',
  cantidad INTEGER UNSIGNED NOT NULL COMMENT 'Cantidad comprada',
  precioUnitario DOUBLE NOT NULL COMMENT 'Precio unitario del producto',
  precioTotal DOUBLE NOT NULL COMMENT 'Precio total del registro',
  fecha DATE DEFAULT NULL COMMENT 'Fecha registro',
  fechaHora DATETIME DEFAULT NULL COMMENT 'Fecha Hora registro',
  timestamp BIGINT DEFAULT NULL COMMENT 'Epoch registro',
  PRIMARY KEY (idDetalle),
  FOREIGN KEY (idOrden) REFERENCES SistemaVentasDB.Ordenes(idOrden),
  FOREIGN KEY (idProducto) REFERENCES SistemaVentasDB.Productos(idProducto)
)
ENGINE = INNODB,
COMMENT='Tabla de detalles de las compras';

ALTER TABLE SistemaVentasDB.OrdenDetalles ADD INDEX idxIdOrden (idOrden);
ALTER TABLE SistemaVentasDB.OrdenDetalles ADD INDEX idxIdProducto (idProducto);
ALTER TABLE SistemaVentasDB.OrdenDetalles ADD INDEX idxFecha (fecha);
ALTER TABLE SistemaVentasDB.OrdenDetalles ADD INDEX idxFechaHora (fechaHora);
ALTER TABLE SistemaVentasDB.OrdenDetalles ADD INDEX idxTimestamp (timestamp);

DELIMITER //
CREATE PROCEDURE sp_insertarcategoria(IN nombre VARCHAR(100),IN fecha DATE,
            IN fechaHora DATETIME,IN timestamp BIGINT)
BEGIN
	INSERT INTO Categorias(nombre,fecha,fechaHora,timestamp) 
        VALUES(nombre,fecha,fechaHora,timestamp);
END //

CREATE PROCEDURE sp_editarcategoria(IN id INTEGER, IN nombre VARCHAR(100),
        IN fecha DATE,IN fechaHora DATETIME,IN timestamp_ BIGINT)
BEGIN
	UPDATE Categorias SET nombre = nombre,fecha = fecha, fechaHora = fechaHora,
            timestamp = timestamp_ WHERE idCategoria = id;
END //

CREATE PROCEDURE sp_eliminarcategoria(IN id INTEGER)
BEGIN
	DELETE FROM Categorias WHERE idCategoria = id;
END //




DELIMITER //
CREATE PROCEDURE sp_insertarproducto(IN sku CHAR(15),IN nombre VARCHAR(100),
        IN idCategoria INTEGER, IN descripcion TEXT,
	IN fecha DATE,IN fechaHora DATETIME,IN timestamp BIGINT)
BEGIN
	INSERT INTO Productos(sku,nombre,idCategoria,descripcion,fecha,
            fechaHora,timestamp) 
            VALUES(sku,nombre,idCategoria,descripcion,fecha,fechaHora,timestamp);
END //

CREATE PROCEDURE sp_editarproducto(IN id INTEGER, IN sku CHAR(15),
        IN nombre VARCHAR(100),
        IN idCategoria INTEGER, IN descripcion TEXT,
	IN fecha DATE,IN fechaHora DATETIME,IN timestamp BIGINT)
BEGIN
	UPDATE Productos SET sku = sku,nombre = nombre,idCategoria = idCategoria,
        descripcion = descripcion,fecha = fecha,fechaHora = fechaHora,
        timestamp = timestamp WHERE idProducto = id;
END //

CREATE PROCEDURE sp_eliminarproducto(IN id INTEGER)
BEGIN
	DELETE FROM Productos WHERE idProducto = id;
END //




DELIMITER //
CREATE PROCEDURE sp_insertarinventario(IN idProducto INTEGER,IN cantidad INTEGER,
        IN precioUnitario DOUBLE, IN fecha DATE,IN fechaHora DATETIME,
        IN timestamp BIGINT)
BEGIN
	INSERT INTO Inventario(idProducto,cantidad,precioUnitario,fecha,
        fechaHora,timestamp) 
	VALUES(idProducto,cantidad,precioUnitario,fecha,fechaHora,timestamp);
END //

CREATE PROCEDURE sp_editarinventario(IN id INTEGER, IN idProducto INTEGER,
        IN cantidad INTEGER,IN precioUnitario DOUBLE,
	IN fecha DATE,IN fechaHora DATETIME,IN timestamp BIGINT)
BEGIN
	UPDATE Inventario SET idProducto = idProducto,cantidad = cantidad,
        precioUnitario = precioUnitario, fecha = fecha,fechaHora = fechaHora,
        timestamp = timestamp WHERE idInventario = id;
END //

CREATE PROCEDURE sp_eliminarinventario(IN id INTEGER)
BEGIN
	DELETE FROM Inventario WHERE idInventario = id;
END //



DELIMITER //
CREATE PROCEDURE sp_insertarempleado(IN nombres VARCHAR(50), IN apellidos VARCHAR(50),
        IN telefono CHAR(15),IN celular CHAR(15),
	IN documento CHAR(15), IN tipoEmpleado CHAR(15), IN fechaNacimiento DATE, 
        IN fecha DATE,IN fechaHora DATETIME,IN timestamp BIGINT)
BEGIN
    INSERT INTO Usuarios(username,pass,fecha,fechaHora,timestamp) 
        VALUES(documento,MD5(documento),fecha,fechaHora,timestamp);
    
    SET @idUsuario = LAST_INSERT_ID();

    INSERT INTO Empleados(apellidos,celular,documento,idUsuario,tipoEmpleado,fecha,
        fechaHora,fechaNacimiento,nombres,telefono,timestamp) 
	VALUES(apellidos,celular,documento,@idUsuario,tipoEmpleado,fecha,fechaHora,
        fechaNacimiento,nombres,telefono,timestamp);

END //

CREATE PROCEDURE sp_editarempleado(IN id INTEGER, IN nombres VARCHAR(50), 
        IN apellidos VARCHAR(50),IN telefono CHAR(15),IN celular CHAR(15),
	IN documento CHAR(15), IN tipoEmpleado CHAR(15), IN fechaNacimiento DATE, 
        IN fecha DATE,IN fechaHora DATETIME,IN timestamp BIGINT)
BEGIN
	UPDATE Empleados SET apellidos = apellidos,celular = celular,
        tipoEmpleado = tipoEmpleado,documento = documento,fecha = fecha,
        fechaHora = fechaHora,fechaNacimiento = fechaNacimiento,nombres = nombres,
        telefono = telefono,timestamp = timestamp WHERE idEmpleado = id;
END //

CREATE PROCEDURE sp_eliminarempleado(IN id INTEGER)
BEGIN
	DELETE FROM Empleados WHERE idEmpleado = id;
END //



DELIMITER //
CREATE PROCEDURE sp_insertarcliente(IN nombres VARCHAR(50), IN apellidos VARCHAR(50),
        IN telefono CHAR(15),IN celular CHAR(15),
	IN documento CHAR(15), IN tipoDocumento CHAR(10), IN fechaNacimiento DATE, 
        IN fecha DATE,IN fechaHora DATETIME,IN timestamp BIGINT)
BEGIN
	INSERT INTO Clientes(apellidos,celular,documento,tipoDocumento,fecha,
        fechaHora,fechaNacimiento,nombres,telefono,timestamp) 
	VALUES(apellidos,celular,documento,tipoDocumento,fecha,fechaHora,
        fechaNacimiento,nombres,telefono,timestamp);
END //

CREATE PROCEDURE sp_editarcliente(IN id INTEGER, IN nombres VARCHAR(50), 
        IN apellidos VARCHAR(50),IN telefono CHAR(15),IN celular CHAR(15),
	IN documento CHAR(15), IN tipoDocumento CHAR(10), IN fechaNacimiento DATE, 
        IN fecha DATE,IN fechaHora DATETIME,IN timestamp BIGINT)
BEGIN
	UPDATE Clientes SET apellidos = apellidos,celular = celular,
        tipoDocumento = tipoDocumento,documento = documento,fecha = fecha,
        fechaHora = fechaHora,fechaNacimiento = fechaNacimiento,
	nombres = nombres,telefono = telefono,timestamp = timestamp
        WHERE idCliente = id;
END //

CREATE PROCEDURE sp_eliminarcliente(IN id INTEGER)
BEGIN
	DELETE FROM Clientes WHERE idCliente = id;
END //

DELIMITER //
CREATE PROCEDURE sp_login(IN username CHAR(20), IN pwd CHAR(50))
BEGIN
	SELECT MD5(pwd) INTO @realpass;
	SELECT idUsuario, username,fecha,fechaHora,timestamp
        FROM Usuarios WHERE username = username AND
        pass = @realpass;
END //

CALL sp_insertarempleado('root','root','root','root','root','Administrador',
    null,null,null,null);