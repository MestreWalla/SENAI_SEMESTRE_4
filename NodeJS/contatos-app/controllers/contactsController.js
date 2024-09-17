// controllers/contactsController.js

import Contact from "@/models/Contact"; // Certifique-se de que o caminho está correto
import connectMongo from "@/utils/dbConnect";
import Group from "@/models/Group";

// Função para buscar todos os contatos
// export async function getContacts() {
//   try {
//     const contacts = await Contact.find().populate("group");
//     return contacts;
//   } catch (error) {
//     console.error("Erro ao buscar contatos:", error);
//     throw new Error(`Erro ao buscar contatos: ${error.message}`);
//   }
// }

export const getContacts = async () => {
  await connectMongo();
  const contacts = await Contact.find();
  return contacts;
};

// Função para buscar um contato por ID
export async function getContactById(id) {
  await connectMongo();
  try {
    const contact = await Contact.findById(id).populate("group");
    if (!contact) {
      throw new Error("Contato não encontrado");
    }
    return contact;
  } catch (error) {
    throw new Error(`Erro ao buscar contato por ID: ${error.message}`);
  }
}

// Função para atualizar um contato
export async function updateContact(id, data) {
  await connectMongo();
  try {
    const contact = await Contact.findByIdAndUpdate(id, data, {
      new: true,
    }).populate("group");
    if (!contact) {
      throw new Error("Contato não encontrado");
    }
    return contact;
  } catch (error) {
    throw new Error(`Erro ao atualizar contato: ${error.message}`);
  }
}


// Função para deletar um contato
export async function deleteContact(id) {
  try {
    // Deleta o contato pelo ID e retorna o contato deletado
    const contact = await Contact.findByIdAndDelete(id);
    return contact;
  } catch (error) {
    throw new Error(`Erro ao deletar contato: ${error.message}`);
  }
}
