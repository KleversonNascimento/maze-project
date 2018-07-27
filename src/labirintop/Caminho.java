package labirintop;

public class Caminho extends Casa {

    public Caminho(int x, int y) {
        super(x, y);
    }

    public char getChar() {
        return ' ';
    }

    public void mensagem() {
        System.out.printf("Posicao atual: X: %d, Y: %d.%n",super.getX(), super.getY());
    }
}
