package src;

import java.util.BitSet;
import java.util.Map;
import java.util.Set;

public class VerificadorDeCertificado {
    public static boolean verificar(Grafo grafo, Set<Integer> vertices) {
        Map<Integer, BitSet> adjacencias = grafo.getAdjacencias();

        for (Map.Entry<Integer, BitSet> entry : adjacencias.entrySet()) {
            int u = entry.getKey();
            BitSet vizinhos = entry.getValue();

            for (int v = vizinhos.nextSetBit(0); v >= 0; v = vizinhos.nextSetBit(v + 1)) {
                if (!vertices.contains(u) && !vertices.contains(v)) {
                    return false;
                }
            }
        }

        return true;
    }
}
