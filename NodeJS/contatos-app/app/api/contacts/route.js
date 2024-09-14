// app/api/contacts/route.js

import { getContacts } from "@/controllers/contactsController";
import { NextResponse } from "next/server";

export async function GET() {
  try {
    const tasks = await getContacts();
    return NextResponse.json({ success: true, data: tasks }, { status: 200 }); // Adicionado status 200
  } catch (error) {
    console.error("Error fetching tasks:", error);
    return NextResponse.json(
      { message: "Ocorreu um erro ao buscar as contatos", error: error.message },
      { status: 500 }
    );
  }
}

