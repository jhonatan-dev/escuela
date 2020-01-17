INSERT INTO public.imagen_producto(
	ruta_imagen, ruta_thumbnail)
	VALUES ('C:\imagen01.jpg', 'C:\imagen01_thumbnail.jpg');
INSERT INTO public.imagen_producto(
	ruta_imagen, ruta_thumbnail)
	VALUES ('C:\imagen02.jpg', 'C:\imagen02_thumbnail.jpg');
INSERT INTO public.imagen_producto(
	ruta_imagen, ruta_thumbnail)
	VALUES ('C:\imagen03.jpg', 'C:\imagen03_thumbnail.jpg');
INSERT INTO public.tipo_producto(
	codigo, nombre)
	VALUES ('TP00001', 'Tipo Producto 1');
INSERT INTO public.tipo_producto(
	codigo, nombre)
	VALUES ('TP00002', 'Tipo Producto 2');
INSERT INTO public.tipo_producto(
	codigo, nombre)
	VALUES ('TP00003', 'Tipo Producto 3');
INSERT INTO public.producto(
	activo, codigo, descripcion, nombre, precio, imagen_producto_id, tipo_producto_id)
	VALUES (true, 'P0001', 'Descripcion 1', 'Producto 1', 12.4, 2, 1);
INSERT INTO public.producto(
	activo, codigo, descripcion, nombre, precio, imagen_producto_id, tipo_producto_id)
	VALUES (true, 'P0002', 'Descripcion 3', 'Producto 2', 32.1, 3, 2);
INSERT INTO public.producto(
	activo, codigo, descripcion, nombre, precio, imagen_producto_id, tipo_producto_id)
	VALUES (true, 'P0003', 'Descripcion 3', 'Producto 3', 22.6, 1, 2);