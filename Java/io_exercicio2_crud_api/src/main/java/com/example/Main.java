package com.example;

public class Main {
    public static void main(String[] args) {
        UsuarioController controller = new UsuarioController();
        controller.write(new Usuario("", "Maycon", 32, "Rua x"));
        controller.read();
        
    }
}