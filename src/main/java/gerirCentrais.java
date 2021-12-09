import util.Consola;

import java.util.ArrayList;

public class gerirCentrais {

    public static void insConsulCentrais(ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica, ArrayList<Empresas> emp, ArrayList<Equipamento> equipamentos){
        int opcao;
        opcao = Consola.lerInt("1 - Inserir central, 2 - Consultar centrais (por localidade) (0 para recuar) ", 0, 2);
        switch (opcao){
            case 1: //Inserir central
                tipoCentralAdicionar(hidro, fotovolt, eolica, emp, equipamentos);
                break;
            case 2: // Consultar por localidade
                break;
        }
    }

    private static void tipoCentralAdicionar(ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica, ArrayList<Empresas> emp, ArrayList<Equipamento> equipamentos){
        int opcao;
        opcao = Consola.lerInt("Qual o tipo de central que deseja adicionar:\n" +
                "1 - Hidroelétrica\n" + "2 - Fotovoltaica\n" + "3 - Eólica", 0, 3);
        switch (opcao){
            case 1:
                adicionarHidro(hidro);
                break;
            case 2:
                adicionarFoto(fotovolt);
                break;
            case 3:
                break;
        }
    }

    private static void adicionarHidro(ArrayList<Hidroeletrica> hidro){
        int numIdentificacao, potencia, capacidade;
        String designacao, localidade, cursoAgua, aproveitamento;
        int[] dataAssociado;

        numIdentificacao = hidro.size() + 1;
        potencia = Consola.lerInt("Potencia instalada (MW): ", 1, 999999);
        capacidade = Consola.lerInt("Capacidade (milhoes m^3): ", 1, 999999);
        designacao = Consola.lerString("Designação da central: ");
        localidade = Consola.lerString("Localidade: ");
        cursoAgua = Consola.lerString("Nome do curso de água: ");
        aproveitamento = Consola.lerString("Tipo de aproveitamento: ");
        System.out.println("Data de inicio de funcionamento:");
        dataAssociado = Principal.lerData();
        hidro.add(new Hidroeletrica(numIdentificacao, designacao, localidade, potencia,dataAssociado, cursoAgua, aproveitamento, capacidade));
    }

    private static void adicionarFoto(ArrayList<Fotovoltaica> fotovolt){
        int numIdentificacao, area;
        String designacao, localidade;
        int[] dataInauguracao;

        numIdentificacao = fotovolt.size() + 1;
        area = Consola.lerInt("Area da central: ", 1, 999999);
        designacao = Consola.lerString("Designação da central: ");
        localidade = Consola.lerString("Localidade: ");
        dataInauguracao = Principal.lerData();
        fotovolt.add(new Fotovoltaica(numIdentificacao, designacao, localidade, dataInauguracao, area));
    }
}
















