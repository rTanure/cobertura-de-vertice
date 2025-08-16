package src;

import java.io.*;
import java.util.*;

public class Grafo{
  private Map<Integer, List<Integer>> adjacencias;

  public Grafo() {
    adjacencias = new HashMap<>();
  }

  public void addVertice(int v) {
    adjacencias.putIfAbsent(v, new ArrayList<>());
  }

  public void addAresta(int u,  int v) {
    List<Integer> vizinhos = adjacencias.get(u);

    int index = Collections.binarySearch(vizinhos, v);

    if(index < 0)
      index = -index -1;

    vizinhos.add(index, v);
  }

  public boolean verificarAdjacencia(int u, int v) {
    List<Integer> vizinhos = adjacencias.get(u);
    int index = Collections.binarySearch(vizinhos, v);

    return index >= 0;
  }

  public Grafo complemento() {
    Grafo grafo = new Grafo();

    for (Integer v : adjacencias.keySet()) {
      grafo.addVertice(v);
    }

    for (Integer u : adjacencias.keySet()) {
      for (Integer v : adjacencias.keySet()) {
        if (!u.equals(v) && !this.verificarAdjacencia(u, v)) {
          grafo.addAresta(u, v);
        }
      }
    }

    return grafo;
  }

  public static Grafo lerArquivo(String path) {
    Grafo grafo = new Grafo();

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      for(String linha = br.readLine(); linha != null; linha = br.readLine()) {
        linha = linha.trim();
        if (linha.isEmpty()) continue;

        String[] partes = linha.split("\\s+");

        int vertice = Integer.parseInt(partes[0]);

        grafo.addVertice(vertice);

        for (int i = 1; i < partes.length; i++) {
          int vizinho = Integer.parseInt(partes[i]);
          grafo.addAresta(vertice, vizinho);
        }
      }
    } catch (IOException e) {
      System.err.println("Erro ao ler arquivo: " + path);
    }

    return grafo;
  }

  public String salvarArquivo(String diretorio) {
    String nome = UUID.randomUUID().toString();
    String path = diretorio + "/" + nome + ".txt";

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
      for (Map.Entry<Integer, List<Integer>> entry : adjacencias.entrySet()) {
        Integer vertice = entry.getKey();
        List<Integer> vizinhos = entry.getValue();

        bw.write(vertice.toString());
        for (Integer vizinho : vizinhos) {
          bw.write(" " + vizinho);
        }
        bw.newLine();
      }
    } catch (IOException e) {
      System.out.println(e);
    }

    return path;
  }
}