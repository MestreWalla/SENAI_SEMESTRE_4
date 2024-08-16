@extends('layouts.app')

@section('content')
    <div class="container-fluid">
        <div class="row">
            <!-- Menu de Navegação Lateral -->
            <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                <div class="position-sticky pt-3">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">
                                <span data-feather="home"></span>
                                Dashboard
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <span data-feather="file"></span>
                                Pedidos
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <span data-feather="shopping-cart"></span>
                                Produtos
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <span data-feather="users"></span>
                                Clientes
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <span data-feather="bar-chart-2"></span>
                                Relatórios
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <span data-feather="layers"></span>
                                Integrações
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>

            <!-- Conteúdo Principal -->
            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2">Dashboard</h1>
                    <div class="btn-toolbar mb-2 mb-md-0">
                        <div class="btn-group me-2">
                            <button type="button" class="btn btn-sm btn-outline-secondary">Compartilhar</button>
                            <button type="button" class="btn btn-sm btn-outline-secondary">Exportar</button>
                        </div>
                    </div>
                </div>

            @section('content')
                <h1>Dashboard de Produtos</h1>


                <form method="GET" action="{{ route('dashboard') }}">
                    <input type="text" name="search" placeholder="Pesquisar produtos..."
                        value="{{ request('search') }}">
                    <button type="submit">Pesquisar</button>
                </form>


                <div class="row">
                    @foreach ($produtos as $produto)
                        <div class="col-md-4">
                            <div class="card">
                                <img src="{{ asset('storage/' . $produto->imagem) }}" class="card-img-top"
                                    alt="{{ $produto->name }}">
                                <div class="card-body">
                                    <h5 class="card-title">{{ $produto->name }}</h5>
                                    <p class="card-text">{{ $produto->description }}</p>
                                    <p class="card-text">Preço: R$ {{ $produto->price }}</p>
                                    {{-- <a href="{{ route('produtos.show', $produto->id) }}" class="btn btn-primary">Ver
                                        Produto</a> --}}
                                </div>
                            </div>
                        </div>
                    @endforeach
                </div>
            @endsection

            <!-- Estatísticas de Vendas -->
            <h4>Estatísticas de Vendas</h4>
            <div class="row">
                <div class="col-md-4">
                    <div class="card text-white bg-primary mb-3">
                        <div class="card-header">Vendas Hoje</div>
                        <div class="card-body">
                            <h5 class="card-title">R$ 2,000</h5>
                            <p class="card-text">Total de vendas realizadas hoje.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-white bg-success mb-3">
                        <div class="card-header">Vendas Esta Semana</div>
                        <div class="card-body">
                            <h5 class="card-title">R$ 15,000</h5>
                            <p class="card-text">Total de vendas realizadas nesta semana.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-white bg-danger mb-3">
                        <div class="card-header">Vendas Este Mês</div>
                        <div class="card-body">
                            <h5 class="card-title">R$ 60,000</h5>
                            <p class="card-text">Total de vendas realizadas neste mês.</p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Gráfico de Vendas (Simples) -->
            <h4 class="mt-4">Gráfico de Vendas</h4>
            <canvas id="salesChart" width="400" height="200"></canvas>
        </main>
    </div>
</div>
@endsection

@section('scripts')
<!-- Scripts para gráficos (usando Chart.js por exemplo) -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    var ctx = document.getElementById('salesChart').getContext('2d');
    var salesChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho'],
            datasets: [{
                label: 'Vendas 2024 (R$)',
                data: [12000, 19000, 3000, 5000, 20000, 30000],
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 2
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
@endsection
