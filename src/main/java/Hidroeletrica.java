import java.util.ArrayList;

public class Hidroeletrica extends Central {
    private String cursoAgua;
    private String aproveitamento; //por exemplo albufeira
    private int capacidade; //Mm^3


    public Hidroeletrica(){
        super();
    }


    public Hidroeletrica(int numIdentificacao, String designacao, String localidade, int anoInicio, int potencia,
                            String cursoAgua, String aproveitamento, int capacidade) {
        super(numIdentificacao, designacao, localidade, anoInicio, potencia);
        this.cursoAgua = cursoAgua;
        this.aproveitamento = aproveitamento;
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        System.out.println("Central Hidroeletrica");
        System.out.println("Curso de água: " + cursoAgua);
        System.out.println("Local de aproveitamento: " + aproveitamento);
        System.out.println("Capacidade (Mm^3): " + capacidade);
        return super.toString();
    }

    public String getCursoAgua() {
        return cursoAgua;
    }

    public String getAproveitamento() {
        return aproveitamento;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setDonos(Empresas donos){
        super.setDonos(donos);
    }
}