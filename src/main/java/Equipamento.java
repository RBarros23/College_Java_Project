import java.io.Serializable;

public class Equipamento implements Serializable {
    private String designacao; //Única
    private String fabricante;
    private String modelo;
    private int potencia;
    private String tipo; //P - Painel ou A - Aerogerador
    private int numCentral; //numero de identificacao da central
    private int[] dataAssociado;

    public Equipamento(String designacao, String fabricante, String modelo, int potencia, String tipo) {
        this.designacao = designacao;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.potencia = potencia;
        this.tipo = tipo;
    }

    public String getDesignacao() {
        return designacao;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getNumCentral() {
        return numCentral;
    }

    public void setNumCentral(int numCentral) {
        this.numCentral = numCentral;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDataAssociado(int dia, int mes, int ano) {
        dataAssociado = new int[]{dia, mes, ano};
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "designacao='" + designacao + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", modelo='" + modelo + '\'' +
                ", potencia=" + potencia +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
