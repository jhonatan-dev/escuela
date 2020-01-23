"use strict";

$(document).ready(function() {
  $("#customFile1").on("change", function() {
    var nombreArchivo = $(this)
      .val()
      .split("\\")
      .pop();
    $(this)
      .siblings("#customFileLabel1")
      .addClass("selected")
      .html(nombreArchivo);
  });
});
