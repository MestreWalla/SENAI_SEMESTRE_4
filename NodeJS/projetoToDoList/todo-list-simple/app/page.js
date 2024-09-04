"use client";

import { useState, useEffect } from "react";

export default function Home() {
  const [todos, setTodos] = useState([]);
  const [newTodo, setNewTodo] = useState("");
  const [editingId, setEditingId] = useState(null);
  const [editingTitle, setEditingTitle] = useState("");

  useEffect(() => {
    fetchTodos();
  }, []);

  const fetchTodos = async () => {
    const response = await fetch("/api/todos");
    const data = await response.json();
    setTodos(data.data);
  };

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

  const editTodo = (id, currentTitle) => {
    setEditingId(id);
    setEditingTitle(currentTitle);
  };

  const updateTodo = async () => {
    const response = await fetch(`/api/todos/${editingId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ title: editingTitle }),
    });
    const data = await response.json();
    setTodos(todos.map((todo) => (todo._id === editingId ? data.data : todo)));
    setEditingId(null);
    setEditingTitle("");
  };

  const toggleCompletion = async (id, currentStatus) => {
    const response = await fetch(`/api/todos/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ completed: !currentStatus }), // Inverte o estado de conclusão
    });
    const data = await response.json();
    setTodos(todos.map((todo) => (todo._id === id ? data.data : todo)));
  };

  const deleteTodo = async (id) => {
    await fetch(`/api/todos/${id}`, {
      method: "DELETE",
    });
    setTodos(todos.filter((todo) => todo._id !== id));
  };

  return (
    <div style={{ padding: "20px", maxWidth: "600px", margin: "0 auto" }}>
      <h1 style={{ textAlign: "center", marginBottom: "20px" }}>To-Do List</h1>
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          marginBottom: "20px",
        }}
      >
        <input
          type="text"
          value={newTodo}
          onChange={(e) => setNewTodo(e.target.value)}
          style={{
            flex: 1,
            padding: "10px",
            marginRight: "10px",
            borderRadius: "4px",
            border: "1px solid #ccc",
          }}
          placeholder="Nova tarefa..."
        />
        <button
          onClick={addTodo}
          style={{
            padding: "10px 20px",
            borderRadius: "4px",
            backgroundColor: "#0070f3",
            color: "#fff",
            border: "none",
            cursor: "pointer",
          }}
        >
          Adicionar
        </button>
      </div>
      <ul style={{ listStyle: "none", padding: 0 }}>
        {todos.map((todo) => (
          <li
            key={todo._id}
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "space-between",
              padding: "10px",
              borderBottom: "1px solid #eee",
              marginBottom: "10px",
            }}
          >
            {editingId === todo._id ? (
              <>
                <input
                  type="text"
                  value={editingTitle}
                  onChange={(e) => setEditingTitle(e.target.value)}
                  style={{
                    flex: 1,
                    padding: "10px",
                    marginRight: "10px",
                    borderRadius: "4px",
                    border: "1px solid #ccc",
                  }}
                />
                <button
                  onClick={updateTodo}
                  style={{
                    padding: "5px 10px",
                    marginRight: "5px",
                    borderRadius: "4px",
                    backgroundColor: "#28a745",
                    color: "#fff",
                    border: "none",
                    cursor: "pointer",
                  }}
                >
                  Salvar
                </button>
                <button
                  onClick={() => setEditingId(null)}
                  style={{
                    padding: "5px 10px",
                    borderRadius: "4px",
                    backgroundColor: "#dc3545",
                    color: "#fff",
                    border: "none",
                    cursor: "pointer",
                  }}
                >
                  Cancelar
                </button>
              </>
            ) : (
              <>
                <div style={{ display: "flex", alignItems: "center", flex: 1 }}>
                  <input
                    type="checkbox"
                    checked={todo.completed}
                    onChange={() => toggleCompletion(todo._id, todo.completed)}
                    style={{ marginRight: "10px" }}
                  />
                  <span
                    style={{
                      flex: 1,
                      textDecoration: todo.completed ? "line-through" : "none",
                      color: todo.completed ? "green" : "black",
                      backgroundColor: todo.completed ? "gray" : "white",
                    }}
                  >
                    {todo.title}
                  </span>
                </div>
                <button
                  onClick={() => editTodo(todo._id, todo.title)}
                  style={{
                    padding: "5px 10px",
                    marginRight: "5px",
                    borderRadius: "4px",
                    backgroundColor: "#ffc107",
                    color: "#fff",
                    border: "none",
                    cursor: "pointer",
                  }}
                >
                  Editar
                </button>
                <button
                  onClick={() => deleteTodo(todo._id)}
                  style={{
                    padding: "5px 10px",
                    borderRadius: "4px",
                    backgroundColor: "#dc3545",
                    color: "#fff",
                    border: "none",
                    cursor: "pointer",
                  }}
                >
                  Excluir
                </button>
              </>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}
