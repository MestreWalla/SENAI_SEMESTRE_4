<?php

use App\Http\Controllers\CarrinhoController;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ControllerUser;
use App\Http\Controllers\DashboardController;
use App\Http\Controllers\HomeController;
use App\Http\Controllers\ProdutoController;
use App\Http\Middleware\ProdutosMiddleware;

// Rota Home
Route::get('/', [HomeController::class, 'index'])->name('home');

// Rotas para Cadastro de Usuário
Route::get('/register', [ControllerUser::class, 'showRegisterForm'])->name('users.register');
Route::post('/register', [ControllerUser::class, 'register']);

// Rotas para Login de Usuário
Route::get('/login', [ControllerUser::class, 'showLoginForm'])->name('users.login');
Route::post('/login', [ControllerUser::class, 'login'])->name('users.authenticate'); // Alterado para evitar duplicidade de nome

// Rota para Logout de Usuário
Route::post('/logout', [ControllerUser::class, 'logout'])->name('users.logout');

// Rota para Visualização de um produto especifico (deve vir antes da definição de resource)
Route::get('produtos/{produto}', [ProdutoController::class, 'show'])->middleware('auth')->name('produto.show');

// Rota para cadastrar produto
Route::get('/cadastrarProduto', [ProdutoController::class, 'create'])->name('produto.create');

// Rota para Listar todos os produtos com middleware
Route::resource('produtos', ProdutoController::class)->except(['show'])->middleware(ProdutosMiddleware::class);

// Rota para Dashboard do Usuário logado
Route::get('/dashboard', [DashboardController::class, 'index'])->middleware(['auth'])->name('dashboard');

// Rota para adicionar produto no carrinho
Route::post('carrinho/add/{produto}', [CarrinhoController::class, 'add'])->middleware('auth')->name('carrinho.add');