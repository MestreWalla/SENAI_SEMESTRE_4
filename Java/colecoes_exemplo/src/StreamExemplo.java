import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExemplo {
    List<String> words = Arrays.asList(
            "Banana", "Abacaxi", "Uva", "Melão", "Maçã", "Morango", "Pêra", "Cereja", "Laranja", "Goiaba", "Kiwi",
            "Pitanga", "Manga", "Cajá", "Açaí", "Mamão", "Nectarina", "Maracuja", "Jabuticaba", "Abacate", "Melancia",
            "Melão", "Mamão", "Laranja", "Uva", "Maçã", "Goiaba", "Kiwi", "Pitanga");

    // Crie uma nova lista resultado, filtre as palavras que começam com a letra 'M'
    // e terminam com a letra 'a', to uppercase, crie um metoo.

    public void resultadoStream() {
        List<String> result = words.stream()
                // .filter(word -> word.startsWith("M") && word.endsWith("a"))
                // .filter(words -> words.contains("Maçã"))
                .filter(words -> words.startsWith("A") || words.startsWith("M") || words.startsWith("N"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
