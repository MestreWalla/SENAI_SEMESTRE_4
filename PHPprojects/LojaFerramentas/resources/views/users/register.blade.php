@extends('layouts.app')
@section('content')
    <div class="container m-4">
        <h1>Registrar-se</h1>
        <form class="align-items-center" method="POST" action="{{ route('users.register') }}">
            @csrf
            <div class="form-group mt-3">
                <label for="name">Nome</label>
                <input type="text" name="name" class="form-control @error('name')is-invalid @enderror"
                    value="{{ old('name') }}" required>
                @error('name')
                    <div class="alert alert-danger invalid-feedback">
                        {{ $message }}
                    </div>
                @enderror
            </div>

            <div class="form-group mt-3">
                <label for="email">Email</label>
                <input type="email" name="email" class="form-control @error('email')is-invalid @enderror"
                    value="{{ old('email') }}" required>
                @error('email')
                    <div class="alert alert-danger invalid-feedback">
                        {{ $message }}
                    </div>
                @enderror
            </div>

            <div class="form-group mt-3">
                <label for="password">Senha</label>
                <input type="password" name="password" class="form-control @error('password')is-invalid @enderror" required>
                @error('password')
                    <div class="alert alert-danger invalid-feedback">
                        {{ $message }}
                    </div>
                @enderror
            </div>

            <div class="form-group mt-3">
                <label for="password_confirmation">Confirme a Senha</label>
                <input type="password" name="password_confirmation"
                    class="form-control @error('password_confirmation')is-invalid @enderror" required>
                @error('password_confirmation')
                    <div class="alert alert-danger invalid-feedback">
                        {{ $message }}
                    </div>
                @enderror
            </div>

            <button type="submit" class="btn btn-primary mt-3">Registrar-se</button>
        </form>
    </div>
@endsection
