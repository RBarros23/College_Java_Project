public class Equipamento {
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
}
