package src;

import java.util.BitSet;
import java.util.Map;
import java.util.Set;


// Método que verifica o certificado
public class VerificadorDeCertificado {
    public static boolean verificar(Grafo grafo, Set<Integer> vertices) {
        // Recupera a lista de adjacencias do grafo
        Map<Integer, BitSet> adjacencias = grafo.getAdjacencias();

        // Verifica se para cada aresta (u, v) do grafo, u ou v está no set vertives
        for (Map.Entry<Integer, BitSet> entry : adjacencias.entrySet()) {
            int u = entry.getKey();
            BitSet vizinhos = entry.getValue();

            for (int v = vizinhos.nextSetBit(0); v >= 0; v = vizinhos.nextSetBit(v + 1)) {
                if (!vertices.contains(u) && !vertices.contains(v)) {
                    // Se uma aresta não foi coberta, retorna false
                    return false;
                }
            }
        }

        // Se todas as arestas foram cobertas, retorna true
        return true;
    }
}
