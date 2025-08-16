package src;

import java.util.Random;

public class GeradorInstancias {
    public static Grafo instanciaAleatoria(Integer numVertices, Double densidade) {
        if(densidade < 0) densidade = 0.0;
        if(densidade > 1) densidade = 1.0;

        Grafo grafo = new Grafo();
        Random random = new Random();

        for(int i = 0; i < numVertices; i++)
            grafo.addVertice(i);

        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (random.nextDouble() < densidade) {
                    grafo.addAresta(i, j);
                    grafo.addAresta(j, i);
                }
            }
        }

        return grafo;
    };
}
