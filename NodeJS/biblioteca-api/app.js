const express = require("express");
const bodyParser = require("body-parser");
const livroRoutes = require("./routes/livroRoutes");

require("dotenv").config();
require("./config/database"); // Conectando ao banco de dados

const app = express();
const path = require("path");


// Middlewares
app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, "public")));

// Rotas
app.use("/livros", livroRoutes);
app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "public", "index.html"));
});

// Importar e usar as rotas dos livros
// const livrosRoutes = require("./routes/livrosRoutes");
// app.use("/livros", livrosRoutes);

// Exportando a aplicação configurada
module.exports = app;