package labirintop;

public class Inicio extends Casa {

    public Inicio(int x, int y) {
        super(x, y);
    }

    public char getChar() {
        return 'I';
    }

    public void mensagem() {
        System.out.println("Bem-vindo ao labinTOP!");
    }
}
