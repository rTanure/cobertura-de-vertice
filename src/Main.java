package src;

import java.util.Set;

public class Main{
    public static void main(String[] args) {
        //  Grafo grafo_a = GeradorInstancias.grafoComCoberturaMinima(10, 4, 1);
        Grafo grafo_b = GeradorInstancias.instanciaAleatoria(100, 0.3);

        // grafo_a.salvarArquivo("./");
        grafo_b.salvarArquivo("./");

        //  Set<Integer> resultado1 = CoberturaDeVertices.forcaBruta(grafo_a);




        Set<Integer>  forcaBruta = ForcaBrutaCobertura.combinacoes(grafo_b,4);

        CoberturaDeVerticesPorAresta coberturaDeVerticesPorAresta = new CoberturaDeVerticesPorAresta();
        Set<Integer> coberturaVertice = coberturaDeVerticesPorAresta.coberturaPorAresta(grafo_b,4);

        CoberturaPorNucleo coberturaPorNucleo = new CoberturaPorNucleo();
        Set<Integer> coberturaNucleo = coberturaPorNucleo.coberturaPorNucleo(grafo_b,4);




        boolean certificadoForcaBruta = VerificadorDeCertificado.verificar(grafo_b,forcaBruta);
        System.out.println("Força bruta");
        if(certificadoForcaBruta){
            System.out.println("Resposta certa!!" + certificadoForcaBruta);
        } else{
            System.out.println("Deu ruim" + certificadoForcaBruta);
        }
        System.out.println(forcaBruta);


        boolean certificadoCobArestas = VerificadorDeCertificado.verificar(grafo_b,coberturaVertice);
        System.out.println("Cobertura de vertice");
        if(certificadoCobArestas){
            System.out.println("Resposta certa!!" + certificadoCobArestas);
        } else{
            System.out.println("Deu ruim" + certificadoCobArestas);
        }
        System.out.println(coberturaVertice);


        boolean certificadoCobNucleo = VerificadorDeCertificado.verificar(grafo_b,coberturaNucleo);
        System.out.println("Cobertura de nucleo");
        if(certificadoCobArestas){
            System.out.println("Resposta certa!!" + certificadoCobNucleo);
        } else{
            System.out.println("Deu ruim" + certificadoCobNucleo);
        }
        System.out.println(coberturaNucleo);


    }
}