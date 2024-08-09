@extends('layouts.master')

<div class="container">
    <h1 class="my-4">Criar Vaga</h1>

    @if ($errors->any())
        <div class="alert alert-danger">
            <strong>Oops!</strong> Houve alguns problemas com sua entrada.<br><br>
            <ul>
                @foreach ($errors->all() as $error)
                    <li>{{ $error }}</li>
                @endforeach
            </ul>
        </div>
    @endif

    <form action="{{ route('vagas.store') }}" method="POST">
        @csrf

        <div class="form-group">
            <label for="titulo">Titulo:</label>
            <input type="text" name="titulo" class="form-control" placeholder="Titulo">
        </div>

        <div class="form-group">
            <label for="setor">Setor:</label>
            <textarea name="setor" class="form-control" placeholder="Setor"></textarea>
        </div>

        <div class="form-group">
            <label for="descricao">Descrição:</label>
            <textarea name="descricao" class="form-control" placeholder="Descrição"></textarea>
        </div>

        <div class="form-group">
            <label for="remuneracao">Remuneração:</label>
            <input type="text" name="remuneracao" class="form-control" placeholder="Remuneração">
        </div>

        <div class="form-group">
            <label for="empresa">Empresa:</label>
            <textarea name="empresa" class="form-control" placeholder="Empresa"></textarea>
        </div>

        <button type="submit" class="btn btn-primary">Enviar</button>
    </form>
</div>