package labirintop;

public abstract class Casa {

    private int x;
    private int y;
    private boolean visitado;
    private Casa pai;

    public Casa(int x, int y) {
        this.x = x;
        this.y = y;
        visitado = false;
        pai = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public abstract void mensagem();
    public abstract char getChar();
}
