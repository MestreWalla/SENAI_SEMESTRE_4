package com.example;

public class BancoService {

    private ContaBancariaRepository repository;

    public BancoService(ContaBancariaRepository repository) {
        this.repository = repository;
    }

    public void depositar(String numeroConta, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo");
        }
        
        ContaBancaria conta = repository.encontrarPorNumero(numeroConta);
        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada");
        }
        
        conta.setSaldo(conta.getSaldo() + valor);
        repository.atualizar(conta);
    }
    

    public void sacar(String numeroConta, double valor) {
        ContaBancaria conta = repository.encontrarPorNumero(numeroConta);
        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }
        conta.sacar(valor);
        repository.atualizar(conta);
    }

    public double consultarSaldo(String numeroConta) {
        ContaBancaria conta = repository.encontrarPorNumero(numeroConta);
        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }
        return conta.getSaldo();
    }

}
