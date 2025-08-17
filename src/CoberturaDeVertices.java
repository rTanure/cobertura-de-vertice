package src;

import java.util.*;

public class CoberturaDeVertices {
    public static Set<Integer> aproximationVertexCover(Grafo grafo) {
        Set<Integer> c = new HashSet<>();
        Grafo grafoCopia = grafo.copia();

        while(grafoCopia.getNumArestas() > 0) {
            List<Integer> aresta = grafoCopia.arestaAleatoria();
            if(aresta == null) break;

            int u = aresta.get(0);
            int v = aresta.get(1);

            c.add(u);
            c.add(v);

            BitSet vizU = (BitSet) grafoCopia.getVizinhos(u).clone();
            for (int w = vizU.nextSetBit(0); w >= 0; w = vizU.nextSetBit(w+1)) {
                grafoCopia.removerAresta(u, w);
            }

            BitSet vizV = (BitSet) grafoCopia.getVizinhos(v).clone();
            for (int w = vizV.nextSetBit(0); w >= 0; w = vizV.nextSetBit(w+1)) {
                grafoCopia.removerAresta(v, w);
            }
        }

        return c;
    }

    public static Set<Integer> forcaBruta(Grafo grafo) {
        Set<Integer> c = new HashSet<>();
        Grafo grafoCopia = grafo.copia();

        while (grafoCopia.getNumArestas() > 0) {
            // Escolher o vértice com maior grau
            int maxGrau = -1;
            int verticeMax = -1;

            for (int v : grafoCopia.getVertices()) {
                int grau = grafoCopia.getVizinhos(v).cardinality();
                if (grau > maxGrau) {
                    maxGrau = grau;
                    verticeMax = v;
                }
            }

            if (verticeMax == -1) break; // segurança

            c.add(verticeMax);

            // Remover todas as arestas incidentes ao vértice escolhido
            BitSet vizinhos = (BitSet) grafoCopia.getVizinhos(verticeMax).clone();
            if(vizinhos.isEmpty()) break;
            for (int w = vizinhos.nextSetBit(0); w >= 0; w = vizinhos.nextSetBit(w + 1)) {
                grafoCopia.removerAresta(verticeMax, w);
            }
        }

        return c;
    }
}
