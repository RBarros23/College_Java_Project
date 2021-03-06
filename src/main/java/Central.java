import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe onde estão definidas os atributos e metodos necessários para a criação de uma Central
 *
 * @author Rui Barros
 * */

public class Central implements Serializable{
    private int numIdentificacao;
    private String designacao;
    private String localidade;
    private int potencia;
    private int potenciaProduzidaAnual;
    private int mediaProducao;
    private int[] potenciaCadaAno;
    private ArrayList<Empresas> donos = new ArrayList<>();
    int[] dataInauguracao;

    /***/
    public Central() {}
    /***/
    public Central(int numIdentificacao, String designacao,String localidade, int potencia, int[] dataInauguracao, int potenciaProduzidaAnual, int[] potenciaCadaAno){
        this.numIdentificacao = numIdentificacao;
        this.designacao = designacao;
        this.localidade = localidade;
        this.potencia = potencia;
        this.dataInauguracao = dataInauguracao;
        this.potenciaProduzidaAnual = potenciaProduzidaAnual;
        this.potenciaCadaAno = potenciaCadaAno;
    }
    /***/
    public void setDonos(Empresas donos) {
        this.donos.add(donos);
    }
    /***/
    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }
    /***/
    public void setMediaProducao(int mediaProducao) {
        this.mediaProducao = mediaProducao;
    }
    /***/
    public int getNumIdentificacao() {
        return numIdentificacao;
    }
    /***/
    public String getdataInauguracao() {
        return dataInauguracao[0] + "/" + dataInauguracao[1] + "/" + dataInauguracao[2];
    }
    /***/
    public String getDesignacao() {
        return designacao;
    }
    /***/
    public String getLocalidade() {
        return localidade;
    }
    /***/
    public int getPotencia() {
        return potencia;
    }
    /***/
    public int getPotenciaProduzidaAnual() {
        return potenciaProduzidaAnual;
    }
    /***/
    public int[] getPotenciaCadaAno() {
        return potenciaCadaAno;
    }
    /***/
    public ArrayList<Empresas> getDonos() {
        return donos;
    }
    /***/
    public int getMediaProducao() {
        return mediaProducao;
    }
    /***/
    @Override
    public String toString() {
        return "";
    }
}