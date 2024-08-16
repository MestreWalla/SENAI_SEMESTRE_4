@extends('layouts.app')

@section('content')
    <div class="container p-3">
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
        <h1>Loja Ferramentas</h1>
        <p>Bem-vindo ao nosso site! Aqui você pode encontrar diversos produtos de qualidade e conforto.</p>
        
        <div id="productCarousel" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                @foreach ($produtos as $index => $produto)
                <div class="carousel-item {{ $index == 0 ? 'active' : '' }}">
                    <img src="assets/img/AlicateBico.jpg" 
                         class="rounded mx-auto d-block border border-dark shadow-lg" 
                         alt="{{ $produto->name }}" 
                         style="width: 400px">
                    <div class="carousel-caption d-sm-block text-dark" 
                         style="background-color: rgba(255, 255, 255, 0.8); border-radius: 10px; padding: 10px;">
                        <h5>{{ $produto->name }}</h5>
                        <p>{{ $produto->description }}</p>
                        <p><strong>Preço:</strong> R$ {{ $produto->price }}</p>
                    </div>
                </div>
                
                @endforeach
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#productCarousel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#productCarousel" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
@endsection
