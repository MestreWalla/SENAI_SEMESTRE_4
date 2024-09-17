"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import axios from "axios";

export default function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const router = useRouter();

  const handleLogin = async () => {
    try {
      const response = await axios.post("/api/auth/login", {
        username,
        password,
      });

      if (response.data.success) {
        // Defina o cookie com o token retornado
        document.cookie = `auth-token=${response.data.token}; path=/; secure; samesite=strict;`;

        // Redirecione para a página desejada após login
        router.push("/agenda");
      } else {
        setError(response.data.message);
      }
    } catch (error) {
      setError("Erro ao fazer login. Por favor, tente novamente.");
      console.error("Erro ao fazer login:", error);
    }
  };

  return (
    <div className="corpo">
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
      </header>
      <main>
        <h1>Login</h1>
        {error && <p style={{ color: "red" }}>{error}</p>}
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button onClick={handleLogin}>Login</button>
        <p>
          Não tem uma conta? <a href="/register">Registre-se</a>
        </p>
      </main>
      <footer>
        <div className="footer-content">
          <p>Desenvolvido por [Maycon e Christopher]</p>
        </div>
      </footer>
      <style jsx>{`
        /* Estilos gerais para a página */
        .corpo {
          display: flex;
          flex-direction: column;
        }

        header {
          background: #f4f4f4;
          padding: 10px;
          display: flex;
          justify-content: space-between;
          align-items: center;
        }

        .hamburguer {
          cursor: pointer;
        }

        .search-container {
          display: flex;
          align-items: center;
        }

        .search-input {
          padding: 8px;
          margin-right: 10px;
        }

        main {
          flex: 1;
          padding: 20px;
        }

        footer {
          background: #f4f4f4;
          padding: 10px;
          text-align: center;
        }

        input {
          display: block;
          margin: 10px 0;
          padding: 8px;
          width: 100%;
          box-sizing: border-box;
        }

        button {
          padding: 10px;
          background: #0070f3;
          color: white;
          border: none;
          cursor: pointer;
        }

        button:hover {
          background: #005bb5;
        }

        p {
          margin: 10px 0;
        }

        a {
          color: #0070f3;
        }

        a:hover {
          text-decoration: underline;
        }
      `}</style>
    </div>
  );
}
