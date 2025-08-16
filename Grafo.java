import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grafo{
  private int numVertices;
  private int numArestas;
  private Map<Integer, HashSet<Integer>> adjacencias;

  public Grafo() {
    this.numArestas = 0;
    this.numVertices = 0;
    this.adjacencias = new HashMap<>();
  }

  public void adicionarVertice(Integer v) {
    adjacencias.putIfAbsent(v, new HashSet<>());
    this.numVertices++;
  }

  public void adicionarAresta(Integer u, Integer v) {
    // Caso os vértices não existam, eles serão adicionados.
    if(!adjacencias.containsKey(u)) this.adicionarVertice(u);
    if(!adjacencias.containsKey(v)) this.adicionarVertice(v);

    boolean arestaExiste = adjacencias.get(u).contains(v);

    if(!arestaExiste) {
      adjacencias.get(u).add(v);
      adjacencias.get(v).add(u);
      this.numArestas++;
    }
  }

  public void removerAresta(Integer u, Integer v) {
    HashSet<Integer> vizinhosU = adjacencias.get(u);
    HashSet<Integer> vizinhosV = adjacencias.get(v);

    boolean arestaExiste = vizinhosU.contains(v);

    if(arestaExiste) {
      vizinhosU.remove(v);
      vizinhosV.remove(u);
    }

    this.numArestas--;
  }

  public static Grafo lerArquivo(String nomeArquivo) {
    Grafo grafo = new Grafo();

    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
      String linha = br.readLine();
      while (linha != null) {
        linha = linha.trim();

        String[] partes = linha.split("\\s+");

        if (partes.length != 2) {
          System.out.println("Linha inválida: " + linha);
          linha = br.readLine();
          continue;
        };

        int u = Integer.parseInt(partes[0]);
        int v = Integer.parseInt(partes[1]);

        grafo.adicionarAresta(u, v);
        linha = br.readLine();
      }
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    }

    return grafo;
  }

  public HashSet<Integer> getVizinhos(Integer u) {
    return this.adjacencias.get(u);
  }

  public String toString() {
    StringBuilder grafoStr = new StringBuilder();

    String num_vertices = "Número de vertices: " + this.numVertices + "\n";
    String num_arestas = "Número de arestas:   " + this.numArestas + "\n";

    grafoStr.append(num_vertices);
    grafoStr.append(num_arestas);

    for(Integer i : this.adjacencias.keySet()) {

      grafoStr.append(String.format("%4d: ", i));
      grafoStr.append(getVizinhos(i).toString()).append("\n");
    }

    return grafoStr.toString();
  }
}