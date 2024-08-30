import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import styled, { keyframes } from "styled-components";

// Animação para fade-in
const fadeIn = keyframes`
  from {
    opacity: 0;
    transform: translateY(-10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
`;

// Estilos para o container
const Container = styled.div`
  padding: 20px;
  background-color: #f9f9f9;
  min-height: 100vh;
`;

// Estilos para o título
const Title = styled.h1`
  font-size: 2.5em;
  color: #333;
  text-align: center;
`;

// Estilos para o botão de adicionar novo livro
const AddButton = styled(Link)`
  display: inline-block;
  margin: 20px 0;
  padding: 10px 20px;
  background-color: #28a745;
  color: white;
  text-decoration: none;
  border-radius: 5px;
  text-align: center;
  transition: background-color 0.3s;

  &:hover {
    background-color: #218838;
  }
`;

// Estilos para a lista
const BookList = styled.ul`
  list-style: none;
  padding: 0;
`;

// Estilos para os itens da lista com animação
const BookItem = styled.li`
  background-color: #fff;
  margin: 10px 0;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: space-between;
  align-items: center;
  animation: ${fadeIn} 0.5s ease-in-out;
`;

// Estilos para os links de edição e deleção
const EditLink = styled(Link)`
  margin-right: 10px;
  text-decoration: none;
  color: #007bff;

  &:hover {
    text-decoration: underline;
  }
`;

const DeleteButton = styled.button`
  background-color: #dc3545;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
  transition: background-color 0.3s;

  &:hover {
    background-color: #c82333;
  }
`;

function ListaLivros() {
  const [livros, setLivros] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:5000/livros")
      .then((response) => setLivros(response.data))
      .catch((error) => {
        console.error("Erro ao buscar os livros:", error);
        console.error("Detalhes do erro:", error.toJSON());
      });
  }, []);

  const deletarLivro = (id) => {
    axios
      .delete(`http://localhost:5000/livros/${id}`)
      .then(() => setLivros(livros.filter((livro) => livro._id !== id)))
      .catch((error) => console.error("Erro ao deletar o livro:", error));
  };

  return (
    <Container>
      <Title>Lista de Livros</Title>
      <AddButton to="/novo">Adicionar Novo Livro</AddButton>
      <BookList>
        {livros.map((livro) => (
          <BookItem key={livro._id}>
            <div>
              {livro.titulo} - {livro.autor}
            </div>
            <div>
              <EditLink to={`/editar/${livro._id}`}>Editar</EditLink>
              <DeleteButton onClick={() => deletarLivro(livro._id)}>
                Deletar
              </DeleteButton>
            </div>
          </BookItem>
        ))}
      </BookList>
    </Container>
  );
}

export default ListaLivros;
