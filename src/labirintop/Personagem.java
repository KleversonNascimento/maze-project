package labirintop;

public class Personagem {

    static final public char IMG = '@';
    private Casa local;

    public Personagem(Casa inicio) {
        this.local = inicio;
    }

    public Casa getLocal() {
        return local;
    }

    public void moveLeft(Casa[][] labirinto) {
        if (local.getY() > 0
                && labirinto[local.getX()][local.getY() - 1].getTipo() != Casa.MURO) {
            local = labirinto[local.getX()][local.getY() - 1];
        }
    }

    public void moveRight(Casa[][] labirinto) {
        if (local.getY() < labirinto[0].length - 1
                && labirinto[local.getX()][local.getY() + 1].getTipo() != Casa.MURO) {
            local = labirinto[local.getX()][local.getY() + 1];
        }
    }

    public void moveUp(Casa[][] labirinto) {
        if (local.getX() > 0
                && labirinto[local.getX() - 1][local.getY()].getTipo() != Casa.MURO) {
            local = labirinto[local.getX() - 1][local.getY()];
        }
    }

    public void moveDown(Casa[][] labirinto) {
        if (local.getX() < labirinto.length - 1
                && labirinto[local.getX() + 1][local.getY()].getTipo() != Casa.MURO) {
            local = labirinto[local.getX() + 1][local.getY()];
        }
    }
}
