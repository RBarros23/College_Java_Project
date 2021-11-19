import java.lang.reflect.Array;
import java.util.ArrayList;
import util.Consola;

public class Principal {

    public static void main(String[] args) {
        ArrayList<Empresas> emp = new ArrayList<>();
        ArrayList<Hidroeletrica> hidro = new ArrayList<>();
        ArrayList<Fotovoltaica> fotovolt = new ArrayList<>();
        ArrayList<Eolica> eolica = new ArrayList<>();

        int opcao, op;
        do {
            opcao = menuInicial();
            switch (opcao) {
                case 1: //Inserir e consultar empresas.
                    inserirEmp();
                    break;
                case 2: //Inserir e consultar (por nif) funcionários
                    break;
                case 3: //Inserir, consultar (por tipo) e alterar dados de tipos de equipamento
                    break;
                case 4: //Inserir, consultar (por localidade) centrais
                    break;
                case 5: //Associar empresas a uma central
                    break;
                case 6: //Associar valor de produção energética anual a uma central
                    break;
                case 7: //Estatisticas
                    do{
                        op = menuStats();
                        switch (op){
                            case 1: //Lista de centrais fotovoltaicas com mais de 10000 painéis
                                break;
                            case 2: //Numero de funcionários por empresa
                                break;
                            case 3: //Médias de produção de energética
                                break;
                            case 4: //Total de produção energética anual por tipo
                                break;
                            case 0: //Voltar para o menu principal
                                break;
                        }
                    }while(op != 0);
                    break;
                case 8: //Gravar dados
                    break;
                case 0: //Gravar e sair
                    break;
            }
        }while(opcao != 0);
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

    public static int menuStats(){
        System.out.println("1 - Lista de centrais fotovoltaicas com mais de 10000 painéis.");
        System.out.println("2 - Numero de funcionários por empresa."); //Ordenado por ordem crescente pelo nome da empresa
        System.out.println("3 - Médias de produção de energética."); //Ordenadas por ordem decrescente da média e desde que entraram em serviço
        System.out.println("4 - Total de produção energética anual por tipo.");
        System.out.println("0 - Voltar para o menu principal.");
        return Consola.lerInt("Opção: ",0,4);
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

    public static void inserirEmp(){
        ArrayList<Empresas> e = new ArrayList(Consola.lerString("Nome da empresa: "), Consola.lerString("Morada da empresa: "), Consola.lerInt("NIF da empresa",0,999999999));

    }



}
