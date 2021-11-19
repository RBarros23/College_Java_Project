import java.lang.reflect.Array;
import java.util.ArrayList;
import util.Consola;

public class Principal {
    public static void main(String[] args) {

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
        return Consola.lerInt("Opção: ",1,7);
    }

    public static void menuStats(){
        System.out.println("1 - Lista de centrais fotovoltaicas com mais de 10000 painéis.");
        System.out.println("Numero");
    }


    public static Boolean verNifEmp(ArrayList<Empresas> e, int nif){
        int cont = 0;
        for(int i = 0; i<e.size(); i++){
            if(nif == e.get(i).getNif()){
                cont ++;
                System.out.println("Já existe uma empresa com este NIF associado!");
                return false;
            }
        }
        return true;
    }





}
