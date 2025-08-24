package src;

// Classe para representar uma aresta no retorno dos métodos.
public class Aresta {
    private Integer u;
    private Integer v;

    public Aresta(Integer u, Integer v) {
        this.u = u;
        this.v = v;
    }

    public Integer getU() {
        return u;
    }

    public void setU(Integer u) {
        this.u = u;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
