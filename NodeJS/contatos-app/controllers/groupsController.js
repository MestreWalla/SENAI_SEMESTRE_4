import Group from '../models/Group';
import { NextResponse } from 'next/server';

// Função para obter todos os grupos
export async function getGroups() {
  try {
    const groups = await Group.find({});
    return NextResponse.json({ success: true, data: groups }, { status: 200 });
  } catch (error) {
    return NextResponse.json({ success: false, error: error.message }, { status: 400 });
  }
}

// Função para criar um novo grupo
export async function createGroup(req) {
  try {
    const data = await req.json(); // Certifique-se de que está usando req.json() para extrair o body no Next.js
    const group = await Group.create(data);
    return NextResponse.json({ success: true, data: group }, { status: 201 });
  } catch (error) {
    return NextResponse.json({ success: false, error: error.message }, { status: 400 });
  }
}

// Função para obter grupo por ID
export async function getGroupById(req) {
  const { id } = req.query;

  try {
    const group = await Group.findById(id);
    if (!group) {
      return NextResponse.json({ success: false, message: 'Group not found' }, { status: 404 });
    }
    return NextResponse.json({ success: true, data: group }, { status: 200 });
  } catch (error) {
    return NextResponse.json({ success: false, error: error.message }, { status: 400 });
  }
}

// Função para atualizar grupo
export async function updateGroup(req) {
  const { id } = req.query;
  try {
    const data = await req.json(); // Extraindo o body
    const group = await Group.findByIdAndUpdate(id, data, {
      new: true,
      runValidators: true,
    });
    if (!group) {
      return NextResponse.json({ success: false, message: 'Group not found' }, { status: 404 });
    }
    return NextResponse.json({ success: true, data: group }, { status: 200 });
  } catch (error) {
    return NextResponse.json({ success: false, error: error.message }, { status: 400 });
  }
}

// Função para deletar grupo
export async function deleteGroup(req) {
  const { id } = req.query;

  try {
    const deletedGroup = await Group.deleteOne({ _id: id });
    if (!deletedGroup.deletedCount) {
      return NextResponse.json({ success: false, message: 'Group not found' }, { status: 404 });
    }
    return NextResponse.json({ success: true, data: {} }, { status: 200 });
  } catch (error) {
    return NextResponse.json({ success: false, error: error.message }, { status: 400 });
  }
}
