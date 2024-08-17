@extends('layouts.app')

@section('content')
    <div class="container-fluid">
        <div class="row">

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

                <h1>Dashboard de Produtos</h1>


                <form method="GET" action="{{ route('dashboard') }}">
                    <input type="text" name="search" placeholder="Pesquisar produtos..."
                        value="{{ request('search') }}">
                    <button type="submit">Pesquisar</button>
                </form>

                <!-- Lista de Produtos -->
                <div class="row mt-3 mb-3">
                    @foreach ($produtos as $produto)
                        <div class="col-md-4">
                            <div class="card">
                                {{-- <img src="{{ asset('storage/' . $produto->imagem) }}" class="card-img-top"
                                    alt="{{ $produto->name }}"> --}}
                                <img src="assets/img/AlicateBico.jpg" class="rounded card-img-top"
                                    alt="{{ $produto->name }}">
                                <div class="card-body">
                                    <h5 class="card-title">{{ $produto->name }}</h5>
                                    <p class="card-text">{{ $produto->description }}</p>
                                    <p class="card-text">Preço: R$ {{ $produto->price }}</p>
                                    <a href="{{ route('produto.show', $produto->id) }}" class="btn btn-primary">Ver
                                        Produto</a>
                                </div>
                            </div>
                        </div>
                    @endforeach
                </div>

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
            </main>
        </div>
    </div>
@endsection
