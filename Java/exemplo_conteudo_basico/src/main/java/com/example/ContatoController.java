package com.example;

public class ContatoController {
    private Contato[] contatos;
    private int contadorDeContatos;

    public ContatoController(int maxContato) {
        maxContato = 5;
        contatos = new Contato[maxContato];
        contadorDeContatos = 0;
    }

    public void addContato(Contato contato) throws AgendaCheiaException {
        if (contadorDeContatos >= contatos.length) {
            throw new AgendaCheiaException("Agenda Cheia");
        }
        try {
            contatos[contadorDeContatos] = contato;
            contadorDeContatos++;
            System.out.println("Contato Adicionado");
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void listarContatos() {

        if (contadorDeContatos == 0) {
            System.out.println("Nenhum contato cadastrado!");
        } else {
            for (int i = 0; i < contatos.length; i++) {
                System.out.println(contatos[i].toString() + "\n1");
            }
        }
    }

    public void buscarNome(String name) throws ContatoNaoEncontrado {

        if (contadorDeContatos == 0) {
            System.out.println("Agenda vazia.");
        } else {
            for (int i = 0; i < contatos.length; i++) {
                if (contatos[i].getNome().equalsIgnoreCase(name)) {
                    System.out.println(contatos[i].toString());
                }
            }
        }
        throw new ContatoNaoEncontrado("Contato não encontrado");
    }
}
