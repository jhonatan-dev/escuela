"use strict";

const express = require("express");
const router = express.Router();

router.get("/", async (req, res) => {
    res.render("index", {
        tituloVentana : "Inicio"
    });
});

module.exports = router;