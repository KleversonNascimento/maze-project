package labirintop;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        String nomeLabirinto, txtLabirinto = "";
        char tipoJogador;

        System.out.println("Em qual labirinto você quer jogar?");
        nomeLabirinto = scn.next();

        try {
            txtLabirinto = lerLabirinto("resources/" + nomeLabirinto + ".txt", StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo");
        }

        Labirinto labirinto = construirLabirinto(txtLabirinto);

        System.out.println("Escolha o tipo de jogador:");
        System.out.println("[H]umano   [M]áquina");

        tipoJogador = scn.next().charAt(0);

        Jogador jogador = new Jogador(labirinto);

        if (tipoJogador == 'H') {
            jogador.jogarManual();
        } else if (tipoJogador == 'M') {
            jogador.encontrarSaida();
        }

        scn.close();
    }

    public static String lerLabirinto(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));

        return new String(encoded, encoding);
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
