"use strict";

const express = require("express");
const morgan = require("morgan");
const path = require("path");
const exphbs = require("express-handlebars");
const favicon = require("serve-favicon");
const app = express();
const http = require("http").createServer(app);
const cookieParser = require("cookie-parser");

//Configuraciones
app.set("port", process.env.PORT || 4000);
app.set("views", path.join(__dirname, "views"));
app.engine(
  ".hbs",
  exphbs({
    defaultLayout: "main",
    layoutsDir: path.join(app.get("views"), "layouts"),
    partialsDir: path.join(app.get("views"), "partials"),
    extname: ".hbs",
    helpers: require("./config/handlebars")
  })
);
app.set("view engine", ".hbs");

//Middlewares
app.use(favicon(path.join(__dirname, "public/img", "logo.png")));
app.use(morgan("dev"));
app.use(express.json());
app.use(cookieParser());
app.use(express.urlencoded({ extended: false }));

//Rutas de la aplicación
app.use(require("./routes/index"));
app.use("/productos", require("./routes/productoRoute"));

//Carpeta Pública donde estarán los .css .js .png .jpg
app.use(express.static(path.join(__dirname, "public")));

//Iniciando el servidor
http.listen(app.get("port"), () => {
  console.log(`Servidor ejecutándose por el puerto ${app.get("port")}`);
});
