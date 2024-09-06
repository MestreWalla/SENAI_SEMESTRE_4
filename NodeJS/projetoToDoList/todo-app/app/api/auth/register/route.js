// Importações necessárias
import User from "@/models/User";
import connectMongo from "@/utils/dbConnect";
import { NextResponse } from "next/server";

// Função assíncrona para criar um novo usuário
export async function POST(request) {
  // Obtém os dados do corpo da requisição
  const data = await request.json();
  // Conecta ao banco de dados
  await connectMongo();
  try {
    const user = await User.create(data);
    // Retorna uma resposta com sucesso
    return NextResponse.json({
      success: true,
      message: "Usuário criado com sucesso!",
      data: user,
    });
  } catch (error) {
    // Retorna uma resposta com erro
    return NextResponse.json(
      { success: false, message: "Erro ao criar usuário." },
      { status: 500 }
    );
  }
}
