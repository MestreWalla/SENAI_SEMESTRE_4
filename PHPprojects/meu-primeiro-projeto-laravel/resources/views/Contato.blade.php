<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contato - Loja de Celulares</title>
</head>
<link rel="stylesheet" href="{{ asset('css/contato.css') }}">
<body>
    @include('components.header')
    <div class="container">
        <h1>Contato</h1>

        <div class="contato-info">
            <h2>Informações de Contato</h2>
            <p>Endereço: Rua Exemplo, 123, Centro, Cidade, Estado</p>
            <p>Telefone: (11) 1234-5678</p>
            <p>Email: contato@lojadecelulares.com</p>
        </div>

        <div class="contato-form">
            <h2>Envie uma Mensagem</h2>
            <form action="processar_contato.php" method="POST">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" required>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>

                <label for="mensagem">Mensagem:</label>
                <textarea id="mensagem" name="mensagem" rows="5" required></textarea>

                <button type="submit">Enviar</button>
            </form>
        </div>
    </div>
</body>
</html>
