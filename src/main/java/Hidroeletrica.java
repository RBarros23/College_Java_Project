import java.io.Serializable;
import java.util.Arrays;

public class Hidroeletrica extends Central implements Serializable {
    private String cursoAgua;
    private String aproveitamento; //por exemplo albufeira
    private int capacidade; //Mm^3


    public Hidroeletrica(){
        super();
    }


    public Hidroeletrica(int numIdentificacao, String designacao, String localidade, int potencia, int[] dataInauguracao,
                            String cursoAgua, String aproveitamento, int capacidade) {
        super(numIdentificacao, designacao, localidade, potencia, dataInauguracao);
        this.cursoAgua = cursoAgua;
        this.aproveitamento = aproveitamento;
        this.capacidade = capacidade;
    }

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
                    ", Capacidade: " + capacidade +
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
                ", Capacidade: " + capacidade;
    }

    public void setDonos(Empresas donos){
        super.setDonos(donos);
    }
}