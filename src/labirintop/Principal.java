package labirintop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        try {
            int dificuldade;
            String txtLabirinto;
            Labirinto labirinto;
            char tipoJogador;

            dificuldade = escolherDificulade();

            txtLabirinto = lerLabirinto("resources/lab" + dificuldade + ".txt");

            labirinto = construirLabirinto(txtLabirinto);

            tipoJogador = escolherTipoJogador();

            Jogador jogador = new Jogador(labirinto);

            if (Character.toUpperCase(tipoJogador) == 'H') {
                jogador.jogarManual();
            } else if (Character.toUpperCase(tipoJogador) == 'M') {
                jogador.encontrarSaida();
            }

        } catch (Exception e) {
            System.out.println("ERRO");
            System.exit(1);
        }
    }

    public static int escolherDificulade() {
        Scanner scn = new Scanner(System.in);

        System.out.println("Em qual labirinto você quer jogar?");
        System.out.println("1 - Muito facil");
        System.out.println("2 - Facil");
        System.out.println("3 - Medio");
        System.out.println("4 - Dificil");
        System.out.println("5 - Muito dificil");

        return scn.nextInt();
    }

    public static char escolherTipoJogador() {
        Scanner scn = new Scanner(System.in);

        System.out.println("Escolha o tipo de jogador:");
        System.out.println("[H]umano   [M]áquina");

        return scn.next().charAt(0);
    }

    public static String lerLabirinto(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));

        return new String(encoded);
    }

    public static Labirinto construirLabirinto(String labirinto) {
        String linhas[] = labirinto.replace("\r\n", "\n").replace("\uFEFF","").split("\n");
        int dimensao = linhas.length;

        Casa[][] casas = new Casa[dimensao][dimensao];
        Inicio inicio = null;

        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                char caractere = linhas[i].charAt(j);

                switch (caractere) {
                    case '#':
                        casas[i][j] = new Parede(i, j);
                        break;
                    case ' ':
                        casas[i][j] = new Caminho(i, j);
                        break;
                    case 'I':
                        inicio = new Inicio(i, j);
                        casas[i][j] = inicio;
                        break;
                    case 'S':
                        casas[i][j] = new Saida(i, j);
                        break;
                    default:
                        break;
                }
            }
        }

        return new Labirinto(casas, inicio);
    }
}
