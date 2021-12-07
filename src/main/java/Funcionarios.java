import java.io.Serializable;
import java.util.Calendar;


public class Funcionarios implements Serializable{
    private String nome;
    private String morada;
    private String funcao;
    private int nif; //Unico
    private int telefone;
    private String empresa;
    Calendar dataNascimento = Calendar.getInstance();

    public Funcionarios(){}

    public Funcionarios(String nm, String morada, String funcao, int nif, int tele, String empresa){
        nome = nm;
        this.morada = morada;
        this.funcao = funcao;
        this.nif = nif;
        telefone = tele;
        this.empresa = empresa;
    }

    public void setDataNascimento(int ano, int mes, int dia) {
        dataNascimento.set(ano,mes, dia);
    }

    public void getDataNascimento() {
        System.out.println(dataNascimento.get(Calendar.DAY_OF_MONTH) + "/" + dataNascimento.get(Calendar.MONTH) + "/" + dataNascimento.get(Calendar.YEAR));
    }

    public int getNif() {
        return nif;
    }
}
