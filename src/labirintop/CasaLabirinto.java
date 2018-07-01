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
public class CasaLabirinto {
    private int posX, posY;
    private boolean ehInicio, ehFim, ehObstaculo, ehLivre;

    public CasaLabirinto(int posX, int posY, boolean ehInicio, boolean ehFim, boolean ehObstaculo, boolean ehLivre) {
        this.posX = posX;
        this.posY = posY;
        this.ehInicio = ehInicio;
        this.ehFim = ehFim;
        this.ehObstaculo = ehObstaculo;
        this.ehLivre = ehLivre;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isEhInicio() {
        return ehInicio;
    }

    public void setEhInicio(boolean ehInicio) {
        this.ehInicio = ehInicio;
    }

    public boolean isEhFim() {
        return ehFim;
    }

    public void setEhFim(boolean ehFim) {
        this.ehFim = ehFim;
    }

    public boolean isEhObstaculo() {
        return ehObstaculo;
    }

    public void setEhObstaculo(boolean ehObstaculo) {
        this.ehObstaculo = ehObstaculo;
    }

    public boolean isEhLivre() {
        return ehLivre;
    }

    public void setEhLivre(boolean ehLivre) {
        this.ehLivre = ehLivre;
    }
    
    
    
}
