const express = require("express");
const bodyParser = require("body-parser");
const livroRoutes = require("./routes/livroRoutes");

require("dotenv").config();
require("./config/database"); // Conectando ao banco de dados

const app = express();
const path = require("path");

// #region Middlewares
app.use(bodyParser.json()); // Permite o parse de dados JSON nos requests
app.use(express.static(path.join(__dirname, "public"))); // Serve arquivos estáticos da pasta 'public'
// #endregion Middlewares

// #region Rotas
app.use("/livros", livroRoutes); // Define as rotas para livros, delegando para o arquivo livroRoutes.js
app.get("/editar/:id", (req, res) => {
  res.sendFile(path.join(__dirname, "public", "editar.html"));});
app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "public", "index.html"));
}); // Serve o arquivo index.html como página inicial


// #endregion Rotas

// Importar e usar as rotas dos livros
// const livrosRoutes = require("./routes/livrosRoutes");
// app.use("/livros", livrosRoutes);

// Exportando a aplicação configurada
module.exports = app;
