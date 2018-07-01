/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirintop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author kleverson
 */
public class FuncoesGerais {
    public static String convertFileToString(String file){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            char[] buf = new char[1024];
            int len;
            while ((len  = in.read(buf, 0, buf.length)) > 0) {
                sb.append(buf, 0, len);
            }
            in.close();
        } catch (IOException e) {
        }
        return sb.toString();
    }
    
    public static int reconhecerQuantidadeLinhasLabirinto(String labirinto) {
        try{
            String primeiraLinha = labirinto.split("\n")[0];
            return Integer.valueOf(primeiraLinha.split("x")[0]);
        } catch (Exception ex){
            System.out.println("Erro ao reconhecer a quantidade de casas do labirinto");
            throw ex;
        }
    }
    
    public static int reconhecerQuantidadeColunasLabirinto(String labirinto) {
        try{
            String primeiraLinha = labirinto.split("\n")[0];
            return Integer.valueOf(primeiraLinha.split("x")[1]);
        } catch (Exception ex){
            System.out.println("Erro ao reconhecer a quantidade de casas do labirinto");
            throw ex;
        }
    }
    
    public static CasaLabirinto[][] passarTXTparaMatriz(String txtlabirinto, int x, int y) {
        try{
            //Criar matriz de labirinto
            CasaLabirinto[][] labirintop = new CasaLabirinto[x][y];
            
            //Ter linhas do arquivo
            String txtLinhas[] = txtlabirinto.split("\n");
            
            //Verifica se a quantidade de linhas bate
            if(x != (txtLinhas.length -1)){
                System.out.println("Quantidade de linhas informada e a quantidade real no arquivo não batem, você tá fazendo merda");
                throw null;
            }
            
            for(int i=0; i < x; i++){
                //Pegar linha atual
                String linha = txtLinhas[i+1];
                for(int j=0; j < y; j++){
                    
                    try{
                    //Pegar caractere atual
                    String caractere = linha.substring(j, j+1);
                    
                    //Salvar caractere na posicao da Matriz
                    switch (caractere){
                            case "P":
                                //Ponto de partida
                                labirintop[i][j] = new CasaLabirinto(i, j, true, false, false, true);
                                break;
                            case "o":
                                //Ponto livre para andar
                                labirintop[i][j] = new CasaLabirinto(i, j, false, false, false, true);
                                break;
                            case "-":
                                //Obstaculo
                                labirintop[i][j] = new CasaLabirinto(i, j, false, false, true, false);
                                break;
                            case "C":
                                //Ponto de chegada
                                labirintop[i][j] = new CasaLabirinto(i, j, false, true, false, true);
                                break;
                            default:
                                System.out.print(j);
                    }
                    } catch(Exception ex){
                        System.out.println("Quantidade de colunas informada e a quantidade real no arquivo não batem, você tá fazendo merda");
                        throw null;
                    }
                }
            }
            
            return labirintop;
        } catch (Exception ex){
            System.out.println("Erro ao reconhecer a quantidade de casas do labirinto");
            throw ex;
        }
    }
}
