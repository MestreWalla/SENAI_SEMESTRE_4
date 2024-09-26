import java.util.ArrayList;
import java.util.List;

public class ListExemplo {
    private List<String> nomes;

    public ListExemplo() {
        this.nomes = new ArrayList<>();
    }

    public void adicionarNome(String nome) {
        this.nomes.add(nome);
        System.out.println("Nome adicionado: " + nome + " no indice: " + nomes.indexOf(nome));
    }

    public void listarNomes() {
        System.out.println();
        System.out.println("Nomes na lista:");
        for (String nome : nomes) {
            System.out.println(" - " + nome);
            
        }
    }

    public void removerNome(String nome) {
        if (this.nomes.contains(nome)) {
            this.nomes.remove(nome);
            System.out.println("Nome removido: " + nome);
        } else {
            System.out.println("Nome não encontrado: " + nome);
        }
    }

    public void modificarNome(int index, String nome, String nomeAntigo) {
        nomeAntigo = this.nomes.get(index);
        if (index >= 0 && index < this.nomes.size()) {
            this.nomes.set(index, nome);
            System.out.println("Index modificado: " + index);
            System.out.println("O nome '" + nomeAntigo + "' foi alterado para '" + nome + "'");
        } else {
            System.out.println("Índice inválido: " + index);
        }
    }
}