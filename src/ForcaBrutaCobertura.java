package src;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ForcaBrutaCobertura {

    public static Set<Integer> combinacoes(Grafo G, int k) {
        int n = G.getVertices().size();

        if (n < k) {
            return null;
        }

        Set<Integer> R = new HashSet<>();                   // conjunto inicial vazio
        Set<Integer> S = new HashSet<>(G.getVertices());    // todos os vértices disponíveis

        return combRec(G, k, R, S);
    }

    private static Set<Integer> combRec(Grafo G, int k, Set<Integer> R, Set<Integer> S) {
        // Caso 1: já temos k vértices em R
        if (R.size() == k) {
            return cobreTodasArestas(G, R) ? new HashSet<>(R) : new HashSet<>(G.getVertices());
        }

        // Caso 2: S tem exatamente k vértices restantes
        if (S.size() == k) {
            return cobreTodasArestas(G, S) ? new HashSet<>(S) : new HashSet<>(G.getVertices());
        }

        // Escolhe um vértice qualquer de S - R
        // vértices disponíveis que ainda não foram escolhidos
        Set<Integer> diff = new HashSet<>(S);
        diff.removeAll(R);

        // Verifica se há vértices para escolher
        if (diff.isEmpty()) {
            return new HashSet<>(G.getVertices()); // sem vértices para escolher
        }

        // Escolhe um vertice qualquer
        Iterator<Integer> it = diff.iterator();
        int v = it.next();

        // Chamada 1: inclui v em R
        Set<Integer> R1 = new HashSet<>(R);
        R1.add(v);
        Set<Integer> X = combRec(G, k, R1, S);

        // Chamada 2: exclui v de S
        Set<Integer> S1 = new HashSet<>(S);
        S1.remove(v);
        Set<Integer> Y = combRec(G, k, R, S1);

        // Retorna o menor conjunto
        if (X.size() <= Y.size()) return X;
        return Y;
    }

    // Verifica se um conjunto de vértices cobre todas as arestas
    private static boolean cobreTodasArestas(Grafo G, Set<Integer> C) {
        for (Integer u : G.getVertices()) {
            BitSet vizinhos = G.getVizinhos(u);
            for (int v = 0; v < G.getVertices().size(); v++) {
                if (vizinhos.get(v)) {
                    if (!(C.contains(u) || C.contains(v))) {
                        return false; // aresta não coberta
                    }
                }
            }
        }
        return true;
    }
}
