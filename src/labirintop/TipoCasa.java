package labirintop;

public enum TipoCasa {
    INICIO('I'),
    SAIDA('S'),
    CAMINHO(' '),
    PAREDE('#'),
    MARCADOR('.');

    private char caractere;

    TipoCasa(char caractere) {
        this.caractere = caractere;
    }

    public char getCaractere() {
        return caractere;
    }
}
