import { updateTodo, deleteTodo } from "@/controller/TodoController";
import { NextResponse } from "next/server";

export async function PUT(req, { params }) {
  try {
    const data = await req.json();
    const todo = await updateTodo(params.id, data);
    if (!todo) {
      return NextResponse.json(
        { success: false, message: "Tarefa não encontrada" },
        { status: 404 }
      );
    }
    return NextResponse.json({ success: true, data: todo });
  } catch (error) {
    console.error(error);
    return NextResponse.json(
      { success: false, message: "Ocorreu um erro" },
      { status: 500 }
    );
  }
}

export async function DELETE(req, { params }) {
  try {
    const todo = await deleteTodo(params.id);
    if (!todo) {
      return NextResponse.json(
        { success: false, message: "Tarefa não encontrada" },
        { status: 404 }
      );
    }
    return NextResponse.json({
      success: true,
      message: "Tarefa excluída com sucesso",
    });
  } catch (error) {
    console.error(error);
    return NextResponse.json(
      { success: false, message: "Ocorreu um erro" },
      { status: 500 }
    );
  }
}
