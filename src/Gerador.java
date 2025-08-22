package src;

import java.util.*;

public class Gerador {
    private static Grafo grafoComCliqueMaxima(int numVertices, int cliqueMaxima, double densidadeRelativa) {
        if(cliqueMaxima > numVertices) cliqueMaxima = numVertices;
        Grafo grafo = new Grafo(numVertices);

        Random random = new Random();

        for(int i = 0; i < cliqueMaxima; i++) {
            for(int j = i + 1 ; j < cliqueMaxima; j++) {
                grafo.addAresta(i, j);
            }
        }

        for(int v = cliqueMaxima; v < numVertices; v++) {
            for(int u = 0; u < cliqueMaxima; u++) {
                if(random.nextDouble() < densidadeRelativa) {
                    grafo.addAresta(v, u);
                }
            }
        }

        // union find
        for(int v = cliqueMaxima; v < numVertices; v++) {
            for(int w = v+1; w < numVertices; w++) {
                int comuns = 0;
                for(int c = 0; c < cliqueMaxima; c++) {
                    if(grafo.verificarAdjacencia(v, c) && grafo.verificarAdjacencia(w, c)) {
                        comuns++;
                    }
                }

                if(comuns < cliqueMaxima - 1 && random.nextDouble() < densidadeRelativa) {
                    grafo.addAresta(v, w);
                }
            }
        }

        return grafo;
    }

    // Gera um grafo com uma cobertura de vértices mínima
    public static Grafo grafoComCoberturaMinima(int numVertices, int coberturaMinima, double densidadeRelativa) {
        if(coberturaMinima > numVertices) coberturaMinima = numVertices;

        int cliqueMaxima = numVertices - coberturaMinima;

        double densidadeRelativaClique = 1 - densidadeRelativa;
        Grafo grafoClique = grafoComCliqueMaxima(numVertices, cliqueMaxima, densidadeRelativaClique);

        return grafoClique.complemento();
    }
}
