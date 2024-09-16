"use client";

import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import axios from "axios";

const ContactList = () => {
  const [contacts, setContacts] = useState([]);
  const router = useRouter();

  useEffect(() => {
    // Função para buscar contatos do banco de dados
    const fetchContacts = async () => {
      try {
        const response = await fetch("/api/contacts");
        const result = await response.json();

        if (result.success) {
          setContacts(result.data);
        } else {
          console.error("Erro ao buscar contatos:", result.message);
        }
      } catch (error) {
        console.error("Erro ao buscar contatos:", error);
      }
    };

    fetchContacts();
  }, []);

  const handleEdit = (id) => {
    router.push(`/edit/${id}`);
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`/api/contacts/${id}`);
      setContacts(contacts.filter((contact) => contact._id !== id));
    } catch (error) {
      console.error("Erro ao excluir contato:", error);
    }
  };

  const handleAddContact = () => {
    router.push("/agenda/add-contact");
  };

  const getImageSrc = (url) => {
    const imageUrl = typeof url === "string" ? url.trim() : "";
    return imageUrl !== ""
      ? imageUrl
      : "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3lUXoW_2yUPKkKpFEVGM04gsRowd0vCyXew&s";
  };

  return (
    <div>
      <header>
        <div className="hamburguer">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="1em"
            height="1em"
            viewBox="0 0 24 24"
          >
            <g
              fill="none"
              stroke="currentColor"
              stroke-dasharray="16"
              stroke-dashoffset="16"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
            >
              <path d="M5 5h14">
                <animate
                  fill="freeze"
                  attributeName="stroke-dashoffset"
                  dur="0.2s"
                  values="16;0"
                />
              </path>
              <path d="M5 12h14">
                <animate
                  fill="freeze"
                  attributeName="stroke-dashoffset"
                  begin="0.2s"
                  dur="0.2s"
                  values="16;0"
                />
              </path>
              <path d="M5 19h14">
                <animate
                  fill="freeze"
                  attributeName="stroke-dashoffset"
                  begin="0.4s"
                  dur="0.2s"
                  values="16;0"
                />
              </path>
            </g>
          </svg>
        </div>
        <div className="search-container">
          <input type="text" className="search-input" placeholder="Search..." />
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="1em"
            height="1em"
            viewBox="0 0 24 24"
          >
            <g
              fill="none"
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
            >
              <path
                stroke-dasharray="40"
                stroke-dashoffset="40"
                d="M10.76 13.24c-2.34 -2.34 -2.34 -6.14 0 -8.49c2.34 -2.34 6.14 -2.34 8.49 0c2.34 2.34 2.34 6.14 0 8.49c-2.34 2.34 -6.14 2.34 -8.49 0Z"
              >
                <animate
                  fill="freeze"
                  attributeName="stroke-dashoffset"
                  dur="0.5s"
                  values="40;0"
                />
              </path>
              <path
                stroke-dasharray="12"
                stroke-dashoffset="12"
                d="M10.5 13.5l-7.5 7.5"
              >
                <animate
                  fill="freeze"
                  attributeName="stroke-dashoffset"
                  begin="0.5s"
                  dur="0.2s"
                  values="12;0"
                />
              </path>
            </g>
          </svg>
        </div>
      </header>
      <div className="container">
        <aside>
          <button onClick={handleAddContact} className="add">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="1em"
              height="1em"
              viewBox="0 0 24 24"
            >
              <g
                fill="none"
                stroke="currentColor"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
              >
                <path
                  stroke-dasharray="64"
                  stroke-dashoffset="64"
                  d="M3 12c0 -4.97 4.03 -9 9 -9c4.97 0 9 4.03 9 9c0 4.97 -4.03 9 -9 9c-4.97 0 -9 -4.03 -9 -9Z"
                >
                  <animate
                    fill="freeze"
                    attributeName="stroke-dashoffset"
                    dur="0.6s"
                    values="64;0"
                  />
                </path>
                <path stroke-dasharray="12" stroke-dashoffset="12" d="M7 12h10">
                  <animate
                    fill="freeze"
                    attributeName="stroke-dashoffset"
                    begin="0.6s"
                    dur="0.2s"
                    values="12;0"
                  />
                </path>
                <path stroke-dasharray="12" stroke-dashoffset="12" d="M12 7v10">
                  <animate
                    fill="freeze"
                    attributeName="stroke-dashoffset"
                    begin="0.8s"
                    dur="0.2s"
                    values="12;0"
                  />
                </path>
              </g>
            </svg>
            Adicionar
          </button>
          <button className="contatos">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="1em"
              height="1em"
              viewBox="0 0 24 24"
            >
              <g
                fill="none"
                stroke="currentColor"
                stroke-dasharray="32"
                stroke-dashoffset="32"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
              >
                <path d="M4 21v-1c0 -3.31 2.69 -6 6 -6h4c3.31 0 6 2.69 6 6v1">
                  <animate
                    fill="freeze"
                    attributeName="stroke-dashoffset"
                    dur="0.4s"
                    values="32;0"
                  />
                </path>
                <path d="M12 11c-2.21 0 -4 -1.79 -4 -4c0 -2.21 1.79 -4 4 -4c2.21 0 4 1.79 4 4c0 2.21 -1.79 4 -4 4Z">
                  <animate
                    fill="freeze"
                    attributeName="stroke-dashoffset"
                    begin="0.4s"
                    dur="0.4s"
                    values="32;0"
                  />
                </path>
              </g>
            </svg>
            Contatos
          </button>
          <div className="groups">
            <p>Grupos</p>
            <button>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="1em"
                height="1em"
                viewBox="0 0 24 24"
              >
                <path
                  fill="none"
                  stroke="currentColor"
                  stroke-dasharray="56"
                  stroke-dashoffset="56"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M5 4h14l-5 6.5v9.5l-4 -4v-5.5Z"
                >
                  <animate
                    fill="freeze"
                    attributeName="stroke-dashoffset"
                    dur="0.6s"
                    values="56;0"
                  />
                </path>
              </svg>
              <p>Group 01</p>
              <p>123 Members</p>
            </button>
            <button>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="1em"
                height="1em"
                viewBox="0 0 24 24"
              >
                <path
                  fill="none"
                  stroke="currentColor"
                  stroke-dasharray="56"
                  stroke-dashoffset="56"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M5 4h14l-5 6.5v9.5l-4 -4v-5.5Z"
                >
                  <animate
                    fill="freeze"
                    attributeName="stroke-dashoffset"
                    dur="0.6s"
                    values="56;0"
                  />
                </path>
              </svg>
              <p>Group 02</p>
              <p>45 Members</p>
            </button>
          </div>
        </aside>

        <main>
          <div className="contact-list">
            {contacts.length > 0 ? (
              contacts.map((contact) => (
                <div key={contact._id} className="contact-item">
                  <img
                    src={getImageSrc(contact.photoURL)}
                    alt={contact.name || "Profile Picture"}
                    className="contact-photo"
                  />

                  <div className="contact-details">
                    <p className="contact-name">{contact.name}</p>
                    <p className="contact-email">{contact.email}</p>
                    <p className="contact-phone">{contact.phone}</p>
                  </div>
                  <div className="contact-actions">
                    <button
                      onClick={() => handleEdit(contact._id)}
                      className="edit-button"
                    >
                      Editar
                    </button>
                    <button
                      onClick={() => handleDelete(contact._id)}
                      className="delete-button"
                    >
                      Excluir
                    </button>
                  </div>
                </div>
              ))
            ) : (
              <p>Nenhum contato encontrado.</p>
            )}
          </div>
        </main>
      </div>
      <style jsx>{`
        .container {
          display: flex;
          flex-direction: row;
          height: 100vh;
          overflow-y: auto;
        }

        header {
          display: flex;
          align-items: center;
          justify-content: space-between;
          padding: 1rem;
          background: #f0f0f0;
        }

        main {
          flex: 1;
        }

        .hamburguer {
          cursor: pointer;
        }

        .search-container {
          display: flex;
          align-items: center;
        }

        .search-input {
          padding: 0.5rem;
          margin-right: 0.5rem;
          border: 1px solid #ccc;
          border-radius: 4px;
        }

        aside {
          display: flex;
          flex-direction: column;
          padding: 1rem;
          background: #f9f9f9;
          border-right: 1px solid #ddd;
        }

        .add,
        .contatos {
          display: flex;
          align-items: center;
          padding: 0.5rem;
          margin-bottom: 1rem;
          background: #e0e0e0;
          border: none;
          border-radius: 4px;
          cursor: pointer;
          font-size: 1rem;
        }

        .add svg,
        .contatos svg {
          margin-right: 0.5rem;
        }

        .groups {
          margin-top: 1rem;
        }

        .groups button {
          display: flex;
          flex-direction: column;
          align-items: center;
          background: #e0e0e0;
          border: none;
          border-radius: 4px;
          padding: 0.5rem;
          margin-bottom: 1rem;
          cursor: pointer;
        }

        .contact-list {
          display: flex;
          flex-direction: column;
          padding: 1rem;
          width: 100%;
        }

        .contact-item {
          display: flex;
          align-items: center;
          width: 100%;
          margin-bottom: 1rem;
          padding: 0.5rem;
          background: #fff;
          border: 1px solid #ddd;
          border-radius: 4px;
        }

        .contact-photo {
          width: 50px;
          height: 50px;
          border-radius: 50%;
          margin-right: 1rem;
        }

        .contact-details {
          flex-grow: 1;
        }

        .contact-name {
          font-weight: bold;
        }

        .contact-actions {
          display: flex;
          gap: 0.5rem;
        }

        .edit-button,
        .delete-button {
          padding: 0.5rem;
          border: none;
          border-radius: 4px;
          cursor: pointer;
          color: #fff;
        }

        .edit-button {
          background: #4caf50;
        }

        .delete-button {
          background: #f44336;
        }
      `}</style>
    </div>
  );
};

export default ContactList;
