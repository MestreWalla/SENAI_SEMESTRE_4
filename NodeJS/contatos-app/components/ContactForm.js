'use client'; // Marca este componente como Client Component

import { useState } from 'react';
import { useRouter } from 'next/navigation';

const ContactForm = ({ onSubmit }) => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');
  const [notes, setNotes] = useState('');

  const router = useRouter();

  const handleSubmit = async (event) => {
    event.preventDefault();
    const contactData = { name, email, phone, address, notes };
    
    try {
      const res = await fetch('/api/contacts', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(contactData),
      });

      if (res.ok) {
        router.push('/agenda'); // Redireciona para a página de agenda após o cadastro
      } else {
        console.error('Failed to create contact');
      }
    } catch (error) {
      console.error('An error occurred while creating the contact:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
        required
      />
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        required
      />
      <input
        type="text"
        placeholder="Phone"
        value={phone}
        onChange={(e) => setPhone(e.target.value)}
        required
      />
      <input
        type="text"
        placeholder="Address"
        value={address}
        onChange={(e) => setAddress(e.target.value)}
        required
      />
      <textarea
        placeholder="Notes"
        value={notes}
        onChange={(e) => setNotes(e.target.value)}
      />
      <button type="submit">Submit</button>
    </form>
  );
};

export default ContactForm;
