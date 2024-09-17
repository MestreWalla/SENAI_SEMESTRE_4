"use client";

import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import axios from "axios";
import themes from "./themes";

const ContactList = () => {
  const [contacts, setContacts] = useState([]);
  const [currentThemeIndex, setCurrentThemeIndex] = useState(0);
  const router = useRouter();
  const [contactCount, setContactCount] = useState(0);

  useEffect(() => {
    const fetchContacts = async () => {
      try {
        const response = await fetch("/api/contacts");
        const result = await response.json();

        if (result.success) {
          setContacts(result.data);
          setContactCount(result.data.length);
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
    router.push(`/agenda/edit/${id}`);
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`/api/contacts/${id}`);
      setContacts((prevContacts) =>
        prevContacts.filter((contact) => contact._id !== id)
      );
    } catch (error) {
      console.error("Erro ao excluir contato:", error);
    }
  };

  const handleAddContact = () => {
    router.push("/agenda/add-contact");
  };

  const themeOptions = Object.keys(themes);

  const handleThemeChange = (event) => {
    const selectedThemeIndex = themeOptions.indexOf(event.target.value);
    setCurrentThemeIndex(selectedThemeIndex);
  };

  const getImageSrc = (url) => {
    return typeof url === "string" && url.trim() !== ""
      ? url
      : "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3lUXoW_2yUPKkKpFEVGM04gsRowd0vCyXew&s";
  };

  const currentTheme = themes[themeOptions[currentThemeIndex]];

  return (
    <div
      style={{ background: currentTheme.background, color: currentTheme.text }}
    >
      <header>
        <div className="hamburguer" aria-label="Menu">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="1em"
            height="1em"
            viewBox="0 0 24 24"
          >
            <g
              fill="none"
              stroke="currentColor"
              strokeDasharray="16"
              strokeDashoffset="16"
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth="2"
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
          <input
            type="text"
            className="search-input"
            placeholder="Search..."
            aria-label="Search contacts"
          />
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="1em"
            height="1em"
            viewBox="0 0 24 24"
          >
            <g
              fill="none"
              stroke="currentColor"
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth="2"
            >
              <path
                strokeDasharray="40"
                strokeDashoffset="40"
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
                strokeDasharray="12"
                strokeDashoffset="12"
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
        <select
          onChange={handleThemeChange}
          value={themeOptions[currentThemeIndex]}
          className="theme-select"
        >
          {themeOptions.map((theme) => (
            <option key={theme} value={theme}>
              {theme} Mode
            </option>
          ))}
        </select>
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
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
              >
                <path
                  strokeDasharray="64"
                  strokeDashoffset="64"
                  d="M3 12c0 -4.97 4.03 -9 9 -9c4.97 0 9 4.03 9 9c0 4.97 -4.03 9 -9 9c-4.97 0 -9 -4.03 -9 -9Z"
                >
                  <animate
                    fill="freeze"
                    attributeName="stroke-dashoffset"
                    dur="0.6s"
                    values="64;0"
                  />
                </path>
                <path strokeDasharray="12" strokeDashoffset="12" d="M7 12h10">
                  <animate
                    fill="freeze"
                    attributeName="stroke-dashoffset"
                    begin="0.6s"
                    dur="0.2s"
                    values="12;0"
                  />
                </path>
                <path strokeDasharray="12" strokeDashoffset="12" d="M12 7v10">
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
            Adicionar contato
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
                strokeDasharray="32"
                strokeDashoffset="32"
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
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
                    dur="0.2s"
                    values="32;0"
                  />
                </path>
              </g>
            </svg>
            Contatos ({contactCount}) {/* Exibe a contagem de contatos */}
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
                  strokeDasharray="56"
                  strokeDashoffset="56"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
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
              <p>Trabalho</p>
              <p>(5)</p>
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
                  strokeDasharray="56"
                  strokeDashoffset="56"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
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
              <p>Familia</p>
              <p>(8)</p>
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
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="1em"
                        height="1em"
                        viewBox="0 0 20 20"
                      >
                        <path
                          fill="currentColor"
                          d="m16.77 8l1.94-2a1 1 0 0 0 0-1.41l-3.34-3.3a1 1 0 0 0-1.41 0L12 3.23zM1 14.25V19h4.75l9.96-9.96l-4.75-4.75z"
                        />
                      </svg>
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
          background: ${currentTheme.background};
        }

        main {
          flex: 1;
          background: ${currentTheme.bodyBackground};
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
          border: 1px solid ${currentTheme.border};
          border-radius: 4px;
          background: ${currentTheme.background};
          color: ${currentTheme.text};
        }

        .theme-select {
          padding: 0.5rem;
          border: 1px solid ${currentTheme.border};
          border-radius: 4px;
          background: ${currentTheme.background};
          color: ${currentTheme.text};
          cursor: pointer;
        }

        aside {
          display: flex;
          flex-direction: column;
          padding: 1rem;
          background: ${currentTheme.background};
          border-right: 1px solid ${currentTheme.border};
        }

        .add,
        .contatos {
          display: flex;
          align-items: center;
          padding: 0.5rem;
          margin-bottom: 1rem;
          background: ${currentTheme.buttonBackground};
          border: none;
          border-radius: 4px;
          cursor: pointer;
          font-size: 1rem;
          color: ${currentTheme.buttonText};
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
          flex-direction: row;
          align-items: center;
          background: ${currentTheme.buttonBackground};
          border: none;
          border-radius: 4px;
          padding: 0.5rem;
          margin-bottom: 1rem;
          cursor: pointer;
          gap: 0.5rem;
          color: ${currentTheme.buttonText};
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
          background: ${currentTheme.contactBackground};
          border: 1px solid ${currentTheme.border};
          border-radius: 25px;
        }

        .contact-photo {
          width: 50px;
          height: 50px;
          border-radius: 15px;
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
          background: ${currentTheme.buttonEdit};
        }

        .delete-button {
          background: ${currentTheme.buttonDelete};
        }
      `}</style>
    </div>
  );
};

export default ContactList;
