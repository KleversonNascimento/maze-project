/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirintop;
/**
 *
 * @author kleverson
 */
public class Labirintop {
    static String TXTLABIRINTO;
    static int posicoesX, posicoesY;
    static boolean labirintoTemInicio = false, labirintoTemFim = false;
    
    public static void main(String[] args) {
        //Ler arquivo
        TXTLABIRINTO = FuncoesGerais.convertFileToString("res/testeLabirinto.txt");
        
        //Descobrir quantas posicoes terá o labirinto
        posicoesX = FuncoesGerais.reconhecerQuantidadeLinhasLabirinto(TXTLABIRINTO);
        posicoesY = FuncoesGerais.reconhecerQuantidadeColunasLabirinto(TXTLABIRINTO);
        System.out.println("linhas:" + posicoesX + ", colunas: " + posicoesY);
        
        //Criar matriz com o labirinto do arquivo
        CasaLabirinto[][] labirintop = FuncoesGerais.passarTXTparaMatriz(TXTLABIRINTO, posicoesX, posicoesY);
        
        //Mostrar e validar propriedades do labirinto
        for(int i=0; i < posicoesX; i++){
            for(int j=0; j < posicoesY; j++){
                if(labirintop[i][j].isEhInicio()){
                    if(labirintoTemInicio){
                        System.out.println("Labirinto tem mais de um inicio, algo de errado não está certo");
                        throw null;
                    } else {
                        System.out.print("P");
                        labirintoTemInicio = true;
                    }
                } else if (labirintop[i][j].isEhFim()){
                    if(labirintoTemFim){
                        System.out.println("Labirinto tem mais de um fim, algo de errado não está certo");
                        throw null;
                    } else {
                        System.out.print("C");
                        labirintoTemFim = true;
                    }
                } else if (labirintop[i][j].isEhObstaculo()){
                    System.out.print("-");
                } else {
                    System.out.print("o");
                }
            }
            System.out.println("");
        }
        
        if((!labirintoTemInicio) || (!labirintoTemFim)){
            System.out.println("Esse labirinto não tem fim ou não tem inicio, me ajuda a te ajudar");
            throw null;
        }
        
    }
    
    
    
}
