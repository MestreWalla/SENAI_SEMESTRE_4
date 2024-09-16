'use client';

import { useState } from 'react';

export default function AddContact() {
  const [name, setName] = useState('');
  const [phone, setPhone] = useState('');
  const [email, setEmail] = useState('');
  const [address, setAddress] = useState("");
  const [profilePicture, setProfilePicture] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validações básicas
    if (!name || !phone || !email) {
      alert('Por favor, preencha todos os campos.');
      return;
    }

    try {
      const response = await fetch("/api/contacts", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ name, phone, email, profilePicture, address }),
      });

      const result = await response.json();

      if (result.success) {
        alert('Contato cadastrado com sucesso!');
        // Redireciona ou limpa o formulário
        setName('');
        setPhone('');
        setEmail('');
        setProfilePicture('');
      } else {
        alert('Erro ao cadastrar contato: ' + result.error);
      }
    } catch (error) {
      console.error('Erro ao cadastrar contato:', error);
      alert("Erro ao cadastrar contato. Tente novamente.", error);
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Cadastrar Contato</h1>
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
              border: "1px solid #ddd",
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
              border: "1px solid #ddd",
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
              border: "1px solid #ddd",
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
              border: "1px solid #ddd",
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
              border: "1px solid #ddd",
            }}
          />
        </div>
        <button
          type="submit"
          style={{
            padding: "10px 20px",
            backgroundColor: "#b9aac2",
            color: "white",
            border: "none",
            borderRadius: "4px",
            cursor: "pointer",
          }}
        >
          Cadastrar
        </button>
      </form>
    </div>
  );
}
