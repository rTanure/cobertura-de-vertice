package src;

import java.util.Set;

public class Main{
    public static void main(String[] args) {
        Grafo grafo_a = GeradorInstancias.grafoComCoberturaMinima(10, 4, 1);
        //Grafo grafo_b = GeradorInstancias.instanciaAleatoria(10, 0.5);

        //grafo_a.salvarArquivo("./");
        //grafo_b.salvarArquivo("./");

        Set<Integer> resultado = CoberturaDeVertices.forcaBruta(grafo_a);

        System.out.println(resultado);
    }
}