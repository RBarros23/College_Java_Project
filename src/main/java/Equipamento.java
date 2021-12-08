import java.io.Serializable;

public class Equipamento implements Serializable {
    private String designacao; //Única
    private String fabricante;
    private String modelo;
    private int potencia;
    private String tipo; //P - Painel ou A - Aerogerador

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
