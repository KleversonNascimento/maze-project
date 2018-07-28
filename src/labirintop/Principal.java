package labirintop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author  Denildo Braga
 * @author  Kleverson Nascimento
 * @author  Lucas Ferraz
 * @author  Matheus Henrique
 */

public class Principal {

    public static void main(String[] args) {

        try {
            int dificuldade;
            String txtLabirinto;
            Labirinto labirinto;
            Jogador jogador = null;
            char tipoJogador;

            dificuldade = escolherDificulade();

            txtLabirinto = lerLabirinto("resources/lab" + dificuldade + ".txt");

            labirinto = construirLabirinto(txtLabirinto);

            tipoJogador = escolherTipoJogador();

            if (tipoJogador == 'H') {
                jogador = new JogadorHumano(labirinto);
            } else if (tipoJogador == 'M') {
                jogador = new JogadorMaquina(labirinto);
            }

            jogador.encontrarSaida();

        } catch (Exception e) {
            System.out.println("ERRO");
            System.exit(1);
        }
    }

    private static int escolherDificulade() {
        Scanner scn = new Scanner(System.in);
        int dificuldade;

        do {
            System.out.println("Em qual labirinto voce quer jogar?");
            System.out.println("1 - Muito facil");
            System.out.println("2 - Facil");
            System.out.println("3 - Medio");
            System.out.println("4 - Dificil");
            System.out.println("5 - Muito dificil");

            dificuldade = scn.nextInt();
        } while (dificuldade < 1 || dificuldade > 5);

        return dificuldade;
    }

    private static char escolherTipoJogador() {
        Scanner scn = new Scanner(System.in);
        char tipoJogador;

        do {
            System.out.println("Escolha o tipo de jogador:");
            System.out.println("[H]umano    [M]aquina");

            tipoJogador = Character.toUpperCase(scn.next().charAt(0));
        } while (tipoJogador != 'H' && tipoJogador != 'M');

        return tipoJogador;
    }

    private static String lerLabirinto(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));

        return new String(encoded);
    }

    private static Labirinto construirLabirinto(String labirinto) {
        String linhas[] = labirinto.replace("\r\n", "\n").replace("\uFEFF","").split("\n");
        int dimensao = linhas.length;

        Casa[][] casas = new Casa[dimensao][dimensao];
        Casa inicio = null;

        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                char caractere = linhas[i].charAt(j);

                switch (caractere) {
                    case '#':
                        casas[i][j] = new Casa(i, j, TipoCasa.PAREDE);
                        break;
                    case ' ':
                        casas[i][j] = new Casa(i, j, TipoCasa.CAMINHO);
                        break;
                    case 'I':
                        casas[i][j] = new Casa(i, j, TipoCasa.INICIO);
                        break;
                    case 'S':
                        casas[i][j] = new Casa(i, j, TipoCasa.SAIDA);
                        break;
                    default:
                        break;
                }

                if (casas[i][j].getTipoCasa() == TipoCasa.INICIO) {
                    inicio = casas[i][j];
                }
            }
        }

        return new Labirinto(casas, inicio);
    }
}