@extends('layouts.app')
@section('content')
    <div class="container">

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

        <div class="row">
            <div class="col-md-6">
                <img src="assets/img/img0.png" class="img-fluid" alt="{{ $produto->name }}">
            </div>
            <div class="col-md-6">
                <h2>{{ $produto->name }}</h2>
                <p>{{ $produto->category }}</p>
                <p>{{ $produto->description }}</p>
                <p>Preço: R$ {{ $produto->price }}</p>

                <form method="POST" action="{{ route('carrinho.add', $produto->id) }}">
                    @csrf
                    <label for="quantity">Selecione a Quantidade</label>
                    <input type="number" class="form-control" name="quantity" min="1" value="1" required>
                    <button type="submit" class="btn btn-primary">Adicionar ao Carrinho</button>
                </form>
            </div>
        </div>
    </div>
@endsection
