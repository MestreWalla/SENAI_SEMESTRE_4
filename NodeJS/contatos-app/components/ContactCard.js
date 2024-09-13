import React from 'react';

const ContactCard = ({ contact }) => {
  return (
    <div className="card">
      <img src={contact.photoURL[0] || '/default.jpg'} alt={contact.name} />
      <p className="name">{contact.name}</p>
      <p className="number">{contact.phone}</p>
      <p className="email">{contact.email}</p>
      <div className="actions">
        <button className="edit">Edit</button>
        <button className="options">
          <a href="#">Compartilhar</a>
          <a href="#">Imprimir</a>
          <a href="#">Editar</a>
          <a href="#">Excluir</a>
        </button>
      </div>
    </div>
  );
};

export default ContactCard;