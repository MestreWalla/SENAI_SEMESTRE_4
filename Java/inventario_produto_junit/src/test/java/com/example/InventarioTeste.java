package com.example;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InventarioTeste {
    private Inventario inventario;

    @Before
    public void setUp() {
        inventario = new Inventario();
    }

    @Test
    public void testeCriar() {
        Produto produto = new Produto (1, "teste", "testeFabricante", 100.00, 15);
        inventario.criar(produto);
    }

    @Test
    public void testeListar() {
        Produto produto = new Produto (1, "teste", "testeFabricante", 100.00, 15);
        inventario.criar(produto);
        assertEquals(1, inventario.listar().size());
    }

    @Test
    public void testeRemover() {
        testeCriar();
        inventario.remover(1);
        assertEquals(0, inventario.listar().size());
    }

    @Test
    public void testeAtualizar() {
        testeCriar();
        inventario.atualizar(1, 20, 150.00);
        Produto produtoAtualizado = inventario.listar().get(0);
        assertEquals(20, produtoAtualizado.getQuantidade());
        assertEquals(150.00, produtoAtualizado.getPreco(), 0.001);
    }
}