package labirintop;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Principal {

    public static void main(String[] args) {

        String txtLabirinto = "";

        try {
            txtLabirinto = lerLabirinto("resources/labirinto1.txt", StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo");
        }

        Labirinto labirinto = construirLabirinto(txtLabirinto);

        Jogador jogador = new Jogador(labirinto);
        jogador.encontrarSaida();
        //jogador.jogarManual();
    }

    public static String lerLabirinto(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static Labirinto construirLabirinto(String labirinto) {
        String linhas[] = labirinto.replace("\r\n", "\n").replace("\uFEFF","").split("\n");
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
