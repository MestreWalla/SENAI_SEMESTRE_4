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

        <div class="produto">
            {{-- <img src="imagem_celular1.jpg" alt="Celular 1"> --}}
            <img src="{{ asset('images/imagem.jpg') }}" alt="Descrição da Imagem">
            <h2>Celular Modelo 1</h2>
            <p>Descrição do Celular Modelo 1. Este é um excelente celular com muitas funcionalidades.</p>
            <p class="preco">R$ 1.200,00</p>
            <a href="#" class="botao-comprar">Comprar</a>
        </div>

        <div class="produto">
            {{-- <img src="imagem_celular2.jpg" alt="Celular 2"> --}}
            <img src="{{ asset('images/imagem.jpg') }}" alt="Descrição da Imagem">
            <h2>Celular Modelo 2</h2>
            <p>Descrição do Celular Modelo 2. Este celular tem um ótimo custo-benefício.</p>
            <p class="preco">R$ 900,00</p>
            <a href="#" class="botao-comprar">Comprar</a>
        </div>

        <!-- Adicione mais produtos conforme necessário -->
    </div>
</body>
</html>
