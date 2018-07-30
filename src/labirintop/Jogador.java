package labirintop;

public abstract class Jogador {

    protected Labirinto labirinto;

    public Jogador(Labirinto labirinto) {
        this.labirinto = labirinto;
    }

    public abstract void encontrarSaida();
}