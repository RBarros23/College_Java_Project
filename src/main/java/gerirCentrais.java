import util.Consola;

import java.util.ArrayList;

public class gerirCentrais {

    public static void insConsulCentrais(ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica){
        int opcao;
        opcao = Consola.lerInt("1 - Inserir central, 2 - Consultar centrais (por localidade) (0 para recuar) ", 0, 2);
        switch (opcao){
            case 1: //Inserir central
                tipoCentralAdicionar(hidro, fotovolt, eolica);
                break;
            case 2: // Consultar por localidade
                procurarLocalidade(hidro, fotovolt, eolica);
                break;
        }
    }

    private static void tipoCentralAdicionar(ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica){
        int opcao;
        opcao = Consola.lerInt("Qual o tipo de central que deseja adicionar:\n" +
                "1 - Hidroelétrica\n" + "2 - Fotovoltaica\n" + "3 - Eólica ", 0, 3);
        switch (opcao){
            case 1:
                adicionarHidro(hidro);
                break;
            case 2:
                adicionarFoto(fotovolt);
                break;
            case 3:
                adicionarEolica(eolica);
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
        int numIdentificacao, area, potenciaAnual;
        String designacao, localidade;
        int[] dataInauguracao;

        numIdentificacao = fotovolt.size() + 1;
        area = Consola.lerInt("Area da central: ", 1, 999999);
        designacao = Consola.lerString("Designação da central: ");
        localidade = Consola.lerString("Localidade: ");
        potenciaAnual = Consola.lerInt("Potencia produzida anualmente (GW): ", 1, 999999);
        System.out.println("Data de inicio de funcionamento: ");
        dataInauguracao = Principal.lerData();
        fotovolt.add(new Fotovoltaica(numIdentificacao, designacao, localidade, dataInauguracao, potenciaAnual, area));
    }

    private static void adicionarEolica(ArrayList<Eolica> eolica){
        int numIdentificacao, potenciaAnual;
        String designacao, localidade;
        int[] dataInauguracao;

        numIdentificacao = eolica.size() + 1;
        designacao = Consola.lerString("Designação da central: ");
        localidade = Consola.lerString("Localização: ");
        potenciaAnual = Consola.lerInt("Potencia anual (GW): ", 1, 999999);
        System.out.println("Data de inicio de funcionamento:");
        dataInauguracao = Principal.lerData();
        eolica.add(new Eolica(numIdentificacao, designacao, localidade, dataInauguracao, potenciaAnual));
    }

    private static void procurarLocalidade(ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica) {
        int check = 0;
        String localidade;
        if (hidro.size() > 0 || fotovolt.size() > 0 || eolica.size() > 0) {
            do {
                localidade = Consola.lerString("Qual a localidade a procurar centrais: ");
                for (Hidroeletrica h : hidro) {
                    if (h.getLocalidade().equalsIgnoreCase(localidade)) {
                        check++;
                    }
                }
                for (Fotovoltaica f : fotovolt) {
                    if (f.getLocalidade().equalsIgnoreCase(localidade)) {
                        check++;
                    }
                }
                for (Eolica e : eolica) {
                    if (e.getLocalidade().equalsIgnoreCase(localidade)) {
                        check++;
                    }
                }
                if (check == 0)
                    System.out.println("Não existe nenhuma central nessa localidade!");
            } while (check == 0);

            for (Hidroeletrica h : hidro) {
                if (h.getLocalidade().equalsIgnoreCase(localidade)) {
                    System.out.println(h.toString());
                }
            }
            for (Fotovoltaica f : fotovolt) {
                if (f.getLocalidade().equalsIgnoreCase(localidade)) {
                    System.out.println(f.toString());
                }
            }
            for (Eolica e : eolica) {
                if (e.getLocalidade().equalsIgnoreCase(localidade)) {
                    System.out.println(e.toString());
                }
            }
        }
        else{
            System.out.println("Não existem centrais registadas!");
        }
    }
}
















