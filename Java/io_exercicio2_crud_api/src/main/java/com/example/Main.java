package com.example;

public class Main {
    public static void main(String[] args) {
        UsuarioController controller = new UsuarioController();
        // controller.write(new Usuario("", "Maycon", 32, "Rua x"));
        // controller.update(new Usuario("", "Nome", 32, "Rua C"));
        controller.read();
        
    }
}