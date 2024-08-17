<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Produto;
use Illuminate\Support\Facades\Auth;

class CarrinhoController extends Controller
{
    public function add(Request $request, Produto $produto)
    {
        // Validar input do produto
        $dados = $request->validate([
            'quantity' => 'required|integer|min:1'
        ]);


        Produto::create([
            'id_produto' => $produto->id,
            'id_user' => Auth::id(),
            'quantidade' => $request->quantidade
        ]);

        // Recuperar o carrinho atual ou inicializar um novo array
        $carrinho = $request->session()->get('carrinho', []);

        // Verificar se o item já está no carrinho
        if (array_key_exists($produto->id, $carrinho)) {
            // Se já estiver, incrementa a quantidade
            $carrinho[$produto->id] += $dados['quantity'];
        } else {
            // Se não estiver, adiciona ao carrinho
            $carrinho[$produto->id] = $dados['quantity'];
        }

        // Atualizar o carrinho na sessão
        $request->session()->put('carrinho', $carrinho);

        // Retornar para a página anterior com uma mensagem de sucesso
        return redirect()->back()->with('success', 'Item adicionado ao carrinho!');
    }
}
