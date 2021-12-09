import util.Consola;

import java.util.ArrayList;

public class gerirCentrais {

    public static void insConsulCentrais(ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica){
        int opcao;
        opcao = Consola.lerInt("1 - Inserir central, 2 - Consultar centrais (por localidade) (0 para recuar) ", 0, 2);
        switch (opcao){
            case 1: //Inserir central
                break;
            case 2: // Consultar por localidade
                break;
        }
    }

    private static void tipoCentralAdicionar(){
        int opcao;
        opcao = Consola.lerInt("Qual o tipo de central que deseja adicionar:\n" +
                "1 - Hidroelétrica\n" + "2 - Fotovoltaica\n" + "3 - Eólica", 0, 3);
        switch (opcao){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    private static void adicionarHidro(){

    }
}
