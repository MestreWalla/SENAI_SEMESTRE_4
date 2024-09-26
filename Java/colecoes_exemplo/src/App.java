public class App {
    public static void main(String[] args) throws Exception {
        ListExemplo list = new ListExemplo();
        System.out.println("EXECUTANDO PROGRAMA LIST EXEMPLO...");
        System.out.println();
        list.adicionarNome("Alice");
        list.adicionarNome("Bob");
        list.adicionarNome("Carlos");
        list.adicionarNome("Daniela");
        list.adicionarNome("Maria");
        list.adicionarNome("João");
        list.adicionarNome("Pedro");
        list.adicionarNome("Ana");

        list.listarNomes();

        list.modificarNome(2, "Beatriz", null);

        list.removerNome("João");

        list.listarNomes();

        list.adicionarNome("Carolina");
        list.adicionarNome("José");
        list.adicionarNome("José");
        list.listarNomes();
        list.removerNome("José");

        SetExemplo set = new SetExemplo();
        System.out.println();
        System.out.println("EXECUTANDO PROGRAMA SET EXEMPLO...");
        System.out.println();
        set.adicionarNome("Alice");
        set.adicionarNome("Bob");
        set.adicionarNome("Carlos");
        set.adicionarNome("Daniela");
        set.adicionarNome("Maria");
        set.adicionarNome("João");
        System.out.println();
        set.listarNomes();
        System.out.println();
        set.removerNome("João");
        System.out.println();
        set.listarNomes();
        System.out.println();
        set.adicionarNome("Carolina");
        set.adicionarNome("José");
        System.out.println();
        set.listarNomes();
        System.out.println();
        set.removerNome("José");
        System.out.println();
        set.listarNomes();
        System.out.println("FIM DO PROGRAMA...");
        System.out.println();
    }
}
