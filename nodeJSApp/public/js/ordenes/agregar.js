"use strict";

$(document).ready(function() {
  var fechaActual = new Date();
  $("#fecha_envio").attr("min", `${fechaActual.toISOString().split("T")[0]}`);
});
