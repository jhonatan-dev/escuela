"use strict";

$(document).ready(function() {
  var btnAgregarNuevaOrden = document.getElementById("agregarNuevaOrden");
  btnAgregarNuevaOrden.addEventListener("click", function() {
    window.location.href = "/ordenes/agregar";
  });
});
