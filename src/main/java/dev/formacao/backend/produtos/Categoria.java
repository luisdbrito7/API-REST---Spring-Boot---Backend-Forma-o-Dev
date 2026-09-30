package dev.formacao.backend.produtos;

public enum Categoria {
    ROUPA("Roupa"),
    ELETRONICO("Eletrônico"),
    ESCRITORIO("Escritório"),
    MOVEIS("Móveis"),
    ACESSORIOS("Acessorios");

    private final String valor;

    Categoria(String valor){
        this.valor = valor;
    }
    public String getValor(){
        return this.valor;
    }
}
