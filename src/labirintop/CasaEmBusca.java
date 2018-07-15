package labirintop;

public class CasaEmBusca extends Casa {

    private boolean visitado;
    private CasaEmBusca pai;

    public CasaEmBusca(int x, int y, char tipo) {
        super(x, y, tipo);
        visitado = false;
        pai = null;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public CasaEmBusca getPai() {
        return pai;
    }

    public void setPai(CasaEmBusca pai) {
        this.pai = pai;
    }
}
