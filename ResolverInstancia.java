import src.CoberturaDeVerticesPorAresta;
import src.Grafo;
import src.VerificadorDeCertificado;

import java.util.Arrays;
import java.util.Set;

public class ResolverInstancia {
    public static void main(String[] args) {
        System.out.println("=========== Resolvendo a intancia ===========");
        String arquivo = args[0];
        String arquivoSemTipo = arquivo.split("\\.")[0];
        Grafo grafo = Grafo.lerArquivo(arquivoSemTipo);

        long inicio = System.currentTimeMillis();
        Set<Integer> resultado = CoberturaDeVerticesPorAresta.resolver(grafo);
        long fim = System.currentTimeMillis();

        boolean verificacao = VerificadorDeCertificado.verificar(grafo, resultado);

        boolean mostrarResultado = false;
        for(String arg : args)
            if (arg.equals("-r")) {
                mostrarResultado = true;
                break;
            }

        System.out.println("> Nome do grafo:                  " + args[0]);
        System.out.println("> Tamanho da cobertura minima:    " + resultado.size());
        System.out.println("> Resultado da verificacao:       " + verificacao);
        System.out.println("> Tempo de execucao (ms):         " + (fim - inicio));
        if(mostrarResultado) {
            System.out.println("> Resultado do mostrar Grafo:     " + resultado);
        }
    }
}
