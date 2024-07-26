package com.example;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class CalculadoraTeste {
    Calculadora calc;

    @Before
    public void setup() {
        calc = new Calculadora();
    }

    @Test
    public void testSoma() {
        int resultado = calc.soma(5, 5);
        assertEquals(10, resultado);
    }

    @Test
    public void testeSubtracao() {
        assertEquals(1, calc.subtracao(5, 4));
    }

    @Test
    public void testeMultiplicacao() {
        assertEquals(26, calc.multiplicacao(5, 5), 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testeDivisorPorZero() {
        double resultado = calc.divisao(5, 0);
    }
}
