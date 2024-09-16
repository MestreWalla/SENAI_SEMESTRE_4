import mongoose from 'mongoose';

// Model para app de contatos
const ContactSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true,
  },
  email: {
    type: String,
    required: true,
    unique: true,
    match: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
  },
  phone: {
    type: String,
    required: true,
    unique: true,
    // match: /^\+\d{1,3}\s?\(?\d{1,4}\)?\s?\d{3,5}-?\d{4,5}$/,
  },
  address: {
    type: String,
    required: true,
  },
  notes: {
    type: String,
    required: false,
  },
  // Relacionamento com o grupo
  group: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Group',
    required: false,
  },
  // Array de URLs para fotos
  photoURL: [
    {
      type: String,
      required: false,
    },
  ],
  // Tags como array de strings
  tags: [
    {
      type: String,
      required: false,
    },
  ],
});

export default mongoose.models.Contact || mongoose.model('Contact', ContactSchema);
