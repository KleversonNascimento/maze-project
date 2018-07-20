package labirintop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FuncoesGerais {

    public static String convertFileToString(String file) {
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

    public static int reconhecerQuantidadeLinhasLabirinto(String labirinto) {
        try {
            String primeiraLinha = labirinto.split("\n")[0];
            return Integer.valueOf(primeiraLinha.split("x")[0]);
        } catch (Exception ex) {
            System.out.println("Erro ao reconhecer a quantidade de linhas do labirinto");
            throw ex;
        }
    }

    public static int reconhecerQuantidadeColunasLabirinto(String labirinto) {
        try {
            String primeiraLinha = labirinto.split("\n")[0];
            return Integer.valueOf(primeiraLinha.split("x")[1]);
        } catch (Exception ex) {
            System.out.println("Erro ao reconhecer a quantidade de colunas do labirinto");
            throw ex;
        }
    }

    public static Casa[][] passarTXTparaMatriz(String txtlabirinto, int x, int y) {
        try {
            //Criar matriz de labirinto
            Casa[][] labirintop = new Casa[x][y];

            //Ter linhas do arquivo
            String txtLinhas[] = txtlabirinto.split("\n");

            //Verifica se a quantidade de linhas bate
            if (x != (txtLinhas.length - 1)) {
                System.out.println("Quantidade de linhas informada e a quantidade real no arquivo não batem, você tá fazendo merda");
                throw null;
            }

            for (int i = 0; i < x; i++) {
                //Pegar linha atual
                String linha = txtLinhas[i + 1];
                for (int j = 0; j < y; j++) {

                    try {
                        //Pegar caractere atual
                        char caractere = linha.substring(j, j + 1).charAt(0);

                        //Salvar caractere na posicao da Matriz
                        labirintop[i][j] = new Casa(i, j, caractere);
                    } catch (Exception ex) {
                        System.out.println("Quantidade de colunas informada e a quantidade real no arquivo não batem, você tá fazendo merda");
                        throw null;
                    }
                }
            }

            return labirintop;
        } catch (Exception ex) {
            System.out.println("Erro ao reconhecer a quantidade de casas do labirinto");
            throw ex;
        }
    }

    public static void resolverLabirinto(Casa[][] labirintoOriginal, int inicio_x, int inicio_y) {
        long startTime, endTime;

        startTime = System.currentTimeMillis();

        Pilha p = buscarMenorCaminho(labirintoOriginal, inicio_x, inicio_y);

        endTime = System.currentTimeMillis();

        while (!p.isVazio()) {
            Casa casa = (Casa) p.desempilhar();
            System.out.println("X: " + casa.getX() + "\tY: " + casa.getY());
        }

        System.out.println("Demorou " + (endTime - startTime) + " milisegundos");
    }

    private static Pilha buscarMenorCaminho(Casa[][] labirintoOriginal, int inicio_x, int inicio_y) {
        CasaEmBusca[][] labirinto = new CasaEmBusca[labirintoOriginal.length][labirintoOriginal[0].length];
        for (int i = 0; i < labirintoOriginal.length; i++) {
            for (int j = 0; j < labirintoOriginal[0].length; j++) {
                labirinto[i][j] = new CasaEmBusca(labirintoOriginal[i][j].getX(), labirintoOriginal[i][j].getY(), labirintoOriginal[i][j].getTipo());
            }
        }
        CasaEmBusca inicio = labirinto[inicio_x][inicio_y];
        CasaEmBusca fim = inicio;
        Fila f = new Fila();
        boolean achouFinal;

        inicio.setVisitado(true);
        f.enfileirar(inicio);
        achouFinal = false;

        while (!f.isVazio()) {
            CasaEmBusca casa = (CasaEmBusca) f.desenfileirar();

            if (casa.getX() > 0) {
                achouFinal = explorarCasa(labirinto[casa.getX() - 1][casa.getY()], casa, f);
            }

            if (casa.getX() < labirinto.length - 1 && !achouFinal) {
                achouFinal = explorarCasa(labirinto[casa.getX() + 1][casa.getY()], casa, f);
            }

            if (casa.getY() > 0 && !achouFinal) {
                achouFinal = explorarCasa(labirinto[casa.getX()][casa.getY() - 1], casa, f);
            }

            if (casa.getY() < labirinto[0].length - 1 && !achouFinal) {
                achouFinal = explorarCasa(labirinto[casa.getX()][casa.getY() + 1], casa, f);
            }

            if (achouFinal) {
                while (!f.isUnico()) {
                    f.desenfileirar();
                }
                fim = (CasaEmBusca) f.desenfileirar();
            }
        }

        Pilha p = new Pilha();

        CasaEmBusca volta = labirinto[fim.getX()][fim.getY()];
        p.empilhar(volta);

        while (volta.getPai() != null) {
            volta = volta.getPai();
            p.empilhar(volta);
        }

        return p;
    }

    private static boolean explorarCasa(CasaEmBusca novo, CasaEmBusca ant, Fila f) {
        if (novo.isVisitado() || novo.getTipo() == '-') {
            return false;
        } else {
            novo.setVisitado(true);
            novo.setPai(ant);
            f.enfileirar(novo);
            return novo.getTipo() == 'C';
        }
    }
}
