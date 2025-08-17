package src;

import java.util.Set;

public class Main{
    public static void main(String[] args) {
        Grafo grafo_a = GeradorInstancias.instanciaAleatoria(5, 1);
        grafo_a.salvarArquivo("grafo 1");
        grafo_a.removerVertice(1);
        grafo_a.salvarArquivo("grafo 2");

    }
}