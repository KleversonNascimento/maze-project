package labirintop;

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
                if (labirintop[i][j].getTipo() == 'P') {
                    if (labirintoTemInicio) {
                        System.out.println("Labirinto tem mais de um inicio, algo de errado não está certo");
                        throw null;
                    } else {
                        System.out.print("P");
                        inicioX = i;
                        inicioY = j;
                        labirintoTemInicio = true;
                    }
                } else if (labirintop[i][j].getTipo() == 'C') {
                    if (labirintoTemFim) {
                        System.out.println("Labirinto tem mais de um fim, algo de errado não está certo");
                        throw null;
                    } else {
                        System.out.print("C");
                        labirintoTemFim = true;
                    }
                } else if (labirintop[i][j].getTipo() == '-') {
                    System.out.print("-");
                } else {
                    System.out.print("o");
                }
            }
            System.out.println("");
        }
        
        if ((!labirintoTemInicio) || (!labirintoTemFim)) {
            System.out.println("Esse labirinto não tem fim ou não tem inicio, me ajuda a te ajudar");
            throw null;
        }
        
        FuncoesGerais.resolverLabirinto(labirintop, inicioX, inicioY);
    }
}
