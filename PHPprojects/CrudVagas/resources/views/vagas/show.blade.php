@extends('layouts.master')

<div class="container">
    <div class="row">
        <a class="btn btn-sm btn-primary" href="{{ route('vagas.index') }}">
            <i class="bi bi-arrow-return-left"></i>
            Voltar
        </a>
        <h1 class="my-4">Detalhes do Produto</h1>
    </div>

    <div class="form-group">
        <strong>Titulo:</strong>
        {{ $vaga->titulo }}
    </div>

    <div class="form-group">
        <strong>Setor:</strong>
        {{ $vaga->setor }}
    </div>

    <div class="form-group">
        <strong>Remuneração:</strong>
        {{ $vaga->remuneracao }}
    </div>

    <div class="form-group">
        <strong>Descrição:</strong>
        {{ $vaga->descricao }}
    </div>

    <div class="form-group">
        <strong>Empresa:</strong>
        {{ $vaga->empresa }}
    </div>
</div>
