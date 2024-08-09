@extends('layouts.master')

@section('content')
    <div class="container">
        <h1 class="my-4">Vagas</h1>

        @if ($message = Session::get('success'))
            <div class="alert alert-success">
                <p>{{ $message }}</p>
            </div>
        @endif

        <a class="btn btn-success mb-2" href="{{ route('vagas.create') }}">Criar Nova vaga</a>

        <table class="table table-bordered">
            <tr>
                <th>Nº</th>
                <th>Titulo</th>
                <th>Setor</th>
                <th>Remuneração</th>
                <th>Descrição</th>
                <th>Empresa</th>
                <th width="280px">Ação</th>
            </tr>
            @foreach ($vagas as $vaga)
                <tr>
                    <td>{{ $loop->iteration }}</td>
                    <td>{{ $vaga->titulo }}</td>
                    <td>{{ $vaga->setor }}</td>
                    <td>{{ $vaga->remuneracao }}</td>
                    <td>{{ $vaga->descricao }}</td>
                    <td>{{ $vaga->empresa }}</td>
                    <td>
                        <form action="{{ route('vagas.destroy', $vaga->id) }}" method="POST">
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <a class="btn btn-info" href="{{ route('vagas.show', $vaga->id) }}">
                                    <i class="bi bi-view-list"></i>
                                    Mostrar
                                </a>
                                <a class="btn btn-primary" href="{{ route('vagas.edit', $vaga->id) }}">
                                    <i class="bi bi-pencil"></i>
                                    Editar
                                </a>
                                @csrf
                                @method('DELETE')
                                <button type="submit" class="btn btn-danger">
                                    <i class="bi bi-trash"></i>
                                    Deletar
                                </button>
                            </div>
                        </form>
                    </td>
                </tr>
            @endforeach
        </table>
    </div>
