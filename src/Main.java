package src;

import java.util.Set;

public class Main{
    public static void main(String[] args) {
        Grafo grafo_a = GeradorInstancias.instanciaAleatoria(100, 0.5);
        Grafo grafo_b= GeradorInstancias.instanciaAleatoria(100, 0.5);

        Set<Integer> resultado = CoberturaDeVertices.aproximationVertexCover(grafo_b);


        System.out.println(VerificadorDeCertificado.verificar(grafo_a, resultado));
    }
}