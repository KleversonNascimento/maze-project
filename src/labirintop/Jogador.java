package labirintop;

import java.util.Scanner;

public class Jogador {

    private Labirinto labirinto;
    private Casa local;

    public Jogador(Labirinto labirinto) {
        this.labirinto = labirinto;
        this.local = labirinto.getInicio();
    }

    public Casa getLocal() {
        return local;
    }

    private void setLocal(Casa local) {
        this.local = local;
    }

    private void moveLeft() {
        if (local.getY() > 0) {
            final Casa casaEsquerda = labirinto.getCasa(local.getX(), local.getY() - 1);

            if (!(casaEsquerda instanceof Parede)) {
                setLocal(casaEsquerda);
            }
        }
    }

    private void moveRight() {
        if (local.getY() < labirinto.getDimensao() - 1) {
            final Casa casaDireita = labirinto.getCasa(local.getX(), local.getY() + 1);

            if (!(casaDireita instanceof Parede)) {
                setLocal(casaDireita);
            }
        }
    }

    private void moveUp() {
        if (local.getX() > 0) {
            final Casa casaCima = labirinto.getCasa(local.getX() - 1, local.getY());

            if (!(casaCima instanceof Parede)) {
                setLocal(casaCima);
            }
        }
    }

    private void moveDown() {
        if (local.getX() < labirinto.getDimensao() - 1) {
            final Casa casaCima = labirinto.getCasa(local.getX() + 1, local.getY());

            if (!(casaCima instanceof Parede)) {
                setLocal(casaCima);
            }
        }
    }

    public void encontrarSaida() {
        long startTime, endTime;

        startTime = System.currentTimeMillis();

        Pilha p = buscarMenorCaminho(labirinto);

        endTime = System.currentTimeMillis();

        while (!p.isVazio()) {
            Casa casa = (Casa) p.desempilhar();
            casa.mensagem();
        }

        System.out.println("Demorou " + (endTime - startTime) + " milisegundos");
    }

    private Pilha buscarMenorCaminho(Labirinto labirinto) {
        Inicio inicio = labirinto.getInicio();
        Casa fim = null;
        Fila f = new Fila();
        boolean achouFinal = false;

        inicio.setVisitado(true);
        f.enfileirar(inicio);

        while (!f.isVazio()) {
            Casa casa = (Casa) f.desenfileirar();

            if (casa.getX() > 0  && !achouFinal) {
                achouFinal = explorarCasa(labirinto.getCasa(casa.getX()-1, casa.getY()), casa, f);
            }

            if (casa.getX() < labirinto.getDimensao() - 1 && !achouFinal) {
                achouFinal = explorarCasa(labirinto.getCasa(casa.getX() + 1, casa.getY()), casa, f);
            }

            if (casa.getY() > 0 && !achouFinal) {
                achouFinal = explorarCasa(labirinto.getCasa(casa.getX(), casa.getY() - 1), casa, f);
            }

            if (casa.getY() < labirinto.getDimensao() - 1 && !achouFinal) {
                achouFinal = explorarCasa(labirinto.getCasa(casa.getX(), casa.getY() + 1), casa, f);
            }

            if (achouFinal) {
                while (!f.isUnico()) {
                    f.desenfileirar();
                }
                fim = (Casa) f.desenfileirar();
            }
        }

        Pilha p = new Pilha();

        Casa volta = labirinto.getCasa(fim.getX(), fim.getY());
        p.empilhar(volta);

        while (volta.getPai() != null) {
            volta = volta.getPai();
            p.empilhar(volta);
        }

        return p;
    }

    private boolean explorarCasa(Casa novo, Casa ant, Fila f) {
        if (novo.isVisitado() || novo instanceof Parede) {
            return false;
        } else {
            novo.setVisitado(true);
            novo.setPai(ant);
            f.enfileirar(novo);

            return novo instanceof Saida;
        }
    }

    public void jogarManual() {
        Scanner scn = new Scanner(System.in);
        char movement;
        long startTime, endTime;

        startTime = System.currentTimeMillis();

        while (!(getLocal() instanceof Saida)) {
            labirinto.exibirLabirinto(getLocal());

            System.out.println("[A]Left   [W]Up   [S]Down   [D]Right");
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
                default:
                    break;
            }
        }

        endTime = System.currentTimeMillis();

        scn.close();

        System.out.println("Venceu");
        System.out.println("Demorou " + (endTime - startTime) / 1000 + " segundos");
    }
}
