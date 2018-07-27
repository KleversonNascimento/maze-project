package labirintop;

public class Parede extends Casa {

    public Parede(int x, int y) {
        super(x, y);
    }

    public char getChar() {
        return '#';
    }

    public void mensagem() {
        System.out.println("Isso eh uma parede.");
    }
}
