package labirintop;

public abstract class Jogador {

    protected Labirinto labirinto;
    protected Casa local;

    public Jogador(Labirinto labirinto) {
        this.labirinto = labirinto;
        this.local = labirinto.getInicio();
    }

    public Casa getLocal() {
        return local;
    }

    protected void setLocal(Casa local) {
        this.local = local;
    }

    public abstract void encontrarSaida();
}