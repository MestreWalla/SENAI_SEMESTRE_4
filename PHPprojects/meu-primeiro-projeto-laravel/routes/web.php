<?php

use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('home');
});
Route::get('Produtos', function () {
    return view('produtos');
});
Route::get('Contato', function () {
    return view('contato');
});