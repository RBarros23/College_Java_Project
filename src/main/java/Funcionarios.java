public class Funcionarios {
    private String nome;
    private String morada;
    private String funcao;
    private int nif; //Unico
    private int telefone;
    private String empresa;
    //falta decidir o tipo de dados para a data de nascimento

    public Funcionarios(){}

    public Funcionarios(String nm, String morada, String funcao, int nif, int tele, String empresa){
        nome = nm;
        this.morada = morada;
        this.funcao = funcao;
        this.nif = nif;
        telefone = tele;
        this.empresa = empresa;
    }


    public int getNif() {
        return nif;
    }
}
