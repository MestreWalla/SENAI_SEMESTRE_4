// app/agenda/page.js
'use client';

import { useEffect } from 'react';

export default function Page() {
  useEffect(() => {
    // Criação do header
    const header = document.createElement('header');
    header.style.backgroundColor = '#b9aac2';
    header.style.color = 'white';
    header.style.padding = '10px';
    header.style.display = 'flex';
    header.style.position = 'fixed';
    header.style.justifyContent = 'space-between';
    header.style.alignItems = 'center';
    header.style.width = '100%';
    header.style.zIndex = '1000';

    const hambIcon = document.createElement('div');
    hambIcon.className = 'hamburguer';
    hambIcon.innerHTML = `
      <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24">
        <g fill="none" stroke="currentColor" stroke-dasharray="16" stroke-dashoffset="16" stroke-linecap="round" stroke-linejoin="round" stroke-width="2">
          <path d="M5 5h14">
            <animate fill="freeze" attributeName="stroke-dashoffset" dur="0.2s" values="16;0" />
          </path>
          <path d="M5 12h14">
            <animate fill="freeze" attributeName="stroke-dashoffset" begin="0.2s" dur="0.2s" values="16;0" />
          </path>
          <path d="M5 19h14">
            <animate fill="freeze" attributeName="stroke-dashoffset" begin="0.4s" dur="0.2s" values="16;0" />
          </path>
        </g>
      </svg>
    `;

    const searchContainer = document.createElement('div');
    searchContainer.className = 'search-container';
    searchContainer.style.display = 'flex';
    searchContainer.style.alignItems = 'center';
    searchContainer.style.background = 'white';
    searchContainer.style.borderRadius = '5px';
    searchContainer.style.overflow = 'hidden';
    searchContainer.style.width = '200px';

    const searchInput = document.createElement('input');
    searchInput.type = 'text';
    searchInput.className = 'search-input';
    searchInput.placeholder = 'Search...';
    searchInput.style.border = '1px solid #ddd';
    searchInput.style.padding = '5px';
    searchInput.style.width = '100%';
    searchInput.style.borderRadius = '5px';
    searchInput.style.outline = 'none';

    const searchIcon = document.createElement('svg');
    searchIcon.className = 'search-icon';
    searchIcon.setAttribute('xmlns', 'http://www.w3.org/2000/svg');
    searchIcon.setAttribute('width', '1em');
    searchIcon.setAttribute('height', '1em');
    searchIcon.setAttribute('viewBox', '0 0 24 24');
    searchIcon.innerHTML = `
      <g fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2">
        <path stroke-dasharray="40" stroke-dashoffset="40" d="M10.76 13.24c-2.34 -2.34 -2.34 -6.14 0 -8.49c2.34 -2.34 6.14 -2.34 8.49 0c2.34 2.34 2.34 6.14 0 8.49c-2.34 2.34 -6.14 2.34 -8.49 0Z">
          <animate fill="freeze" attributeName="stroke-dashoffset" dur="0.5s" values="40;0" />
        </path>
        <path stroke-dasharray="12" stroke-dashoffset="12" d="M10.5 13.5l-7.5 7.5">
          <animate fill="freeze" attributeName="stroke-dashoffset" begin="0.5s" dur="0.2s" values="12;0" />
        </path>
      </g>
    `;

    searchContainer.appendChild(searchInput);
    searchContainer.appendChild(searchIcon);

    header.appendChild(hambIcon);
    header.appendChild(searchContainer);

    // Criação do sidebar
    const sidebar = document.createElement('aside');
    sidebar.style.width = '225px';
    sidebar.style.backgroundColor = '#b9aac2';
    sidebar.style.padding = '15px';
    sidebar.style.boxShadow = '2px 0 5px rgba(0, 0, 0, 0.1)';
    sidebar.style.marginTop = '50px';
    sidebar.style.position = 'fixed';
    sidebar.style.height = 'calc(100vh - 50px)';

    const addButton = document.createElement('button');
    addButton.className = 'add';
    addButton.style.display = 'flex';
    addButton.style.gap = '10px';
    addButton.style.marginBottom = '10px';
    addButton.style.backgroundColor = '#fbf2f2';
    addButton.style.color = 'black';
    addButton.style.border = 'none';
    addButton.style.padding = '10px';
    addButton.style.width = '100%';
    addButton.style.textAlign = 'center';
    addButton.style.cursor = 'pointer';
    addButton.style.fontSize = '20px';
    addButton.style.alignItems = 'center';
    addButton.innerHTML = `
      <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24">
        <g fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2">
          <path stroke-dasharray="64" stroke-dashoffset="64" d="M3 12c0 -4.97 4.03 -9 9 -9c4.97 0 9 4.03 9 9c0 4.97 -4.03 9 -9 9c-4.97 0 -9 -4.03 -9 -9Z">
            <animate fill="freeze" attributeName="stroke-dashoffset" dur="0.6s" values="64;0" />
          </path>
          <path stroke-dasharray="12" stroke-dashoffset="12" d="M7 12h10">
            <animate fill="freeze" attributeName="stroke-dashoffset" begin="0.6s" dur="0.2s" values="12;0" />
          </path>
          <path stroke-dasharray="12" stroke-dashoffset="12" d="M12 7v10">
            <animate fill="freeze" attributeName="stroke-dashoffset" begin="0.8s" dur="0.2s" values="12;0" />
          </path>
        </g>
      </svg>
      Adicionar
    `;

    const contatosButton = document.createElement('button');
    contatosButton.className = 'contatos';
    contatosButton.style.display = 'flex';
    contatosButton.style.gap = '10px';
    contatosButton.style.marginBottom = '10px';
    contatosButton.style.backgroundColor = '#fbf2f2';
    contatosButton.style.color = 'black';
    contatosButton.style.border = 'none';
    contatosButton.style.padding = '10px';
    contatosButton.style.width = '100%';
    contatosButton.style.textAlign = 'center';
    contatosButton.style.cursor = 'pointer';
    contatosButton.innerHTML = `
      <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24">
        <g fill="none" stroke="currentColor" stroke-dasharray="32" stroke-dashoffset="32" stroke-linecap="round" stroke-linejoin="round" stroke-width="2">
          <path d="M4 21v-1c0 -3.31 2.69 -6 6 -6h4c3.31 0 6 2.69 6 6v1">
            <animate fill="freeze" attributeName="stroke-dashoffset" dur="0.4s" values="32;0" />
          </path>
          <path d="M12 11c-2.21 0 -4 -1.79 -4 -4c0 -2.21 1.79 -4 4 -4s4 1.79 4 4c0 2.21 -1.79 4 -4 4Z">
            <animate fill="freeze" attributeName="stroke-dashoffset" begin="0.4s" dur="0.4s" values="32;0" />
          </path>
        </g>
      </svg>
      Contatos
    `;

    const groups = document.createElement('div');
    groups.className = 'groups';
    groups.style.display = 'flex';
    groups.style.flexDirection = 'column';
    groups.style.gap = '10px';

    sidebar.appendChild(addButton);
    sidebar.appendChild(contatosButton);
    sidebar.appendChild(groups);

    // Criação do container de contatos
    const contactsContainer = document.createElement('div');
    contactsContainer.id = 'contactsContainer';
    contactsContainer.style.marginLeft = '250px'; // Ajuste conforme necessário
    contactsContainer.style.padding = '20px';

    // Adicionando os elementos ao body
    document.body.appendChild(header);
    document.body.appendChild(sidebar);
    document.body.appendChild(contactsContainer);

    // Função para obter e exibir grupos
    async function fetchGroups() {
      try {
        const response = await fetch('/api/groups');
        const data = await response.json();

        if (data.success && data.groups) {
          const groupsList = document.querySelector('.groups');
          groupsList.innerHTML = '<p>Grupos</p>'; // Clear existing content
          data.groups.forEach(group => {
            const button = document.createElement('button');
            button.style.display = 'flex';
            button.style.flexDirection = 'column';
            button.style.alignItems = 'center';
            button.style.gap = '10px';
            button.style.padding = '10px';
            button.style.backgroundColor = '#fbf2f2';
            button.style.border = 'none';
            button.style.cursor = 'pointer';
            button.innerHTML = `
              <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24">
                <!-- SVG content -->
              </svg>
              <p>${group.name}</p>
              <p>${group.members} Members</p>
            `;
            button.addEventListener('click', () => {
              // Handle group button click, maybe redirect to group details
              window.location.href = `/groups/${group.id}`;
            });
            groupsList.appendChild(button);
          });
        } else {
          console.error('Failed to fetch groups:', data.error);
        }
      } catch (error) {
        console.error('Error fetching groups:', error);
      }
    }

    // Função para obter e exibir contatos
    async function fetchContacts() {
      try {
        const response = await fetch('/api/contacts');
        const data = await response.json();

        const contactsContainer = document.getElementById('contactsContainer');
        contactsContainer.innerHTML = ''; // Clear existing content

        if (data.success && data.contacts && data.contacts.length > 0) {
          data.contacts.forEach(contact => {
            const card = document.createElement('div');
            card.className = 'card';
            card.style.backgroundColor = 'white';
            card.style.border = '1px solid #ddd';
            card.style.borderRadius = '5px';
            card.style.padding = '10px';
            card.style.marginBottom = '10px';
            card.style.boxShadow = '0 2px 4px rgba(0, 0, 0, 0.1)';

            card.innerHTML = `
              <img src="${contact.profilePicture || 'https://via.placeholder.com/300'}" alt="Profile Picture" style="width: 100%; height: auto; border-radius: 50%;">
              <p class="name" style="font-weight: bold;">${contact.name}</p>
              <p class="number">${contact.phone}</p>
              <p class="email">${contact.email}</p>
              <div class="actions" style="display: flex; justify-content: space-between;">
                <a href="#">
                  <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 24 24">
                    <!-- SVG content -->
                  </svg>
                </a>
                <button class="options" style="border: none; background: none; cursor: pointer;">
                  <svg xmlns="http://www.w3.org/2000/svg" width="0.44em" height="1em" viewBox="0 0 7 16">
                    <!-- SVG content -->
                  </svg>
                  <div class="dropdown-menu" style="display: none; position: absolute; background: white; border: 1px solid #ddd; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);">
                    <a href="#">WhatsApp</a>
                    <a href="#">Share</a>
                    <a href="#">Print</a>
                    <a href="#">Edit</a>
                    <a href="#">Delete</a>
                  </div>
                </button>
              </div>
            `;
            contactsContainer.appendChild(card);
          });
        } else {
          contactsContainer.innerHTML = '<p>Nada cadastrado</p>';
        }
      } catch (error) {
        console.error('Error fetching contacts:', error);
      }
    }

    // Inicializar a página
    fetchGroups();
    fetchContacts();

    // Lidar com o clique do botão de adicionar grupo
    document.querySelector('.add').addEventListener('click', () => {
      window.location.href = '/add-group';
    });
  }, []);

  return null; // O componente não renderiza nada, apenas manipula o DOM diretamente
}
