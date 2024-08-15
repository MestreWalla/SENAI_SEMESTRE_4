@extends('layouts.app')


@section('content')
    <div class="container">
        <h1 class="my-4">Editar Produto</h1>


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


        <form action="{{ route('produto.update', $produto->id) }}" method="POST">
            @csrf
            @method('PUT')

            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" name="nome" class="form-control" value="{{ $produto->name }}">
            </div>

            <div class="form-group">
                <label for="descricao">Descrição:</label>
                <textarea name="descricao" class="form-control">{{ $produto->description }}</textarea>
            </div>

            <div class="form-group">
                <label for="categoria">Categoria:</label>
                <input type="text" name="categoria" class="form-control" value="{{ $produto->category }}">
            </div>

            <div class="form-group">
                <label for="quantidade">Quantidade:</label>
                <input type="number" name="quantidade" class="form-control" value="{{ $produto->quantity }}">
            </div>

            <div class="form-group">
                <label for="preco">Preço:</label>
                <input type="text" name="preco" class="form-control" value="{{ $produto->price }}">
            </div>

            <button type="submit" class="btn btn-primary">Enviar</button>
        </form>
    </div>
@endsection
