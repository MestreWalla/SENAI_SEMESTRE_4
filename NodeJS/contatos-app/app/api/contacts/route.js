// app/api/contacts/route.js

import { getContacts } from "@/controllers/contactsController";
import { NextResponse } from "next/server";
import Contact from "@/models/Contact"; // Importe o modelo de contato
import connectMongo from "@/utils/dbConnect";
export async function GET() {
  try {
    const tasks = await getContacts();
    return NextResponse.json({ success: true, data: tasks }, { status: 200 });
  } catch (error) {
    console.error("Erro ao buscar contatos:", error);
    return NextResponse.json(
      {
        success: false,
        message: "Ocorreu um erro ao buscar os contatos",
        error: error.message,
      },
      { status: 500 }
    );
  }
}

// Função para criar um novo contato
export async function POST(request) {
  try {
    await connectMongo(); // Certifique-se de conectar ao MongoDB

    const data = await request.json();
    const newContact = new Contact(data); // Cria uma nova instância do modelo Contact

    await newContact.save(); // Salva o novo contato no banco de dados

    return NextResponse.json(
      {
        success: true,
        message: "Contato cadastrado com sucesso!",
        data: newContact,
      },
      { status: 201 }
    );
  } catch (error) {
    console.error("Erro ao cadastrar contato:", error);
    return NextResponse.json(
      {
        success: false,
        message: "Erro ao cadastrar contato",
        error: error.message,
      },
      { status: 500 }
    );
  }
}
