<?php

namespace App\Http\Controllers;

use App\Models\Vaga;
use Illuminate\Http\Request;

class VagaController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $vagas = Vaga::all();
        return view('vagas.index', compact('vagas'));
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('vagas.create');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        // Validar os dados
        $request->validate([
            'titulo' => 'required',
            'descricao' => 'required',
            'remuneracao' => 'required|decimal',
            'setor' => 'required',
            'empresa' => 'required',
            'status' => 'required',
        ]);
        Vaga::create($request->all());
        return redirect()->route('vagas.index')->with('success', 'Vaga cadastrada com sucesso!');
    }

    /**
     * Display the specified resource.
     */
    public function show(Vaga $vaga)
    {
        return view('vagas.show', compact('vaga'));
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Vaga $vaga)
    {
        return view('vagas.edit', compact('vaga'));
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Vaga $vaga)
    {
        // Validar os dados
        $request->validate([
            'titulo' => 'required',
            'descricao' => 'required',
            'remuneracao' => 'required|decimal',
           'setor' => 'required',
            'empresa' => 'required',
           'status' => 'required',
        ]);
        $vaga->update($request->all());
        return redirect()->route('vagas.index')->with('success', 'Vaga atualizada com sucesso!');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        //
    }
}
