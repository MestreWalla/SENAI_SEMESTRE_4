<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ControllerUser;
use App\Http\Controllers\ProdutoController;

Route::get('/', function () {
    return view('home');
});

Route::get('/register', [ControllerUser::class, 'showRegisterForm'])->name('users.register');
Route::post('/register', [ControllerUser::class, 'register'])->name('users.register');
Route::get('/login', [ControllerUser::class, 'showLoginForm'])->name('users.login');
Route::post('/login', [ControllerUser::class, 'login'])->name('users.login');
Route::post('/logout', [ControllerUser::class, 'logout'])->name('users.logout');
Route::get('/produtos', [ProdutoController::class, 'index'])->name('produto.index');
Route::get('/cadastrarProduto', [ProdutoController::class, 'create'])->name('produto.create');
Route::resource('produto', ProdutoController::class)->middleware('auth');
Route::get(
    '/dashboard',
    function () {
        return view('users.dashboard');
    }
)->middleware(['auth'])->name('users.dashboard');
