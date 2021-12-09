import java.io.Serializable;


public class Funcionarios implements Serializable{
    private String nome;
    private String morada;
    private String funcao;
    private int nif; //Unico
    private int telefone;
    int[] dataNascimento;

    public Funcionarios(){}

    public Funcionarios(String nm, String morada, String funcao, int nif, int tele, int dia, int mes, int ano){
        nome = nm;
        this.morada = morada;
        this.funcao = funcao;
        this.nif = nif;
        telefone = tele;
        setDataNascimento(ano, mes, dia);
    }

    public void setDataNascimento(int ano, int mes, int dia) {
        dataNascimento = new int[]{dia, mes, ano};
    }

    public String getDataNascimento() {
        return dataNascimento[0] + "/" + dataNascimento[1] + "/" + dataNascimento[2];
    }

    public int getNif() {
        return nif;
    }

    @Override
    public String toString() {
        return "\nFuncionarios{" +
                "nome='" + nome + '\'' +
                ", morada='" + morada + '\'' +
                ", funcao='" + funcao + '\'' +
                ", nif=" + nif +
                ", telefone=" + telefone +
                ", dataNascimento=" + getDataNascimento() +
                '}';
    }
}
