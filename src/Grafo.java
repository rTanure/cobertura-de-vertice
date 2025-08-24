package src;

import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

// Classe que representa um grafo no algoritmo
public class Grafo{

  private static final String DIRETORIO_GRAFOS = "grafos";
  private Map<Integer, BitSet> adjacencias;
  private Integer numArestas;

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

  // Retorna o número de arestas do graof.
  // O(1)
  public Integer getNumArestas() {
    return this.numArestas;
  }

  // Retorna o grau de um vértice.
  // O(|V|)
  public Integer grauDoVertive(Integer v) {
    return this.adjacencias.get(v).cardinality();
  }

  // Retorna um set com todos os vertices do grafo
  // O(1)
  public Set<Integer> getVertices() {
    return this.adjacencias.keySet();
  }

  // Retorna o BitSet que representa os vizinhos do vértice u
  // O(1)
  public BitSet getVizinhos(int u) {
    return adjacencias.get(u);
  }

  // Retorna a lista de adjacencias do grafo
  // O(1)
  public Map<Integer, BitSet> getAdjacencias() {
    return this.adjacencias;
  }

  // Retorna uma aresta arbitraria
  // O(|V| + d), d -> grau do vértice retornado
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

  // Adiciona um novo vértice
  // O(1)
  public void addVertice(int v) {
    this.adjacencias.putIfAbsent(v, new BitSet());
  }

  // Adiciona uma aresta no grafo
  // O(1)
  public void addAresta(int u,  int v) {
    BitSet vizinhosU = this.adjacencias.get(u);
    BitSet vizinhosV = this.adjacencias.get(v);

    if(!vizinhosU.get(v)) {
      vizinhosU.set(v);
      vizinhosV.set(u);
      this.numArestas++;
    }
  }

  // Remove uma aresta no grafo
  // O(1)
  public void removerAresta(int u, int v) {
    BitSet vizinhosU = this.adjacencias.get(u);
    BitSet vizinhosV = this.adjacencias.get(v);

    if(vizinhosU.get(u) || vizinhosV.get(u)) {
      vizinhosU.clear(v);
      vizinhosV.clear(u);
      this.numArestas--;
    }
  }


  // Retorna a densidade do grafo
  // O(1)
  public double densidade() {
    Integer numVertices = adjacencias.keySet().size();
    return (double) (2 * this.numArestas) / (numVertices * (numVertices - 1));
  }

  // Clona um bitset para setar os vizinhos
  // O(|V|)
  private void setVizinhos(int u, BitSet bitSet) {
    this.adjacencias.put(u, (BitSet) bitSet.clone());
  }

  // Clona o numero de arestas
  // O(1)
  private void setNumArestas(int numArestas) {
    this.numArestas = numArestas;
  }

  // Retorna uma cópia do grafo
  // O(V + E)
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

  // Verifica se há aresta entre u e v
  // O(1)
  public boolean verificarAdjacencia(int u, int v) {
    BitSet vizinhos = this.adjacencias.get(u);
    return vizinhos.get(v);
  }

  // Retorna o grafo complementar
  // O(|V|^2)
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

  // Lê um grafo de um arquivo texto
  // O(V + E)
  public static Grafo lerArquivo(String nome) {
    Grafo grafo = new Grafo();
    String path = DIRETORIO_GRAFOS + "/" + nome + ".txt";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      for(String linha = br.readLine(); linha != null; linha = br.readLine()) {
        linha = linha.trim();
        if (linha.isEmpty()) continue;

        String[] partes = linha.split("\\s+");

        int vertice = Integer.parseInt(partes[0]);

        grafo.addVertice(vertice);

        for (int i = 1; i < partes.length; i++) {
          int vizinho = Integer.parseInt(partes[i]);
          grafo.addVertice(vizinho);
          grafo.addAresta(vertice, vizinho);
        }
      }
    } catch (IOException e) {
      System.err.println("Erro ao ler arquivo: " + path);
    }

    return grafo;
  }

  // Cria o diretório para salvar os grafos
  // O(1)
  private void criaDiretorioSeNaoExiste(String diretorio) {
    File dir = new File(diretorio);
    if (!dir.exists()) dir.mkdirs();
  }

  // Salva o grafo em um arquivo texto
  // O(V + E)
  public String salvarArquivo(String nome) {

    criaDiretorioSeNaoExiste(DIRETORIO_GRAFOS);

    if(nome == null || nome.isEmpty())
      nome = Timestamp.from(Instant.now()).toString();

    String path = DIRETORIO_GRAFOS + "/" + nome + ".txt";


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

  // Remove todas as arestas incidentes em um vértice
  // O(Grau do vértice),
  public  void removerArestasIncidentes(int vertice) {
    BitSet vizinhos = this.getVizinhos(vertice);
    for (int v = vizinhos.nextSetBit(0); v >= 0; v = vizinhos.nextSetBit(v + 1)) {
      this.removerAresta(vertice, v);
    }
  }
}