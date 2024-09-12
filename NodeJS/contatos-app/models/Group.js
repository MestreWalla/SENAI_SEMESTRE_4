import mongoose from 'mongoose';

// Model para os grupos de contatos
const GroupSchema = new mongoose.Schema({
  name: {
    type: String,
    required: true,
    unique: true,
  },
  description: {
    type: String,
    required: false,
  },
});

export default mongoose.models.Group || mongoose.model('Group', GroupSchema);