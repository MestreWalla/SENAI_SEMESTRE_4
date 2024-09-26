import java.util.HashMap;
import java.util.Map;

public class MapExemplo {
    private Map<String, Integer> nomesIdade;

    public MapExemplo() {
        nomesIdade = new HashMap<>();
    }

    public void adicionarNomeIdade(String nome, Integer idade) {
        if (nomesIdade.containsKey(nome)) {
            System.out.println("Nome já existe: " + nome);
            return;
        }
        nomesIdade.put(nome, idade);
        System.out.println("Nome adicionado: " + nome + ", Idade: " + idade);
    }

    public void listarNomesIdades() {
        System.out.println("Lista de nomes e idades cadastrados:");
        for (String nome : nomesIdade.keySet()) {
        System.out.println(" - " + nome + " - " + nomesIdade.get(nome) + " anos");
        }
    }

    public void removerNome(String nome) {
        nomesIdade.remove(nome);
        System.out.println("Nome removido: " + nome);
    }

    public void modificarNomeIdade(String nome, Integer idade) {
        nomesIdade.put(nome, idade);
        System.out.println("Nome e idade modificados: " + nome + ", " + idade);
    }
}
