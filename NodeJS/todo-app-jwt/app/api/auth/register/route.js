// Importações necessárias
import User from "@/models/User";
import connectMongo from "@/utils/dbConnect";
import { NextResponse } from "next/server";

// Função assíncrona para criar um novo usuário
export async function POST(request) {
  const data = await request.json();
  console.log("Dados recebidos:", data); // Verifica se os dados estão corretos

  await connectMongo(); // Conecta ao MongoDB

  try {
    const user = await User.create(data); // Tenta criar o usuário
    return NextResponse.json({
      success: true,
      message: "Usuário criado com sucesso!",
      data: user,
    });
  } catch (error) {
    console.error("Erro ao criar usuário:", error); // Log de erro mais detalhado
    return NextResponse.json(
      {
        success: false,
        message: "Erro ao criar usuário.",
        error: error.message,
      }, // Envia o erro detalhado na resposta
      { status: 500 }
    );
  }
}
