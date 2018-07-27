package labirintop;

public class Saida extends Casa {

    public Saida(int x, int y) {
        super(x, y);
    }

    public char getChar() {
        return 'S';
    }

    public void mensagem() {
        System.out.println("Parabens! Voce resolveu o labinTOP!");
    }
}
