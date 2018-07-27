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

    public Casa getCasa(int x, int y) {
        return casas[x][y];
    }

    public int getDimensao() {
        return casas.length;
    }

    // TODO move to Inicio
    public static void bemVindo() {
        System.out.println("Bem-vindo ao labinTOP!");
    }

    // TODO move to Saida
    public static void parabens() {
        System.out.println("Parabens! Voce resolveu o labinTOP!");
    }

    public void exibirLabirinto() {
        for (int i = 0; i < getDimensao(); i++) {
            for (int j = 0; j < getDimensao(); j++) {
                System.out.print(getCasa(i, j).getTipo());
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
                    System.out.print(getCasa(i, j).getTipo());
                }
            }

            System.out.println();
        }
    }
}
