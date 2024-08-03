<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produto - Loja de Celulares</title>

    <link rel="stylesheet" href="{{ asset('css/produtoDetalhes.css') }}">
</head>

<body>
    @include('components.header')
    <div class="container">
        <div class="produto-detalhes">
            <div class="produto-imagem">
                <img src="{{ asset('images/imagem.jpg') }}" alt="Imagem do Produto">
            </div>
            <div class="produto-info">
                <h1>{{ $produto->nome }}</h1>
                <p>{{ $produto->descricao }}</p>
                <p class="preco">R$ {{ number_format($produto->preco, 2, ',', '.') }}</p>
                <p class="disponibilidade">Disponibilidade: {{ $produto->disponibilidade ? 'Em estoque' : 'Indisponível' }}</p>
                <a href="{{ route('produtos.comprar', $produto->id) }}" class="botao-comprar">Comprar</a>
            </div>
        </div>
        
        <div class="produto-detalhes-extras">
            <h2>Detalhes do Produto</h2>
            <p>{{ $produto->detalhes }}</p>
            
            <h2>Avaliações</h2>
            @foreach ($produto->avaliacoes as $avaliacao)
                <div class="avaliacao">
                    <p><strong>{{ $avaliacao->usuario->nome }}</strong> - {{ $avaliacao->comentario }}</p>
                    <p>Nota: {{ $avaliacao->nota }}</p>
                </div>
            @endforeach
            
            <h2>Perguntas e Respostas</h2>
            @foreach ($produto->perguntas as $pergunta)
                <div class="pergunta">
                    <p><strong>{{ $pergunta->usuario->nome }}:</strong> {{ $pergunta->conteudo }}</p>
                    @if ($pergunta->resposta)
                        <p><strong>Resposta:</strong> {{ $pergunta->resposta->conteudo }}</p>
                    @endif
                </div>
            @endforeach
        </div>
    </div>
</body>

</html>
