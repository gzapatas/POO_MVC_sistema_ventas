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
CREATE DATABASE SistemaVentasDB
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

CREATE TABLE SistemaVentasDB.Cajeros (
  idCajero INTEGER UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'Id de Cajero',
  nombres VARCHAR(50) NOT NULL COMMENT 'Nombres',
  apellidos VARCHAR(50) NOT NULL COMMENT 'Apellidos',
  telefono CHAR(15) NOT NULL COMMENT 'Telefono',
  celular CHAR(15) NOT NULL COMMENT 'Celular',
  documento CHAR(15) NOT NULL COMMENT 'Documento de identidad',
  fechaNacimiento DATE DEFAULT NULL COMMENT 'Fecha de nacimiento',
  fecha DATE DEFAULT NULL COMMENT 'Fecha registro',
  fechaHora DATETIME DEFAULT NULL COMMENT 'Fecha Hora registro',
  timestamp BIGINT DEFAULT NULL COMMENT 'Epoch registro',
  PRIMARY KEY (idCajero)
)
ENGINE = INNODB,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_general_ci,
COMMENT='Tabla de cajeros';

ALTER TABLE SistemaVentasDB.Cajeros ADD INDEX idxDocumento (documento);
ALTER TABLE SistemaVentasDB.Cajeros ADD INDEX idxFechaNacimiento (fechaNacimiento);
ALTER TABLE SistemaVentasDB.Cajeros ADD INDEX idxFecha (fecha);
ALTER TABLE SistemaVentasDB.Cajeros ADD INDEX idxFechaHora (fechaHora);
ALTER TABLE SistemaVentasDB.Cajeros ADD INDEX idxTimestamp (timestamp);



CREATE TABLE SistemaVentasDB.Categorias (
  idCategoria INTEGER UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'ID de categoria',
  nombre VARCHAR(100) NOT NULL COMMENT 'Nombre de la categoria',
  fecha DATE DEFAULT NULL COMMENT 'Fecha registro',
  fechaHora DATETIME DEFAULT NULL COMMENT 'Fecha Hora registro',
  timestamp BIGINT DEFAULT NULL COMMENT 'Epoch registro',
  PRIMARY KEY (idCategoria)
)
ENGINE = INNODB,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_general_ci,
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
CHARACTER SET utf8mb4,
COLLATE utf8mb4_general_ci,
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
CHARACTER SET utf8mb4,
COLLATE utf8mb4_general_ci,
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
CHARACTER SET utf8mb4,
COLLATE utf8mb4_general_ci,
COMMENT='Tabla de inventario';

ALTER TABLE SistemaVentasDB.Inventario ADD INDEX idxIdProducto (idProducto);
ALTER TABLE SistemaVentasDB.Inventario ADD INDEX idxFecha (fecha);
ALTER TABLE SistemaVentasDB.Inventario ADD INDEX idxFechaHora (fechaHora);
ALTER TABLE SistemaVentasDB.Inventario ADD INDEX idxTimestamp (timestamp);



CREATE TABLE SistemaVentasDB.Ordenes (
  idOrden INTEGER UNSIGNED AUTO_INCREMENT NOT NULL COMMENT 'ID de orden',
  idCliente INTEGER UNSIGNED NOT NULL COMMENT 'ID de cliente',
  idCajero INTEGER UNSIGNED NOT NULL COMMENT 'ID de cajero',
  montoTotal DOUBLE NOT NULL COMMENT 'Monto total de la compra',
  descuento DOUBLE NOT NULL COMMENT 'Descuento aplicado al monto',
  fecha DATE DEFAULT NULL COMMENT 'Fecha registro',
  fechaHora DATETIME DEFAULT NULL COMMENT 'Fecha Hora registro',
  timestamp BIGINT DEFAULT NULL COMMENT 'Epoch registro',
  PRIMARY KEY (idOrden),
  FOREIGN KEY (idCliente) REFERENCES SistemaVentasDB.Clientes(idCliente),
  FOREIGN KEY (idCajero) REFERENCES SistemaVentasDB.Cajeros(idCajero)
)
ENGINE = INNODB,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_general_ci,
COMMENT='Tabla de ordenes de compra';

ALTER TABLE SistemaVentasDB.Ordenes ADD INDEX idxIdCliente (idCliente);
ALTER TABLE SistemaVentasDB.Ordenes ADD INDEX idxIdCajero (idCajero);
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
CHARACTER SET utf8mb4,
COLLATE utf8mb4_general_ci,
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
CHARACTER SET utf8mb4,
COLLATE utf8mb4_general_ci,
COMMENT='Tabla de detalles de las compras';

ALTER TABLE SistemaVentasDB.OrdenDetalles ADD INDEX idxIdOrden (idOrden);
ALTER TABLE SistemaVentasDB.OrdenDetalles ADD INDEX idxIdProducto (idProducto);
ALTER TABLE SistemaVentasDB.OrdenDetalles ADD INDEX idxFecha (fecha);
ALTER TABLE SistemaVentasDB.OrdenDetalles ADD INDEX idxFechaHora (fechaHora);
ALTER TABLE SistemaVentasDB.OrdenDetalles ADD INDEX idxTimestamp (timestamp);