package src;

import java.io.*;
import java.util.*;

public class Grafo{
  private Map<Integer, BitSet> adjacencias;
  private Integer numArestas;

  public Integer getNumArestas() {
    return this.numArestas;
  }

  // Construtor - Grafo vazio
  public Grafo() {
    this.numArestas = 0;
    this.adjacencias = new HashMap<>();
  }

  // Construtor - Grafo com 'numVertices' vertices sem arestas
  public Grafo(Integer numVertices) {
    this.adjacencias = new HashMap<>();
    for (int i = 0; i < numVertices; i++)
      this.adjacencias.put(i, new BitSet());
    this.numArestas = 0;
  }

  // Retorna um set com todos os vertices do grafo
  public Set<Integer> getVertices() {
    return this.adjacencias.keySet();
  }

  // Retorna o BitSet que representa os vizinhos do vértice u
  public BitSet getVizinhos(int u) {
    return adjacencias.get(u);
  }

  // Retorna a lista de adjacencias do grafo
  public Map<Integer, BitSet> getAdjacencias() {
    return this.adjacencias;
  }

  // Retorna uma aresta arbitraria
  public Aresta getArestaArbitraria() {
    if(this.numArestas == 0) return null;

    Set<Integer> vertices = adjacencias.keySet();

    for(Integer v : vertices) {
      BitSet vizinhos = adjacencias.get(v);
      for (int u = vizinhos.nextSetBit(0); u >= 0; u = vizinhos.nextSetBit(u + 1)){
        return new Aresta(u, v);
      }
    }
    return null;
  }

  public void addVertice(int v) {
    this.adjacencias.put(v, new BitSet());
  }

  public void addAresta(int u,  int v) {
    BitSet vizinhosU = this.adjacencias.get(u);
    BitSet vizinhosV = this.adjacencias.get(v);

    if(!vizinhosU.get(v)) {
      vizinhosU.set(v);
      vizinhosV.set(u);
      this.numArestas++;
    }
  }

  public void removerAresta(int u, int v) {
    BitSet vizinhosU = this.adjacencias.get(u);
    BitSet vizinhosV = this.adjacencias.get(v);

    if(vizinhosU.get(u) || vizinhosV.get(u)) {
      vizinhosU.clear(v);
      vizinhosV.clear(u);
      this.numArestas--;
    }
  }

  public void removerVertice(int u) {
    BitSet vizinhos = this.adjacencias.get(u);

    // Remove todas as arestas que vão de v para u
    for (int v = vizinhos.nextSetBit(0); v >= 0; v = vizinhos.nextSetBit(v +1)) {
      removerAresta(v, u);
    }

    this.adjacencias.remove(u);
  }

  private void setVizinhos(int u, BitSet bitSet) {
    this.adjacencias.put(u, (BitSet) bitSet.clone());
  }

  private void setNumArestas(int numArestas) {
    this.numArestas = numArestas;
  }

  public Grafo copia() {
    Grafo grafo = new Grafo();

    for (Map.Entry<Integer, BitSet> entry : this.adjacencias.entrySet()) {
      int vertice = entry.getKey();
      BitSet vizinhos = entry.getValue();

      grafo.addVertice(vertice);
      grafo.setVizinhos(vertice, vizinhos);
      grafo.setNumArestas(this.numArestas);

      grafo.adjacencias.get(vertice).or((BitSet) vizinhos.clone());
    }

    return grafo;
  }

  public boolean verificarAdjacencia(int u, int v) {
    BitSet vizinhos = this.adjacencias.get(u);
    return vizinhos.get(v);
  }

  public Grafo complemento() {
    Grafo grafo = new Grafo();

    for (Integer v : this.adjacencias.keySet()) {
      grafo.addVertice(v);
    }

    for (Integer u : this.adjacencias.keySet()) {
      for (Integer v : this.adjacencias.keySet()) {
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

  public String salvarArquivo(String nome) {
    String path = nome + ".txt";

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
      for (Map.Entry<Integer, BitSet> entry : this.adjacencias.entrySet()) {
        Integer vertice = entry.getKey();
        BitSet vizinhos = entry.getValue();

        bw.write(vertice.toString());
        for (int vizinho = vizinhos.nextSetBit(0); vizinho >= 0; vizinho = vizinhos.nextSetBit(vizinho + 1)) {
          bw.write(" " + vizinho);
        }
        bw.newLine();
      }
    } catch (IOException e) {
      System.out.println(e);
    }

    return path;
  }

  public static void main(String[] args) {
    Grafo grafo = GeradorInstancias.instanciaAleatoria(5, 0.2);
    grafo.salvarArquivo("./");
    grafo.complemento().salvarArquivo("./");
  }
}