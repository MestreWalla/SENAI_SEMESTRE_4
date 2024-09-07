import Task from "@/models/Task";
import connectMongo from "@/utils/dbConnect";

// Função para obter todas as tarefas
export const getTask = async () => {
  await connectMongo();
  const tasks = await Task.find();
  return tasks;
};

// Função para obter uma tarefa por ID
export const getTaskById = async (id) => {
  await connectMongo();
  const task = await Task.findById(id);
  return task;
};

// Função para criar uma nova tarefa
export const createTask = async (data) => {
  await connectMongo();
  const newTask = new Task(data); // Cria uma nova instância do modelo Task
  await newTask.save(); // Salva a nova tarefa no banco de dados
  return newTask; // Retorna a nova tarefa criada
};


// Função para atualizar uma tarefa por ID
export const updateTask = async (id, data) => {
  await connectMongo();
  const updatedTask = await Task.findByIdAndUpdate(id, data, {
    new: true,
    runValidators: true, // Corrigido para 'runValidators'
  });
  return updatedTask;
};

// Função para deletar uma tarefa por ID
export const deleteTask = async (id) => {
  await connectMongo();
  const tarefaDeletada = await Task.deleteOne({ _id: id }); // Corrigido para usar '_id'
  console.log("Tarefa deletada:", tarefaDeletada);
};

// Função para buscar tarefas por termo de pesquisa
export const searchTasks = async (searchTerm) => {
  await connectMongo();
  const tasks = await Task.find({
    $text: { $search: searchTerm },
  });
  return tasks;
};
