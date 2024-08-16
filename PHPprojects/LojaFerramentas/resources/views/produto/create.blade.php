@extends('layouts.app')
@section('content')
    <div class="container m-4">
        <h1>Cadastrar Produto</h1>

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

        <form class="align-items-center" method="POST" action="{{ route('produtos.store') }}">
            @csrf
            <div class="form-group mt-3">
                <label for="name">Nome:</label>
                <input type="text" name="name" class="form-control @error('name')is-invalid @enderror"
                    value="{{ old('name') }}" required>
                @error('name')
                    <div class="alert alert-danger invalid-feedback">
                        {{ $message }}
                    </div>
                @enderror
            </div>

            <div class="form-group mt-3">
                <label for="description">Descrição:</label>
                <input type="description" name="description" class="form-control @error('description')is-invalid @enderror"
                    value="{{ old('description') }}" required>
                @error('description')
                    <div class="alert alert-danger invalid-feedback">
                        {{ $message }}
                    </div>
                @enderror
            </div>

            <div class="form-group mt-3">
                <label for="price">Valor:</label>
                <input type="price" name="price" class="form-control @error('price')is-invalid @enderror"
                    value="{{ old('price') }}" required>
                @error('price')
                    <div class="alert alert-danger invalid-feedback">
                        {{ $message }}
                    </div>
                @enderror
            </div>

            <div class="form-group mt-3">
                <label for="quantity">Quantidade:</label>
                <input type="quantity" name="quantity" class="form-control @error('quantity')is-invalid @enderror"
                    value="{{ old('quantity') }}" required>
                @error('quantity')
                    <div class="alert alert-danger invalid-feedback">
                        {{ $message }}
                    </div>
                @enderror
            </div>

            <div class="form-group mt-3">
                <label for="category">Categoria:</label>
                <input type="category" name="category" class="form-control @error('category')is-invalid @enderror"
                    value="{{ old('category') }}" required>
                @error('category')
                    <div class="alert alert-danger invalid-feedback">
                        {{ $message }}
                    </div>
                @enderror
            </div>

            <div class="form-group mt-3">
                <label for="category">Categoria:</label>
                <input type="category" name="category" class="form-control @error('category')is-invalid @enderror"
                    value="{{ old('category') }}" required>
                @error('category')
                    <div class="alert alert-danger invalid-feedback">
                        {{ $message }}
                    </div>
                @enderror
            </div>

            <div class="mb-3">
                <label for="img_url" class="form-label">Imagem:</label>
                <input type="file" name="img_url" id="formFileSm"
                    class="form-control form-control-sm
                    @error('img_url')is-invalid @enderror"
                    value="{{ old('img_url') }}">
            </div>

            <button type="submit" class="btn btn-primary mt-3">Cadastrar</button>
        </form>
    </div>
@endsection
