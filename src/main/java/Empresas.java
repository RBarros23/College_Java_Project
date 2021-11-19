public class Empresas {
    private String nome;
    private String morada;
    private int nif; //unico


    public Empresas() {}


    public Empresas(String nome, String morada, int nif) {
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
    }


    public int getNif() {
        return nif;
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
