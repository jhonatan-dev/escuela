"use strict";

$(document).ready(function() {
  var btnAgregarProducto = document.getElementById("agregarProducto");
  btnAgregarProducto.addEventListener("click", function() {
    window.location.href = "/productos/agregar";
  });
});
