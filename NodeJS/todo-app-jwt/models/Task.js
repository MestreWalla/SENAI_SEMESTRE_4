import mongoose from "mongoose";

// Definindo o schema para as tarefas (Task)
const TaskSchema = new mongoose.Schema({
  userId: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "User", // Relacionando com o modelo de User
    required: true,
  },
  title: {
    type: String,
    required: true,
  },
  completed: {
    type: Boolean,
    default: false,
  },
});

// Corrigindo o nome do modelo para "Task"
const Task = mongoose.models.Task || mongoose.model("Task", TaskSchema);

export default Task; // Exportando o modelo Task corretamente
