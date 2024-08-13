<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;

class ControllerUser extends Controller
{
    // Exibir o formulário de login
    public function showLoginForm()
    {
        return view('user.login');
    }


    // Processar o login do usuário
    public function login(Request $request)
    {
        $credentials = $request->validate([
            'email' => ['required', 'email'],
            'password' => ['required'],
        ]);


        if (Auth::guard('web')->attempt($credentials)) {
            $request->session()->regenerate();
            return redirect()->intended('/dashboard');
        }


        return back()->withErrors([
            'email' => 'As credenciais não correspondem aos nossos registers.',
        ])->onlyInput('email');
    }


    // Exibir o formulário de registro
    public function showRegisterForm()
    {
        return view('user.register');
    }


    // Processar o registro de um novo usuário
    public function register(Request $request)
    {
        $request->validate([
            'name' => 'required|string|max:255',
            'email' => 'required|string|email|max:255|unique:user',
            'password' => 'required|string|min:8|confirmed',
        ]);


        $user = User::create([
            'name' => $request->name,
            'email' => $request->email,
            'password' => Hash::make($request->password),
        ]);


        // Auth::login($user);

        return redirect('/');
    }


    // Realizar o logout do usuário
    public function logout(Request $request)
    {
        Auth::logout();


        $request->session()->invalidate();
        $request->session()->regenerateToken();


        return redirect('/');
    }
}