package labirintop;

import java.util.Scanner;

public class Principal {
    
    static String TXTLABIRINTO;
    static int posicoesX, posicoesY, inicioX, inicioY;
    static boolean labirintoTemInicio = false, labirintoTemFim = false;
    
    public static void main(String[] args) {
        //Ler arquivo
        TXTLABIRINTO = FuncoesGerais.convertFileToString("res/testeLabirinto.txt");

        //Descobrir quantas posicoes terá o labirinto
        posicoesX = FuncoesGerais.reconhecerQuantidadeLinhasLabirinto(TXTLABIRINTO);
        posicoesY = FuncoesGerais.reconhecerQuantidadeColunasLabirinto(TXTLABIRINTO);
        System.out.println("linhas:" + posicoesX + ", colunas: " + posicoesY);

        //Criar matriz com o labirinto do arquivo
        Casa[][] labirintop = FuncoesGerais.passarTXTparaMatriz(TXTLABIRINTO, posicoesX, posicoesY);

        //Mostrar e validar propriedades do labirinto
        for (int i = 0; i < posicoesX; i++) {
            for (int j = 0; j < posicoesY; j++) {
                if (labirintop[i][j].getTipo() == Casa.INICIO) {
                    if (labirintoTemInicio) {
                        System.out.println("Labirinto tem mais de um inicio");
                        throw null;
                    } else {
                        inicioX = i;
                        inicioY = j;
                        labirintoTemInicio = true;
                    }
                } else if (labirintop[i][j].getTipo() == Casa.FIM) {
                    if (labirintoTemFim) {
                        System.out.println("Labirinto tem mais de um fim");
                        throw null;
                    } else {
                        labirintoTemFim = true;
                    }
                }
            }
        }
        
        if ((!labirintoTemInicio) || (!labirintoTemFim)) {
            System.out.println("Esse labirinto não tem fim ou não tem inicio");
            throw null;
        }
        
        jogar(labirintop);
        
        FuncoesGerais.resolverLabirinto(labirintop, inicioX, inicioY);
    }
    
    public static void jogar(Casa[][] labirinto) {
        Personagem player = new Personagem(labirinto[inicioX][inicioY]);
        Scanner scn = new Scanner(System.in);
        char movement;
        long startTime, endTime;

        startTime = System.currentTimeMillis();

        while (player.getLocal().getTipo() != Casa.FIM) {
            for (int i = 0; i < 100; ++i) {
                System.out.println();
            }

            FuncoesGerais.exibirLabirinto(labirinto, player);

            System.out.println("[A]Left   [W]Up   [S]Down   [D]Right");
            movement = scn.next().charAt(0);

            switch (movement) {
                case 'w':
                    player.moveUp(labirinto);
                    break;
                case 's':
                    player.moveDown(labirinto);
                    break;
                case 'a':
                    player.moveLeft(labirinto);
                    break;
                case 'd':
                    player.moveRight(labirinto);
                    break;
                default:
                    break;
            }
        }

        endTime = System.currentTimeMillis();

        scn.close();

        System.out.println("Venceu");
        System.out.println("Demorou " + (endTime - startTime) / 1000
                + " segundos");
    }
}
