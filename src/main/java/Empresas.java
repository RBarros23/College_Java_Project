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

    public String getNome() {
        return nome;
    }

    public int getNif() {
        return nif;
    }

    public void setEmpregados(Funcionarios emp) {
        empregados.add(emp);
    }

    public ArrayList<Funcionarios> getEmpregados() {
        return empregados;
    }

    @Override
    public String toString() {

        return "\nEmpresa:\n" + "Nome: " + nome + "\nMorada: " + morada + "\nNIF: " + nif ;

//                "Empresas{" +
//                "nome='" + nome + '\'' +
//                ", morada='" + morada + '\'' +
//                ", nif=" + nif +
//                '}';
    }
}
