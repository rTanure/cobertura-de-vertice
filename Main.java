public class Main{
    public static void main(String[] args) {
        Grafo grafo = Grafo.lerArquivo(args[0]);
        System.out.println(grafo);
    }
}