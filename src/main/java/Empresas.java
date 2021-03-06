import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe onde estão definidas os atributos e metodos necessários para a criação de uma Empresa
 *
 * @author Rui Barros
 * */

public class Empresas implements Serializable {
    private String nome;
    private String morada;
    private int nif;
    private ArrayList<Funcionarios> empregados = new ArrayList<Funcionarios>();

    /***/
    public Empresas() {}
    /***/
    public Empresas(String nome, String morada, int nif) {
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
    }
    /***/
    public String getNome() {
        return nome;
    }
    /***/
    public int getNif() {
        return nif;
    }
    /***/
    public void setEmpregados(Funcionarios emp) {
        empregados.add(emp);
    }
    /***/
    public ArrayList<Funcionarios> getEmpregados() {
        return empregados;
    }
    /***/
    @Override
    public String toString() {
        return "\nNome: " + nome +
                ", Morada: " + morada +
                ", NIF: " + nif +
                ", Nº funcionarios: " + empregados.size();
    }
}
