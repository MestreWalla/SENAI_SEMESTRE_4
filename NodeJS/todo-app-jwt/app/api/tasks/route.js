import { getTask, createTask } from "@/controllers/TaskController";
import { NextResponse } from "next/server";

export async function GET() {
  try {
    const tasks = await getTask();
    return NextResponse.json({ success: true, data: tasks }, { status: 200 }); // Adicionado status 200
  } catch (error) {
    console.error("Error fetching tasks:", error);
    return NextResponse.json(
      { message: "Ocorreu um erro ao buscar as tarefas", error: error.message },
      { status: 500 }
    );
  }
}

export async function POST(req) {
  try {
    // Corrigido: invocando req.json()
    const data = await req.json();
    const task = await createTask(data);
    return NextResponse.json({ success: true, data: task }, { status: 201 }); // Adicionado status 201
  } catch (error) {
    console.error("Erro ao criar tarefa:", error);
    return NextResponse.json(
      { message: "Ocorreu um erro ao criar a tarefa" },
      { status: 500 }
    );
  }
}
