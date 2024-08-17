@extends('layouts.app')


@section('content')
    <div class="container">
        
        <h1 class="my-4">Produtos</h1>

        @if ($message = Session::get('success'))
            <div class="alert alert-success">
                <p>{{ $message }}</p>
            </div>
        @endif
        @if ($message = Session::get('error'))
            <div class="alert alert-warning">
                <p>{{ $message }}</p>
            </div>
        @endif

        <!-- Menu de Navegação Lateral -->
        @include('parts.sidebar')

        <a class="btn btn-success mb-2" href="{{ route('produto.create') }}"> Criar Novo Produto</a>

        <table class="table table-bordered">
            <tr>
                <th>No</th>
                <th>Imagem</th>
                <th>Nome</th>
                <th>Descrição</th>
                <th>Categoria</th>
                <th>Quantidade</th>
                <th>Preço</th>
                <th width="280px">Ação</th>
            </tr>
            @foreach ($produtos as $produto)
                <tr>
                    <td>{{ $loop->iteration }}</td>
                    <td>@if($produto->img_url)
                        <img src="{{ $produto->img_url }}" alt="{{ $produto->name }}" class="img-fluid">
                    @else
                        <p>Imagem não disponível</p>
                    @endif</td>
                    <td>{{ $produto->name }}</td>
                    <td>{{ $produto->description }}</td>
                    <td>{{ $produto->category }}</td>
                    <td>{{ $produto->quantity }}</td>
                    <td>{{ $produto->price }}</td>
                    <td>
                        <form action="{{ route('produtos.destroy', $produto->id) }}" method="POST">
                            <a class="btn btn-info" href="{{ route('produto.show', $produto->id) }}">Mostrar</a>
                            <a class="btn btn-primary" href="{{ route('produtos.edit', $produto->id) }}">Editar</a>
                            @csrf
                            @method('DELETE')
                            <button type="submit" class="btn btn-danger">Deletar</button>
                        </form>
                    </td>
                </tr>
            @endforeach
        </table>
    </div>
@endsection
