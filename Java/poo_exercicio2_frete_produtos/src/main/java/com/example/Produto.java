package com.example;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Produto {
    private String nome;
    private double preco;

    public abstract String calcularFrete();
}
