<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Produto;

class ProdutoController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $produtos = Produto::all();
        return view('produto.index', compact('produtos'));
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('produto.create');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        // Validar os dados
        $request->validate([
            'name' => 'required|string|max:255',
            'description' => 'required',
            'price' => 'required|numeric',
            'quantity' => 'required|numeric',
            'category' => 'required',
            // 'img_url' => 'optional',
        ]);
        Produto::create($request->all());
        return redirect()->route('produto.index')->with('success', 'Produto cadastrado com sucesso!');
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Produto $produto)
    {
        return view('produto.edit', compact('produto'));
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Produto $produto)
    {
        // Validar os dados
        $request->validate([
            'name' => 'required',
            'description' => 'required',
            'price' => 'required|numeric',
            'quantity' => 'required',
            'category' => 'required',
            'img_url' => 'max:2048|mimes:jpeg,png,jpg,gif,svg|optional',
        ]);
        $produto->update($request->all());
        return redirect()->route('produto.index')->with('success', 'Produto atualizada com sucesso!');    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Produto $produto){
        $produto->delete();
        return redirect()->route('produto.index')->with('success', 'Produto excluído com sucesso!');
    }

    public function messages(): array
        {
            return [
                'name.required' => 'O campo Nome é obrigatório',
                'email.required' => 'O campo E-mail é obrigatório',
                'email.email' => 'O campo E-mail deve ser um endereço de e-mail válido',
                'email.unique' => 'O campo E-mail já está cadastrado',
                'img.max' => 'O campo img deve ter no máximo 2MB',
                'img.mimes' => 'O campo img deve ser um arquivo do tipo: jpeg, png, jpg, gif, svg',

            ];
        }
}
