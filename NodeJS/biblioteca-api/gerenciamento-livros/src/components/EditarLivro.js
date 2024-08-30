import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import styled from "styled-components";

// Estilos para o container do formulário
const FormContainer = styled.form`
  max-width: 500px;
  margin: 50px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
`;

// Estilos para o título
const Title = styled.h1`
  font-size: 2em;
  text-align: center;
  color: #333;
  margin-bottom: 20px;
`;

// Estilos para os inputs
const Input = styled.input`
  width: 100%;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 1em;
  box-sizing: border-box;

  &:focus {
    border-color: #007bff;
    outline: none;
  }
`;

// Estilos para o botão
const Button = styled.button`
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  font-size: 1em;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;

  &:hover {
    background-color: #0056b3;
  }
`;

function EditarLivro() {
  const { id } = useParams();
  const [titulo, setTitulo] = useState("");
  const [autor, setAutor] = useState("");
  const [ano, setAno] = useState("");
  const [genero, setGenero] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`http://localhost:5000/livros/${id}`)
      .then((response) => {
        setTitulo(response.data.titulo);
        setAutor(response.data.autor);
        setAno(response.data.ano);
        setGenero(response.data.genero);
      })
      .catch((error) => console.error("Erro ao buscar o livro:", error));
  }, [id]);

  const atualizarLivro = (e) => {
    e.preventDefault();
    axios
      .put(`http://localhost:5000/livros/${id}`, { titulo, autor, ano, genero })
      .then(() => navigate("/"))
      .catch((error) => console.error("Erro ao atualizar livro:", error));
  };

  return (
    <FormContainer onSubmit={atualizarLivro}>
      <Title>Editar Livro</Title>
      <Input
        type="text"
        value={titulo}
        onChange={(e) => setTitulo(e.target.value)}
        placeholder="Título"
        required
      />
      <Input
        type="text"
        value={autor}
        onChange={(e) => setAutor(e.target.value)}
        placeholder="Autor"
        required
      />
      <Input
        type="number"
        value={ano}
        onChange={(e) => setAno(e.target.value)}
        placeholder="Ano de Publicação"
        required
      />
      <Input
        type="text"
        value={genero}
        onChange={(e) => setGenero(e.target.value)}
        placeholder="Gênero"
        required
      />
      <Button type="submit">Atualizar</Button>
    </FormContainer>
  );
}

export default EditarLivro;
