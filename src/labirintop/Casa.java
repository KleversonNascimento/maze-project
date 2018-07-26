package labirintop;

public class Casa {

    private int x;
    private int y;
    private char tipo;
    private boolean visitado;
    private Casa pai;

    public Casa(int x, int y, char tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        visitado = false;
        pai = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getTipo() {
        return tipo;
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
