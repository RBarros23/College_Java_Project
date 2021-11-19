import java.util.ArrayList;
import util.Consola;

public class Principal {

    public static void main(String[] args) {
        int op = 0;
        do {
            op = menuInicial();
            switch (op) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 0:
                    break;


            }
        }while(op != 0);
    }

    public static int menuInicial(){
        System.out.println("Número de centrais existentes:");
        System.out.println("Hidroeletrica: " + "Fotovoltaicas: " + "Eolica: " ); //falta contadores de centrais // utilizador tamanho das listas
        System.out.println("1 - Inserir e consultar (todas) empresas.");
        System.out.println("2 - Inserir e consultar (por nif) funcionários.");
        System.out.println("3 - Inserir, consultar (por tipo) e alterar dados de tipos de equipamento.");
        System.out.println("4 - Inserir, consultar (por localidade) centrais."); //Mostrar tambem a empresa que gere
        System.out.println("5 - Associar empresas a uma central.");
        System.out.println("6 - Associar valor de produção energética anual a uma central.");
        System.out.println("7 - Estatisticas.");
        System.out.println("8 - Gravar dados.");
        System.out.println("0 - Gravar e sair.");
        return Consola.lerInt("Opção: ",0,8);
    }

    public static void menuStats(){
        System.out.println("1 - Lista de centrais fotovoltaicas com mais de 10000 painéis.");
        System.out.println("2 - Numero de funcionários por empresa."); //Ordenado por ordem crescente pelo nome da empresa
        System.out.println("Médias de produção de energética."); //Ordenadas por ordem decrescente da média e desde que entraram em serviço
        System.out.println("Total de produção energética anual por tipo.");
    }

    public static Boolean verNifEmp(ArrayList<Empresas> e, int nif){
        for(Empresas i : e){
            if(nif == i.getNif()){
                System.out.println("Já existe uma empresa com este NIF associado!");
                return false;
            }
        }
        return true;
    }

    public static Boolean verNifTrab(ArrayList<Funcionarios> f, int nif) {
        for (Funcionarios i : f) {
            if (nif == i.getNif()) {
                System.out.println("Já existe um trabalhador com este NIF associado!");
                return false;
            }
        }
        return true;
    }

    public static Boolean verNumIdentCentral(ArrayList<Central> c, int numIdent) {
        for (Central i : c) {
            if (numIdent == i.getNumIdentificacao()) {
                System.out.println("Já existe uma empresa com este NIF associado!");
                return false;
            }
        }
        return true;
    }




}
