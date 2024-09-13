import React, { useState } from 'react';

const ContactForm = ({ onSubmit, initialData }) => {
  const [name, setName] = useState(initialData?.name || '');
  const [email, setEmail] = useState(initialData?.email || '');
  const [phone, setPhone] = useState(initialData?.phone || '');
  const [address, setAddress] = useState(initialData?.address || '');
  const [notes, setNotes] = useState(initialData?.notes || '');
  const [group, setGroup] = useState(initialData?.group || '');
  const [photoURL, setPhotoURL] = useState(initialData?.photoURL || []);
  const [tags, setTags] = useState(initialData?.tags || []);

  const handleSubmit = (e) => {
    e.preventDefault();
    const contactData = { name, email, phone, address, notes, group, photoURL, tags };
    onSubmit(contactData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="name">Name:</label>
        <input
          type="text"
          id="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
      </div>
      <div>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
      </div>
      <div>
        <label htmlFor="phone">Phone:</label>
        <input
          type="text"
          id="phone"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
          required
        />
      </div>
      <div>
        <label htmlFor="address">Address:</label>
        <input
          type="text"
          id="address"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
          required
        />
      </div>
      <div>
        <label htmlFor="notes">Notes:</label>
        <textarea
          id="notes"
          value={notes}
          onChange={(e) => setNotes(e.target.value)}
        />
      </div>
      <div>
        <label htmlFor="group">Group:</label>
        <input
          type="text"
          id="group"
          value={group}
          onChange={(e) => setGroup(e.target.value)}
        />
      </div>
      <div>
        <label htmlFor="photoURL">Photo URL:</label>
        <input
          type="text"
          id="photoURL"
          value={photoURL}
          onChange={(e) => setPhotoURL([e.target.value])}
        />
      </div>
      <div>
        <label htmlFor="tags">Tags:</label>
        <input
          type="text"
          id="tags"
          value={tags}
          onChange={(e) => setTags(e.target.value.split(','))}
        />
      </div>
      <button type="submit">Submit</button>
    </form>
  );
};

export default ContactForm;
