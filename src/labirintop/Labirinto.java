package labirintop;

public class Labirinto {

    private Casa[][] casas;
    private Casa inicio;

    public Labirinto(Casa[][] casas, Casa inicio) {
        this.casas = casas;
        this.inicio = inicio;
    }

    public Casa getInicio() {
        return inicio;
    }

    public Casa getCasa(int x, int y) throws IndexOutOfBoundsException {
        return casas[x][y];
    }

    public int getDimensao() {
        return casas.length;
    }

    public void exibir() {
        for (int i = 0; i < getDimensao(); i++) {
            for (int j = 0; j < getDimensao(); j++) {
                System.out.print(getCasa(i, j).getTipoCasa().getCaractere());
            }

            System.out.println();
        }
    }

    public void exibir(Casa local) {
        for (int i = 0; i < getDimensao(); i++) {
            for (int j = 0; j < getDimensao(); j++) {
                if (i == local.getX() && j == local.getY()) {
                    System.out.print('@');
                } else {
                    System.out.print(getCasa(i, j).getTipoCasa().getCaractere());
                }
            }

            System.out.println();
        }
    }
}
