import java.util.ArrayList;

public class Central {
    private static int numIdentificacao; //unico e sequencial
    private String designacao;
    private String localidade;
    private int anoInicio;
    private int potencia; //potencia instalada
    private ArrayList<ArrayList<String>> donos = new ArrayList<ArrayList<String>>();


    public Central() {}


    public Central(String designacao,String localidade, int anoInicio, int potencia, ArrayList<String> emp){
        numIdentificacao ++; //como o num é estático irá manter o seu valor e quando for criada uma nova central será com valor +1 que o existente
        this.designacao = designacao;
        this.localidade = localidade;
        this.anoInicio = anoInicio;
        this.potencia = potencia;
        donos.add(emp);
    }


    public int getNumIdentificacao() { // será utilizado para confirmar se já existe o numero ou nao
        return numIdentificacao;
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
