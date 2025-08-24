package src;

import java.util.*;

public class Gerador {
    private static Grafo grafoComCliqueMaxima(int numVertices, int cliqueMaxima, double densidadeRelativa) {
        if(cliqueMaxima > numVertices) cliqueMaxima = numVertices;
        Grafo grafo = new Grafo(numVertices);

        Random random = new Random();

        for (int u = 0; u < numVertices; u++) {
            for (int v = u; v < numVertices; v++) {
                // Impede a criação de
                if(u == v) continue;

                // Se a aresta está na clique
                if (u < cliqueMaxima && v < cliqueMaxima) {
                    grafo.addAresta(u, v);
                    continue;
                }

                if (random.nextDouble() >= densidadeRelativa) continue;

                // Se a aresta é entre um vértice da clique e um de fora
                Integer grau_v = grafo.grauDoVertive(v);
                if (u < cliqueMaxima) {
                    if (grau_v < cliqueMaxima) {
                        grafo.addAresta(u, v);
                        continue;
                    }
                }

                // Se a aresta é entre dois vértices de fora da clique
                Integer grau_u = grafo.grauDoVertive(u);
                if (grau_u < cliqueMaxima && grau_v < cliqueMaxima) {
                    grafo.addAresta(u, v);
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
