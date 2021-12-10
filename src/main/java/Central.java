import java.io.Serializable;
import java.util.ArrayList;

public class Central implements Serializable{
    private int numIdentificacao; //unico e sequencial definido no main
    private String designacao;
    private String localidade;
    private int potencia; //potencia instalada
    private int potenciaProduzidaAnual;
    private int[] potenciaCadaAno;
    private ArrayList<Empresas> donos = new ArrayList<>();
    int[] dataInauguracao;


    public Central() {}
    /**Construtor utilizado por Fotovoltaicos e Eolicas*/
    public Central(int numIdentificacao, String designacao,String localidade, int[] dataInauguracao, int potenciaProduzidaAnual, int[] potenciaCadaAno){
        this.numIdentificacao = numIdentificacao;
        this.designacao = designacao;
        this.localidade = localidade;
        this.dataInauguracao = dataInauguracao;
        this.potenciaProduzidaAnual = potenciaProduzidaAnual;
        this.potenciaCadaAno = potenciaCadaAno;
    }

    /**Construtor utilizado por Hidroeletricas*/
    public Central(int numIdentificacao, String designacao,String localidade, int potencia, int[] dataInauguracao, int potenciaProduzidaAnual, int[] potenciaCadaAno){
        this.numIdentificacao = numIdentificacao;
        this.designacao = designacao;
        this.localidade = localidade;
        this.potencia = potencia;
        this.dataInauguracao = dataInauguracao;
        this.potenciaProduzidaAnual = potenciaProduzidaAnual;
        this.potenciaCadaAno = potenciaCadaAno;
    }

    public void setDonos(Empresas donos) {
        this.donos.add(donos);
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public void setPotenciaCadaAno(int[] potenciaCadaAno) {
        this.potenciaCadaAno = potenciaCadaAno;
    }

    public int getNumIdentificacao() { // será utilizado para confirmar se já existe o numero ou nao
        return numIdentificacao;
    }

    public String getdataInauguracao() {
        return dataInauguracao[0] + "/" + dataInauguracao[1] + "/" + dataInauguracao[2];
    }

    public String getDesignacao() {
        return designacao;
    }

    public String getLocalidade() {
        return localidade;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getPotenciaProduzidaAnual() {
        return potenciaProduzidaAnual;
    }

    public ArrayList<Empresas> getDonos() {
        return donos;
    }

    @Override
    public String toString() {
        return "";
    }
}