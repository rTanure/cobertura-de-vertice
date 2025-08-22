import src.Argumentos;
import src.Gerador;
import src.Grafo;

public class GerarInstancias {
    public static void main(String[] args) {
        Argumentos argumentos = new Argumentos(args);

        Grafo grafo = Gerador.grafoComCoberturaMinima(
                argumentos.getN(),
                argumentos.getK(),
                argumentos.getD()
        );

        grafo.salvarArquivo(argumentos.getNome());
        Grafo grafo_2 = Grafo.lerArquivo(argumentos.getNome());
        grafo_2.salvarArquivo(argumentos.getNome() + "2");

        System.out.println("=========== Gerador de instâncias ===========");
        System.out.println("> Nome do grafo:                  " + argumentos.getNome());
        System.out.println("> N de vertices:                  " + argumentos.getN());
        System.out.println("> Tamanho da cobertura minima:    " + argumentos.getK());
        System.out.println("> Densidade relativa:             " + argumentos.getD());
        System.out.println("> Densidade do grafo:             " + grafo.densidade());
        System.out.println("> N de arestas:                   " + grafo.getNumArestas());
    }
}
