<?php

use App\Http\Controllers\VagaController;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

Route::get('vagas', [VagaController::class]);