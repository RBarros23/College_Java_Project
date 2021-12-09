import java.io.Serializable;
import java.util.ArrayList;

public class Central implements Serializable{
    private int numIdentificacao; //unico e sequencial definido no main
    private String designacao;
    private String localidade;
    private int potencia; //potencia instalada
    private int potenciaProduzida; //potencia produzida desde que foi inaugurada
    private ArrayList<Empresas> donos = new ArrayList<>();
    int[] dataInauguracao;


    public Central() {}
    /**Construtor utilizado por Fotovoltaicos e Eolicas*/
    public Central(int numIdentificacao, String designacao,String localidade, int[] dataInauguracao){
        this.numIdentificacao = numIdentificacao;
        this.designacao = designacao;
        this.localidade = localidade;
        this.dataInauguracao = dataInauguracao;
    }
    /**Construtor utilizado por Hidroeletricas*/
    public Central(int numIdentificacao, String designacao,String localidade, int potencia, int[] dataInauguracao){
        this.numIdentificacao = numIdentificacao;
        this.designacao = designacao;
        this.localidade = localidade;
        this.potencia = potencia;
        this.dataInauguracao = dataInauguracao;
    }

    public void setDonos(Empresas donos) {
        this.donos.add(donos);
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
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

    public int getPotenciaProduzida() {
        return potenciaProduzida;
    }

    public ArrayList<Empresas> getDonos() {
        return donos;
    }

    @Override
    public String toString() {
        return "";
    }
}