import Task from "@/models/Task";
import connectMongo from "@/utils/dbConnect";

// Crud

export const getTask = async () => {
  await connectMongo();
  const tasks = await Task.find();
  return tasks;
};

export const getTaskById = async (id) => {
  await connectMongo();
  const task = await Task.findById(id);
  return task;
};

export const createTask = async (data) => {
  await connectMongo();
  await task.create(data);
  return task;
};

export const updateTask = async (id, data) => {
  await connectMongo();
  const updatedTask = await Task.findByIdAndUpdate(id, data, {
    new: true,
    runValidation: true,
  });
  return updatedTask;
};

export const deleteTask = async (id) => {
  await connectMongo();
  const tarefaDeletada = await Task.deleteOne({ id: id });
  console.log("Tarefa deletada:", tarefaDeletada);
};

export const searchTasks = async (searchTerm) => {
  await connectMongo();
  const tasks = await Task.find({
    $text: { $search: searchTerm },
  });
  return tasks;
};