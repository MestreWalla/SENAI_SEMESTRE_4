import java.util.HashSet;
import java.util.Set;

public class SetExemplo {
    private Set<String> nomes;

    public SetExemplo() {
        this.nomes = new HashSet<>();
    }

    public void adicionarNome(String nome) {
        this.nomes.add(nome);
        System.out.println(nomes.hashCode() + " - Nome adicionado: " + nome);
    }

    public void listarNomes() {
        System.out.println("Nomes na lista:");
        for (String nome : nomes) {
            System.out.println(" - " + nome);
        }
    }

    public void removerNome(String nome) {
        if (this.nomes.contains(nome)) {
            this.nomes.remove(nome);
            System.out.println("O nome: " + nome + " foi removido da lista. HashCode: " + nomes.hashCode());
        } else {
            System.out.println("Nome não encontrado: " + nome);
        }
    }

    public void modificarNome(int index, String nome, String nomeAntigo) {
        if (this.nomes.contains(nomeAntigo)) {
            this.nomes.remove(nomeAntigo);
            this.nomes.add(nome);
            System.out.println("Nome alterado de " + nomeAntigo + " para " + nome);
        } else {
            System.out.println("Nome antigo não encontrado: " + nomeAntigo);
        }
    }
}
