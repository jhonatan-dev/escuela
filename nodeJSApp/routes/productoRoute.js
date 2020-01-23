"use strict";

const express = require("express");
const router = express.Router();

const axios = require("axios");

const { gatewayURL } = require("../keys/Gateway");

router.get("/", async (req, res) => {
  var lista_productos = [];
  var resultado = null;
  try {
    resultado = await axios.get(gatewayURL + "/producto/productos/");
    if (resultado.status === 200) {
      lista_productos = resultado.data;
    }
  } catch (error) {
    console.error(error);
  }
  res.render("productosViews/lista", {
    lista_productos,
    tituloVentana: "Lista de Productos"
  });
});

router.get("/agregar", async (req, res) => {
  var lista_tipoProductos = [];
  var resultado = null;
  try {
    resultado = await axios.get(gatewayURL + "/producto/tipoproductos/");
    if (resultado.status === 200) {
      lista_tipoProductos = resultado.data;
    }
  } catch (error) {
    console.error(error);
  }
  res.render("productosViews/agregar", {
    lista_tipoProductos,
    tituloVentana: "Agregar Nuevo Producto"
  });
});

router.post("/agregar", async (req, res) => {
  var nuevoProducto = {
    nombre: req.body.nombre,
    codigo: req.body.codigo,
    descripcion: req.body.descripcion,
    precio: req.body.precio,
    codigo_producto: req.body.codigo_producto,
    ruta_imagen: req.body.ruta_imagen,
    ruta_thumbnail: req.body.ruta_imagen
  };
  try {
    var resultado = await axios.post(
      gatewayURL + "/producto/productos/",
      nuevoProducto
    );
  } catch (error) {
    console.error(error);
    return res.redirect("/productos/agregar");
  }
  return res.redirect("/productos");
});

module.exports = router;
