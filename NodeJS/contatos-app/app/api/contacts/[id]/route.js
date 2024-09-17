// app/api/contacts/[id]/route.js

import {
  getContactById,
  updateContact,
  deleteContact,
} from "@/controllers/contactsController";
import { NextResponse } from "next/server";

export async function GET(request, { params }) {
  try {
    const contact = await getContactById(params.id);
    if (!contact) {
      return NextResponse.json(
        { success: false, message: "Contato não encontrado" },
        { status: 404 }
      );
    }
    return NextResponse.json({ success: true, data: contact }, { status: 200 });
  } catch (error) {
    console.error("Erro ao buscar contato:", error);
    return NextResponse.json(
      {
        success: false,
        message: "Erro ao buscar contato",
        error: error.message,
      },
      { status: 500 }
    );
  }
}

export async function POST(request, { params }) {
  try {
    const data = await request.json();
    const contact = await updateContact(params.id, data);
    if (!contact) {
      return NextResponse.json(
        { success: false, message: "Contato não encontrado" },
        { status: 404 }
      );
    }
    return NextResponse.json(
      {
        success: true,
        message: "Contato atualizado com sucesso!",
        data: contact,
      },
      { status: 200 }
    );
  } catch (error) {
    console.error("Erro ao atualizar contato:", error);
    return NextResponse.json(
      {
        success: false,
        message: "Erro ao atualizar contato",
        error: error.message,
      },
      { status: 500 }
    );
  }
}

export async function PUT(request, { params }) {
  try {
    const data = await request.json();
    const contact = await updateContact(params.id, data);
    if (!contact) {
      return NextResponse.json(
        { success: false, message: "Contato não encontrado" },
        { status: 404 }
      );
    }
    return NextResponse.json(
      {
        success: true,
        message: "Contato atualizado com sucesso!",
        data: contact,
      },
      { status: 200 }
    );
  } catch (error) {
    console.error("Erro ao atualizar contato:", error);
    return NextResponse.json(
      {
        success: false,
        message: "Erro ao atualizar contato",
        error: error.message,
      },
      { status: 500 }
    );
  }
}

export async function DELETE(request, { params }) {
  try {
    const contact = await deleteContact(params.id);
    if (!contact) {
      return NextResponse.json(
        { success: false, message: "Contato não encontrado" },
        { status: 404 }
      );
    }
    return NextResponse.json(
      { success: true, message: "Contato deletado com sucesso!" },
      { status: 200 }
    );
  } catch (error) {
    console.error("Erro ao deletar contato:", error);
    return NextResponse.json(
      {
        success: false,
        message: "Erro ao deletar contato",
        error: error.message,
      },
      { status: 500 }
    );
  }
}
