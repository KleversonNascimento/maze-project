package labirintop;

public class JogadorMaquina extends Jogador {

    public JogadorMaquina(Labirinto labirinto) {
        super(labirinto);
    }

    @Override
    public void encontrarSaida() {
        long startTime, endTime;

        startTime = System.currentTimeMillis();

        Pilha p = buscarMenorCaminho(labirinto);

        endTime = System.currentTimeMillis();

        while (!p.isVazio()) {
            Casa casa = (Casa) p.desempilhar();

            if (casa.getTipoCasa() != TipoCasa.INICIO && casa.getTipoCasa() != TipoCasa.SAIDA) {
                labirinto.getCasa(casa.getX(), casa.getY()).setTipoCasa(TipoCasa.MARCADOR);
            }
        }

        labirinto.exibir();

        System.out.println("Demorou " + (endTime - startTime) + " milisegundos");
    }

    private Pilha buscarMenorCaminho(Labirinto labirinto) {
        Casa inicio = labirinto.getInicio();
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
        if (novo.isVisitado() || (novo.getTipoCasa() == TipoCasa.PAREDE)) {
            return false;
        } else {
            novo.setVisitado(true);
            novo.setPai(ant);
            f.enfileirar(novo);

            return (novo.getTipoCasa() == TipoCasa.SAIDA);
        }
    }
}
