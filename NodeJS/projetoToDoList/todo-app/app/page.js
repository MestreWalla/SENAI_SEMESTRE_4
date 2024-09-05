"use client";

import { useState, useEffect } from "react";

export default function Home() {
  const [todos, setTodos] = useState([]);
  const [newTodo, setNewTodo] = useState("");

  useEffect(() => {
    fetchTodos();
  }, []);

  // Função para buscar todas as tarefas
  const fetchTodos = async () => {
    const response = await fetch("/api/todos");
    const data = await response.json();
    setTodos(data.data);
  };

  // Função para adicionar uma nova tarefa
  const addTodo = async () => {
    const response = await fetch("/api/todos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ title: newTodo }),
    });
    const data = await response.json();
    setTodos([...todos, data.data]);
    setNewTodo("");
  };

  // Função para deletar uma tarefa
  const deleteTodo = async (id) => {
    await fetch(`/api/todos/${id}`, {
      method: "DELETE",
    });
    setTodos(todos.filter((todo) => todo._id !== id));
  };

  // Função para atualizar o status de uma tarefa
  const updateTodo = async (id, newStatus) => {
    const response = await fetch(`/api/todos/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ completed: newStatus }),
    });
    const data = await response.json();
    setTodos(todos.map((todo) => (todo._id === id ? data.data : todo)));
  };

  return (
    <body style={styles.body}>
      <div style={styles.container}>
        <h1 style={styles.title}>To-Do List</h1>
        <div style={styles.inputContainer}>
          <input
            type="text"
            value={newTodo}
            onChange={(e) => setNewTodo(e.target.value)}
            placeholder="Digite uma nova tarefa"
            style={styles.input}
          />
          <button onClick={addTodo} style={styles.addButton}>
            Adicionar Tarefa
          </button>
        </div>
        <ul style={styles.list}>
          {todos.map((todo) => (
            <li key={todo._id} style={styles.listItem} className="fade-in">
              <span style={styles.todoText}>
                {todo.title} - {todo.completed}
              </span>
              <div style={styles.actions}>
                <select
                  value={todo.completed}
                  onChange={(e) => updateTodo(todo._id, e.target.value)}
                  style={styles.select}
                >
                  <option value="A fazer">A fazer</option>
                  <option value="Fazendo">Fazendo</option>
                  <option value="Concluido">Concluido</option>
                </select>
                <button
                  onClick={() => deleteTodo(todo._id)}
                  style={styles.deleteButton}
                >
                  Excluir
                </button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </body>
  );
}

// Estilizações e Animações Avançadas
const styles = {
  body: {
    margin: "0",
    padding: "0",
    backgroundcolor: "#f0f0f0",
    fontfamily: "'Poppins', sans-serif",
    color: "#333",
    minHeight: "100vh",
  },
  container: {
    maxWidth: "700px",
    margin: "50px auto",
    padding: "30px",
    backgroundColor: "#f5f5f5",
    borderRadius: "15px",
    boxShadow: "0 10px 30px rgba(0, 0, 0, 0.1)",
    fontFamily: "'Poppins', sans-serif",
  },
  title: {
    textAlign: "center",
    fontSize: "36px",
    color: "#333",
    marginBottom: "20px",
    animation: "fade-in-title 1s ease-out",
  },
  inputContainer: {
    display: "flex",
    justifyContent: "space-between",
    marginBottom: "20px",
  },
  input: {
    flex: 1,
    padding: "12px",
    fontSize: "18px",
    borderRadius: "10px",
    border: "1px solid #ccc",
    marginRight: "10px",
    transition: "box-shadow 0.3s",
    boxShadow: "0 3px 6px rgba(0, 0, 0, 0.1)",
  },
  addButton: {
    padding: "12px 20px",
    fontSize: "18px",
    borderRadius: "10px",
    backgroundColor: "#28a745",
    color: "#fff",
    border: "none",
    cursor: "pointer",
    transition: "background-color 0.3s, transform 0.2s",
  },
  list: {
    listStyleType: "none",
    padding: 0,
  },
  listItem: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: "15px",
    marginBottom: "10px",
    backgroundColor: "#fff",
    borderRadius: "10px",
    boxShadow: "0 3px 6px rgba(0, 0, 0, 0.1)",
    opacity: 0,
    transform: "translateY(-10px)",
    animation: "fade-in 0.5s forwards",
  },
  todoText: {
    flex: 1,
    fontSize: "20px",
    color: "#333",
  },
  actions: {
    display: "flex",
    alignItems: "center",
  },
  select: {
    marginRight: "10px",
    padding: "8px",
    fontSize: "16px",
    borderRadius: "8px",
    border: "1px solid #ccc",
    boxShadow: "0 3px 6px rgba(0, 0, 0, 0.1)",
    transition: "transform 0.2s, box-shadow 0.2s",
  },

  deleteButton: {
    padding: "8px 12px",
    fontSize: "14px",
    borderRadius: "8px",
    backgroundColor: "#dc3545",
    color: "#fff",
    border: "none",
    cursor: "pointer",
    transition: "transform 0.2s, box-shadow 0.2s",
  },
};

// Adicionando animações ao CSS usando keyframes
const globalStyles = `
  @keyframes fade-in {
    0% {
      opacity: 0;
      transform: translateY(-10px);
    }
    100% {
      opacity: 1;
      transform: translateY(0);
    }
  }

  @keyframes fade-in-title {
    0% {
      opacity: 0;
      transform: translateY(-30px);
    }
    100% {
      opacity: 1;
      transform: translateY(0);
    }
  }

  .fade-in {
    animation: fade-in 0.5s ease-in-out forwards;
  }

  input:focus {
    box-shadow: 0 0 10px rgba(0, 123, 255, 0.5);
  }

  button:hover {
    transform: scale(1.05);
  }

  select:hover {
    transform: scale(1.05);
  }

  select:active {
    transform: scale(1.05);
  }

  button:active {
    transform: scale(0.95);
  }
`;

// Inserindo as animações diretamente no documento
if (typeof document !== "undefined") {
  const styleSheet = document.createElement("style");
  styleSheet.type = "text/css";
  styleSheet.innerText = globalStyles;
  document.head.appendChild(styleSheet);
}
