"use strict";

const express = require("express");
const router = express.Router();

const axios = require("axios");

const { gatewayURL } = require("../keys/Gateway");

router.get("/", async (req, res) => {
  var lista_ordenes = [];
  var resultado = null;
  try {
    resultado = await axios.get(gatewayURL + "/orden/ordenes/");
    if (resultado.status === 200) {
      lista_ordenes = resultado.data;
    }
  } catch (error) {
    console.error(error);
  }
  res.render("ordenesViews/lista", {
    lista_ordenes,
    tituloVentana: "Lista de Ordenes"
  });
});

router.get("/agregar", async (req, res) => {
  res.render("ordenesViews/agregar", {
    tituloVentana: "Agregar Nueva Orden"
  });
});

router.post("/agregar", async (req, res) => {
    res.send(req.body);
});

module.exports = router;
