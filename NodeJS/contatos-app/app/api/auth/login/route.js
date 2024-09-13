import User from "@/models/User";
import connectMongo from "@/utils/dbConnect";
import { NextResponse } from "next/server";
import jwt from "jsonwebtoken";

export async function POST(request) {
  const { username, password } = await request.json();
  await connectMongo();

  try {
    // Verificar se o usuário existe
    const user = await User.findOne({ username });
    if (!user) {
      return NextResponse.json(
        { success: false, message: "Usuário não encontrado" },
        { status: 400 }
      );
    }

    // Verificar se a senha está correta
    const isPasswordCorrect = await user.comparePassword(password);
    if (!isPasswordCorrect) {
      return NextResponse.json(
        { success: false, message: "Senha incorreta" },
        { status: 400 }
      );
    }

    // Gerar token
    const token = jwt.sign({ userId: user._id }, process.env.JWT_SECRET_KEY, {
      expiresIn: "1h",
    });

    return NextResponse.json({ success: true, token });
  } catch (error) {
    console.error("Erro ao processar login:", error);
    return NextResponse.json(
      { success: false, message: "Erro ao processar login" },
      { status: 500 }
    );
  }
}
