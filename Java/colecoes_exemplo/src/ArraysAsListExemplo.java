import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class ArraysAsListExemplo {

    private String[] nomes;
    private List<String> nomesList;

    public ArraysAsListExemplo() {
        nomes = new String[] { "João", "Maria", "Pedro" };
        nomesList = new ArrayList<>(Arrays.asList(nomes));
    }

    // alteração da array fixo

    public void adicinoarArray(String nome) {
        int posicao = nomes.length;
        try {
            nomes[posicao] = nome;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Arrays.toString(nomes) + " - Nome adicionado: " + nome);
        }
    }

    // alteração da lista
    public void adicionarList(String nome) {
        try {
            nomesList.add(nome);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(nomesList);
        }
    }
}