package labirintop;

public class Casa {

    private int x;
    private int y;
    private TipoCasa tipoCasa;
    private boolean visitado;
    private Casa pai;

    public Casa(int x, int y, TipoCasa tipoCasa) {
        this.x = x;
        this.y = y;
        this.tipoCasa = tipoCasa;
        visitado = false;
        pai = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TipoCasa getTipoCasa() {
        return tipoCasa;
    }

    public void setTipoCasa(TipoCasa tipoCasa) {
        this.tipoCasa = tipoCasa;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public Casa getPai() {
        return pai;
    }

    public void setPai(Casa pai) {
        this.pai = pai;
    }
}
