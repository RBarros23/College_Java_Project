import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Central implements Serializable{
    private int numIdentificacao; //unico e sequencial definido no main
    private String designacao;
    private String localidade;
    private int anoInicio;
    private int potencia; //potencia instalada
    private ArrayList<Empresas> donos = new ArrayList<>();


    public Central() {}


    public Central(int numIdentificacao, String designacao,String localidade, int anoInicio, int potencia){
        this.numIdentificacao = numIdentificacao;
        this.designacao = designacao;
        this.localidade = localidade;
        this.anoInicio = anoInicio;
        this.potencia = potencia;
    }


    public int getNumIdentificacao() { // será utilizado para confirmar se já existe o numero ou nao
        return numIdentificacao;
    }


    public void setDonos(Empresas donos) {
        this.donos.add(donos);
    }

    @Override
    public String toString() {
        return "numIdentificacao=" + numIdentificacao +
                ", designacao='" + designacao + '\'' +
                ", localidade='" + localidade + '\'' +
                ", anoInicio=" + anoInicio +
                ", potencia=" + potencia +
                '}';
    }
}
