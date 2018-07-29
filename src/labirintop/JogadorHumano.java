package labirintop;

import java.util.Scanner;

public final class JogadorHumano extends Jogador {

    public JogadorHumano(Labirinto labirinto) {
        super(labirinto);
    }

    @Override
    public void encontrarSaida() {
        System.out.println("Bem vindo ao labirinTOP!");

        Scanner scn = new Scanner(System.in);
        char movement;
        long startTime, endTime;

        startTime = System.currentTimeMillis();

        while (getLocal().getTipoCasa() != TipoCasa.SAIDA) {
            labirinto.exibirLabirinto(getLocal());

            System.out.println("[A]Esquerda   [W]Cima   [S]Baixo   [D]Direita");
            movement = Character.toLowerCase(scn.next().charAt(0));

            switch (movement) {
                case 'w':
                    moveUp();
                    break;
                case 's':
                    moveDown();
                    break;
                case 'a':
                    moveLeft();
                    break;
                case 'd':
                    moveRight();
                    break;
            }
        }

        endTime = System.currentTimeMillis();

        System.out.println("Parabéns! Você resolveu o labirinTOP!");
        System.out.println("Demorou " + ((endTime - startTime) / 1000) + " segundos");
    }

    private void moveLeft() {
        if (local.getY() > 0) {
            final Casa casaEsquerda = labirinto.getCasa(local.getX(), local.getY() - 1);

            if (casaEsquerda.getTipoCasa() != TipoCasa.PAREDE) {
                setLocal(casaEsquerda);
            }
        }
    }

    private void moveRight() {
        if (local.getY() < labirinto.getDimensao() - 1) {
            final Casa casaDireita = labirinto.getCasa(local.getX(), local.getY() + 1);

            if (casaDireita.getTipoCasa() != TipoCasa.PAREDE) {
                setLocal(casaDireita);
            }
        }
    }

    private void moveUp() {
        if (local.getX() > 0) {
            final Casa casaCima = labirinto.getCasa(local.getX() - 1, local.getY());

            if (casaCima.getTipoCasa() != TipoCasa.PAREDE) {
                setLocal(casaCima);
            }
        }
    }

    private void moveDown() {
        if (local.getX() < labirinto.getDimensao() - 1) {
            final Casa casaBaixo = labirinto.getCasa(local.getX() + 1, local.getY());

            if (casaBaixo.getTipoCasa() != TipoCasa.PAREDE) {
                setLocal(casaBaixo);
            }
        }
    }
}
