package labirintop;

public class Labirinto {

    private Casa[][] casas;
    private Inicio inicio;

    public Labirinto(Casa[][] casas, Inicio inicio) {
        this.casas = casas;
        this.inicio = inicio;
    }

    public Inicio getInicio() {
        return inicio;
    }

    public Casa getCasa(int x, int y) {
        if(x >= 0 && y >= 0 && x < getDimensao() && y < getDimensao()) {
            return casas[x][y];
        }
        else {
            return null;
        }
    }

    public int getDimensao() {
        return casas.length;
    }

    public void exibirLabirinto() {
        for (int i = 0; i < getDimensao(); i++) {
            for (int j = 0; j < getDimensao(); j++) {
                System.out.print(getCasa(i, j).getChar());
            }

            System.out.println();
        }
    }

    public void exibirLabirinto(Casa local) {
        for (int i = 0; i < getDimensao(); i++) {
            for (int j = 0; j < getDimensao(); j++) {
                if (i == local.getX() && j == local.getY()) {
                    System.out.print('@');
                } else {
                    System.out.print(getCasa(i, j).getChar());
                }
            }

            System.out.println();
        }
    }
}
