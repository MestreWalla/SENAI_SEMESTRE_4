<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produtos - Loja de Celulares</title>

    <link rel="stylesheet" href="{{ asset('css/produtos.css') }}">
</head>

<body>
    @include('components.header')
    <div class="container">
        <h1>Nossos Produtos</h1>
        @foreach ($produtos as $produto)
            <div class="produto">
                <img src="{{ asset('images/imagem.jpg') }}" alt="Descrição da Imagem">
                <h2>{{ $produto->nome }}</h2>
                <p>{{ $produto->descricao }}</p>
                <p class="preco">{{ $produto->descricao }}</p>
                <a href="ProdutoDetalhes,{{ $produto->id }}" class="botao-comprar">Comprar</a>
                {{-- <a href="{{ route('produtos.edit', $produto->id) }}" class="btn btn-warning">Editar</a>
                <form action="{{ route('produtos.destroy', $produto->id) }}" method="POST" style="display:inline;">
                    @csrf
                    @method('DELETE')
                    <button type="submit" class="btn btn-danger">Excluir</button>
                </form> --}}
            </div>
        @endforeach
    </div>
</body>

</html>
