package src;

import java.util.*;
public class CoberturaPorNucleo {
    public Set<Integer> coberturaPorNucleo(Grafo grafo, int k) {
        Set<Integer> R = new HashSet<>();

        //encontra vértices com mais de k vizinhos
        for (Integer v : grafo.getVertices()) {
            if (grafo.getVizinhos(v).cardinality() > k) {
                R.add(v);
            }
        }

        // cria subgrafo F sem os vértices de R
        Grafo F = grafo.copia();
        for (Integer r : R) {
            F.removerVertice(r);
        }

        // calcula j = k - |R|
        int j = k - R.size();

        // se número de vértices em F > j + j * k, devolve cobertura trivial
        if (F.getVertices().size() > j + j * k) {
            return new HashSet<>(grafo.getVertices());
        }

        // aplica algoritmo da aresta no núcleo
        CoberturaDeVerticesPorAresta algoritmoAresta = new CoberturaDeVerticesPorAresta();
        Set<Integer> X = algoritmoAresta.coberturaPorAresta(F, j);

        // se cobertura do núcleo couber em j, junta com R
        if (X.size() <= j) {
            Set<Integer> resultado = new HashSet<>(R);
            resultado.addAll(X);
            return resultado;
        }

        // caso contrário, devolve cobertura trivial
        return new HashSet<>(grafo.getVertices());
    }
}
