import mongoose from "mongoose";

const TodoSchema = new mongoose.Schema({
    title: { type: String, required: true },
    description: String,
    completed: { type: Enum('A fazer', 'Fazendo', 'Concluido'), default: 'A fazer' },
    createdAt: { type: Date, default: Date.now },
    updatedAt: Date,
});

const Todo = mongoose.models.Todo || mongoose.model('Todo', TodoSchema);
export default Todo;