@extends('layouts.app')

@section('content')
    <div class="container m-4">
        <h1>Login</h1>
        <form method="POST" action="{{ route('users.login') }}">
            @csrf

            <div class="form-group mt-3">
                <label for="email">Email</label>
                <input type="email" name="email" class="form-control" required autofocus>
            </div>

            <div class="form-group mt-3">
                <label for="password">Senha</label>
                <input type="password" name="password" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-primary mt-3">Login</button>
        </form>
    </div>
@endsection
