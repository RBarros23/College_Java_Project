import java.io.Serializable;

/**
 * Classe onde estão definidas os atributos especificos de uma central Hidro elétrica
 * e metodos necessários para a sua criação
 *
 * @author Rui Barros
 * */
public class Hidroeletrica extends Central implements Serializable {
    private String cursoAgua;
    private String aproveitamento;
    private int capacidade;

    /***/
    public Hidroeletrica(){
        super();
    }
    /***/
    public Hidroeletrica(int numIdentificacao, String designacao, String localidade, int potencia, int[] dataInauguracao, int potenciaProduzidaAnual, int[] potenciaCadaAno,
                            String cursoAgua, String aproveitamento, int capacidade) {
        super(numIdentificacao, designacao, localidade, potencia, dataInauguracao, potenciaProduzidaAnual, potenciaCadaAno);
        this.cursoAgua = cursoAgua;
        this.aproveitamento = aproveitamento;
        this.capacidade = capacidade;
    }
    /***/
    @Override
    public String toString() {
        if(getDonos().size() > 0) {
            return "Designação: " + getDesignacao() +
                    ", Numero de identificação: " + getNumIdentificacao() +
                    ", Data da Inauguração: " + getdataInauguracao() +
                    ", Localidade: " + getLocalidade() +
                    ", Potencia (MW): " + getPotencia() +
                    ", Curso de água: " + cursoAgua +
                    ", Aproveitamento: " + aproveitamento +
                    ", Capacidade (Mm^3): " + capacidade +
                    ", Donos: " + getDonos()
            ;
        }
        return "Designação: " + getDesignacao() +
                ", Numero de identificação: " + getNumIdentificacao() +
                ", Data da Inauguração: " + getdataInauguracao() +
                ", Localidade: " + getLocalidade() +
                ", Potencia (MW): " + getPotencia() +
                ", Curso de água: " + cursoAgua +
                ", Aproveitamento: " + aproveitamento +
                ", Capacidade (Mm^3): " + capacidade;
    }
    /***/
    public void setDonos(Empresas donos){
        super.setDonos(donos);
    }



}