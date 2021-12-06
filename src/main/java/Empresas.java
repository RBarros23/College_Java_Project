import java.io.Serializable;
import java.util.ArrayList;

public class Empresas implements Serializable {
    private String nome;
    private String morada;
    private int nif; //unico
    private ArrayList<Funcionarios> empregados = new ArrayList<>();
    //falta metodo para adicionar funcionario para o Arraylist

    public Empresas() {}


    public Empresas(String nome, String morada, int nif) {
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
    }


    public int getNif() {
        return nif;
    }

    public ArrayList<Funcionarios> getEmpregados() {
        return empregados;
    }

    @Override
    public String toString() {
        return "Empresas{" +
                "nome='" + nome + '\'' +
                ", morada='" + morada + '\'' +
                ", nif=" + nif +
                '}';
    }
}
