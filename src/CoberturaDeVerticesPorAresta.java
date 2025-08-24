package src;

import java.util.*;
public class CoberturaDeVerticesPorAresta {
    // Referência: https://www.ime.usp.br/~pf/analise_de_algoritmos/aulas/v-cover-fpt.html

    public static Set<Integer> coberturaPorAresta(Grafo grafo, int k) {
        if (grafo.getVertices().size() < k) {
            return new HashSet<>(grafo.getVertices());
        }
        return coberturaPorArestaRec(grafo, k, new HashSet<>());
    }

    private static Set<Integer> coberturaPorArestaRec(Grafo grafo, int k, Set<Integer> coberturaAtual) {
        // Caso base: todas as arestas estão cobertas
        if (grafo.getNumArestas() == 0) {
            return new HashSet<>(coberturaAtual);
        }

        // Caso base: já atingimos o limite de vértices
        if (coberturaAtual.size() == k) {
            return new HashSet<>(grafo.getVertices()); // penalidade
        }

        // Escolhe uma aresta qualquer
        Aresta aresta = grafo.getArestaArbitraria();
        if (aresta == null) {
            return new HashSet<>(coberturaAtual);
        }

        int u = aresta.getU();
        int v = aresta.getV();

        // Cria cópias do grafo para cada ramo da recursão
        Grafo grafoU = grafo.copia();
        Grafo grafoV = grafo.copia();

        // Remove todas as arestas incidentes a u e v
        grafoU.removerArestasIncidentes(u);
        grafoV.removerArestasIncidentes(v);

        // Ramo com u
        Set<Integer> coberturaComU = new HashSet<>(coberturaAtual);
        coberturaComU.add(u);
        Set<Integer> resultadoU = coberturaPorArestaRec(grafoU, k, coberturaComU);

        // Ramo com v
        Set<Integer> coberturaComV = new HashSet<>(coberturaAtual);
        coberturaComV.add(v);
        Set<Integer> resultadoV = coberturaPorArestaRec(grafoV, k, coberturaComV);

        // Retorna o menor conjunto
        return resultadoU.size() <= resultadoV.size() ? resultadoU : resultadoV;
    }

    public static Set<Integer> resolver(Grafo grafo) {
        int n = grafo.getVertices().size();

        // Para cada valor de k até o |V|
        for (int k = 0; k <= n; k++) {
            // Calcula se existe uma cobertura com no maximo k vértices
            Set<Integer> cobertura = coberturaPorAresta(grafo, k);

            // Se a cobertura existe, retorne ela
            if (cobertura.size() <= k) {
                return cobertura;
            }
        }

        // A cobertura são todos os vértices
        return new HashSet<>(grafo.getVertices());
    }
}
