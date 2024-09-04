import Todo from "@/models/todo";
import connectMongo from "@/utils/dbConnect";

// Metodos Crud

// Read
export const getTodos = async (req, res) => {
  await connectMongo();
  try {
    return await Todo.find({});
  } catch (error) {
    console.error(error);
  }
};

// Read
// export const getTodos = async (req, res) => {
//   try {
//     await connectMongo();
//     const todos = await Todo.find({});
//     res.status(200).json(todos);
//   } catch (error) {
//     res.status(500).json({ error: error.message });
//   }
// };

// Create
export const createTodo = async (data) => {
  connectMongo();
  try {
    return await Todo.create(data);
  } catch (error) {
    console.error(error);
  }
};

// Create
// export const createTodo = async (req, res) => {
//   if (req.method === "POST") {
//     try {
//       await connectMongo();
//       const { title, description } = req.body;
//       const newTodo = new Todo({ title, description });
//       await newTodo.save();
//       res.status(201).json(newTodo);
//     } catch (error) {
//       res.status(500).json({ error: error.message });
//     }
//   } else {
//     res.status(405).json({ error: "Method not allowed" });
//   }
// };

// Update
export const updateTodo = async (id, data) => {
  await connectMongo();
  try {
    return await Todo.findByIdAndUpdate(id, data, {
      new: true,
      runValidators: true,
    });
  } catch (error) {
    console.error(error);
  }
};

// Update
// export const updateTodo = async (req, res) => {
//   if (req.method === "PUT") {
//     try {
//       await connectMongo();
//       const { id } = req.params;
//       const { title, description, completed } = req.body;
//       const updatedTodo = await Todo.findByIdAndUpdate(
//         id,
//         { title, description, completed },
//         { new: true }
//       );
//       if (!updatedTodo) {
//         return res.status(404).json({ error: "Todo not found" });
//       }
//       res.status(200).json(updatedTodo);
//     } catch (error) {
//       res.status(500).json({ error: error.message });
//     }
//   } else {
//     res.status(405).json({ error: "Method not allowed" });
//   }
// };

// Delete
export const deleteTodo = async (id) => {
  await connectMongo();
  try {
  return await Todo.deleteOne({ _id: id });
  } catch (error) {
    console.error(error);
  }
};

// Delete
// export const deleteTodo = async (req, res) => {
//   if (req.method === "DELETE") {
//     try {
//       await connectMongo();
//       const { id } = req.params;
//       const deletedTodo = await Todo.findByIdAndDelete(id);
//       if (!deletedTodo) {
//         return res.status(404).json({ error: "Todo not found" });
//       }
//       res.status(204).end();
//     } catch (error) {
//       res.status(500).json({ error: error.message });
//     }
//   } else {
//     res.status(405).json({ error: "Method not allowed" });
//   }
// };
