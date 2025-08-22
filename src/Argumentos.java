package src;

import java.util.HashMap;
import java.util.Map;

public class Argumentos {
    private final Map<String, String> parametros = new HashMap<>();
    private String timestamp;

    public Argumentos(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            String arg = args[i];
            if (arg.startsWith("-")) {
                parametros.put(arg, args[i + 1]);
            }
        }

        this.timestamp = String.valueOf(System.currentTimeMillis());

    }

    // Representa a quantidade de vertices do grafo.
    // Valor padrão: 1000
    public int getN() {
        return Integer.parseInt(parametros.getOrDefault("-n", "1000"));
    }

    // Representa a cobertura mínima do grafo.
    // Valor padrão: 5
    public int getK() {
        return Integer.parseInt(parametros.getOrDefault("-k", "5"));
    }

    // Representa a densidade relativa do grafo.
    // Valor padrão: 0.5
    public Double getD() {
        return Double.parseDouble(parametros.getOrDefault("-d", "0.5"));
    }

    // Nome do grafo que será salvo
    // Valor padrão: timestamp atual
    public String getNome() {
        return parametros.getOrDefault("-nome", this.timestamp);
    }

}
