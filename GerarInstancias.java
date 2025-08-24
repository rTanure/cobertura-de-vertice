import src.Argumentos;
import src.Gerador;
import src.Grafo;

public class GerarInstancias {
    public static void main(String[] args) {
        Argumentos argumentos = new Argumentos(args);
        System.out.println("=========== Gerando a instancia ===========");

        long inicio = System.currentTimeMillis();
        Grafo grafo = Gerador.grafoComCoberturaMinima(
                argumentos.getN(),
                argumentos.getK(),
                argumentos.getD()
        );
        long fim = System.currentTimeMillis();

        grafo.salvarArquivo(argumentos.getNome());

        System.out.println("> Nome do grafo:                  " + argumentos.getNome());
        System.out.println("> N de vertices:                  " + argumentos.getN());
        System.out.println("> Tamanho da cobertura minima:    " + argumentos.getK());
        System.out.println("> Densidade relativa:             " + argumentos.getD());
        System.out.println("> Densidade do grafo:             " + grafo.densidade());
        System.out.println("> N de arestas:                   " + grafo.getNumArestas());
        System.out.println("> Tempo de execucao (ms):         " + (fim - inicio));
    }
}
