import { updateTask, deleteTask } from "@/controllers/TaskController";
import { NextResponse } from "next/server";

export async function POST(request, {params}) {
    try {
        const data = await request.json();
        const task = await updateTask(params.id, data);
        if (!task) {
            return NextResponse.json({ success: false, message: "Tarefa não encontrada" }, { status: 404 });
        }
        return NextResponse.json({ success: true, message: "Tarefa atualizada com sucesso!", data: task });
    } catch (error) {
        console.error("Erro ao atualizar tarefa:", error);
        return NextResponse.json({ success: false, message: "Erro ao atualizar tarefa", error: error.message }, { status: 500 }); 
    }
}

export async function DELETE(req, {params}) {
    try {
        const task = await deleteTask(params.id);
        if (task) {
            return NextResponse.json({ success: false, message: "Tarefa não encontrada" }, { status: 404 });
        }
        return NextResponse.json({ success: true, message: "Tarefa deletada com sucesso!" });
    } catch (error) {
        console.error("Erro ao deletar tarefa:", error);
        return NextResponse.json({ success: false, message: "Erro ao deletar tarefa", error: error.message }, { status: 400 }); 
    }
}