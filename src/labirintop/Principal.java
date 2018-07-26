package labirintop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {

    public static void main(String[] args) {
        String txtLabirinto = fileToString("resources/Labirinto1.txt");

        Labirinto labirinto = construirLabirinto(txtLabirinto);

        Jogador jogador = new Jogador(labirinto);
        jogador.encontrarSaida();
        //jogador.jogarManual();
    }

    public static String fileToString(String file) {
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            char[] buf = new char[1024];
            int len;

            while ((len = in.read(buf, 0, buf.length)) > 0) {
                sb.append(buf, 0, len);
            }

            in.close();

        } catch (IOException e) {
            System.out.println("Erro na conversão do arquivo");
        }

        return sb.toString();
    }

    public static Labirinto construirLabirinto(String txtLabirinto) {
        String linhas[] = txtLabirinto.split("\n");
        int dimensao = linhas.length;

        Casa[][] casas = new Casa[dimensao][dimensao];
        Casa inicio = null;

        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                char caractere = linhas[i].charAt(j);

                if (caractere == FuncoesGerais.INICIO) {
                    inicio = new Casa(i, j, caractere);
                }

                casas[i][j] = new Casa(i, j, caractere);
            }
        }

        return new Labirinto(casas, inicio);
    }
}
