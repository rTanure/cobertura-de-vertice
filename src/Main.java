package src;

public class Main{
    public static void main(String[] args) {
        Grafo grafo = GeradorInstancias.instanciaAleatoria(10, 1.0);
        grafo.salvarArquivo("./");
    }
}