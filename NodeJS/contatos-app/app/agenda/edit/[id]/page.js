"use client";

import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import themes from "../../themes";

export default function EditContact({ params }) {
  const router = useRouter();
  const { id } = params; // Pegando o ID do contato da URL
  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [profilePicture, setProfilePicture] = useState("");
  const [currentTheme, setCurrentTheme] = useState("light"); // Defina o tema padrão aqui

  useEffect(() => {
    // Função para buscar os dados do contato
    const fetchContact = async () => {
      try {
        const response = await fetch(`/api/contacts/${id}`);
        const data = await response.json();

        if (data.success) {
          setName(data.data.name);
          setPhone(data.data.phone);
          setEmail(data.data.email);
          setAddress(data.data.address);
          setProfilePicture(data.data.profilePicture);
        } else {
          alert("Erro ao carregar contato: " + data.error);
        }
      } catch (error) {
        console.error("Erro ao carregar contato:", error);
        alert("Erro ao carregar contato. Tente novamente.");
      }
    };

    fetchContact();
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validações básicas
    if (!name || !phone || !email) {
      alert("Por favor, preencha todos os campos.");
      return;
    }

    try {
      const response = await fetch(`/api/contacts/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ name, phone, email, profilePicture, address }),
      });

      const result = await response.json();

      if (result.success) {
        alert("Contato atualizado com sucesso!");
        router.push("/agenda"); // Redireciona para a página principal ou para a lista de contatos
      } else {
        alert("Erro ao atualizar contato: " + result.error);
      }
    } catch (error) {
      console.error("Erro ao atualizar contato:", error);
      alert("Erro ao atualizar contato. Tente novamente.");
    }
  };

  const theme = themes[currentTheme];

  return (
    <div
      style={{
        padding: "20px",
        backgroundColor: theme.background,
        color: theme.text,
      }}
    >
      <h1>Editar Contato</h1>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: "10px" }}>
          <label htmlFor="name">Nome:</label>
          <input
            type="text"
            id="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            style={{
              display: "block",
              width: "100%",
              padding: "8px",
              borderRadius: "4px",
              border: `1px solid ${theme.border}`,
              backgroundColor: theme.buttonBackground,
              color: theme.text,
            }}
          />
        </div>
        <div style={{ marginBottom: "10px" }}>
          <label htmlFor="phone">Telefone:</label>
          <input
            type="text"
            id="phone"
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            style={{
              display: "block",
              width: "100%",
              padding: "8px",
              borderRadius: "4px",
              border: `1px solid ${theme.border}`,
              backgroundColor: theme.buttonBackground,
              color: theme.text,
            }}
          />
        </div>
        <div style={{ marginBottom: "10px" }}>
          <label htmlFor="email">E-mail:</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            style={{
              display: "block",
              width: "100%",
              padding: "8px",
              borderRadius: "4px",
              border: `1px solid ${theme.border}`,
              backgroundColor: theme.buttonBackground,
              color: theme.text,
            }}
          />
        </div>
        <div style={{ marginBottom: "10px" }}>
          <label htmlFor="address">Endereço:</label>
          <input
            type="text"
            id="address"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
            style={{
              display: "block",
              width: "100%",
              padding: "8px",
              borderRadius: "4px",
              border: `1px solid ${theme.border}`,
              backgroundColor: theme.buttonBackground,
              color: theme.text,
            }}
          />
        </div>
        <div style={{ marginBottom: "10px" }}>
          <label htmlFor="profilePicture">Foto de Perfil (URL):</label>
          <input
            type="text"
            id="profilePicture"
            value={profilePicture}
            onChange={(e) => setProfilePicture(e.target.value)}
            style={{
              display: "block",
              width: "100%",
              padding: "8px",
              borderRadius: "4px",
              border: `1px solid ${theme.border}`,
              backgroundColor: theme.buttonBackground,
              color: theme.text,
            }}
          />
        </div>
        <button
          type="submit"
          style={{
            padding: "10px 20px",
            backgroundColor: theme.buttonEdit,
            color: theme.buttonText,
            border: "none",
            borderRadius: "4px",
            cursor: "pointer",
          }}
        >
          Atualizar
        </button>
      </form>
    </div>
  );
}
