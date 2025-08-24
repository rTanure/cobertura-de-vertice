// RICHARDY RODRIGUES TANURE - 22.2.8003
// MAYKE ANSELMO BRITO LELLIS - 22.2.8008

package src;

import java.util.*;

// Classe responsável por gerar um grafo.
public class Gerador {
    // Gera um grafo com uma clique maxima
    private static Grafo grafoComCliqueMaxima(int numVertices, int cliqueMaxima, double densidadeRelativa) {
        // Trata entradas de clique maxima maior que o número de vértices.
        if(cliqueMaxima > numVertices) cliqueMaxima = numVertices;
        Grafo grafo = new Grafo(numVertices);

        Random random = new Random();

        // Para cada aresta possivel no grafo
        for (int u = 0; u < numVertices; u++) {
            for (int v = u; v < numVertices; v++) {
                // Impede que um vertice aponte para ele mesmo
                if(u == v) continue;

                // Se (u, v) está na clique, adicione a aresta no grafo
                if (u < cliqueMaxima && v < cliqueMaxima) {
                    grafo.addAresta(u, v);
                    continue;
                }

                // Verifica a densidade relativa
                if (random.nextDouble() >= densidadeRelativa) continue;

                // Se u xor v são vértices de fora da clique
                Integer grau_v = grafo.grauDoVertive(v);
                if (u < cliqueMaxima) {
                    if (grau_v < cliqueMaxima) {
                        grafo.addAresta(u, v);
                        continue;
                    }
                }

                // Se u e v são vértices de fora da clique
                Integer grau_u = grafo.grauDoVertive(u);
                if (grau_u < cliqueMaxima && grau_v < cliqueMaxima) {
                    grafo.addAresta(u, v);
                }
            }
        }

        // retorna o grafo gerado
        return grafo;
    }

    // Gera um grafo com uma cobertura de vértices mínima
    public static Grafo grafoComCoberturaMinima(int numVertices, int coberturaMinima, double densidadeRelativa) {
        // Trata entradas de cobertura mínima maior que o número de vértices.
        if(coberturaMinima > numVertices) coberturaMinima = numVertices;

        int cliqueMaxima = numVertices - coberturaMinima;

        double densidadeRelativaClique = 1 - densidadeRelativa;
        // Gera o grafo com a clique maxima
        Grafo grafoClique = grafoComCliqueMaxima(numVertices, cliqueMaxima, densidadeRelativaClique);

        // Retorna o complemento
        return grafoClique.complemento();
    }
}
