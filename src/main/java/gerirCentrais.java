import util.Consola;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

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
        int numIdentificacao, potencia, capacidade, potenciaProduzidaAnual;
        String designacao, localidade, cursoAgua, aproveitamento;
        int[] dataAssociado, potenciaCadaAno;

        numIdentificacao = hidro.size() + 1;
        potencia = Consola.lerInt("Potencia instalada (MW): ", 1, 999999);
        capacidade = Consola.lerInt("Capacidade (milhoes m^3): ", 1, 999999);
        designacao = Consola.lerString("Designação da central: ");
        localidade = Consola.lerString("Localidade: ");
        potenciaProduzidaAnual = Consola.lerInt("Potencia produzida anualmente (GW): ", 1, 999999);

        cursoAgua = Consola.lerString("Nome do curso de água: ");
        aproveitamento = Consola.lerString("Tipo de aproveitamento: ");
        System.out.println("Data de inicio de funcionamento:");
        dataAssociado = Principal.lerData();
        potenciaCadaAno = potenciaCadaAno(dataAssociado[2]);
        hidro.add(new Hidroeletrica(numIdentificacao, designacao, localidade, potencia, dataAssociado, potenciaProduzidaAnual, potenciaCadaAno, cursoAgua, aproveitamento, capacidade));
    }

    private static void adicionarFoto(ArrayList<Fotovoltaica> fotovolt){
        int numIdentificacao, area, potenciaAnual, numPaineis, potenciaPainel;
        String designacao, localidade;
        int[] dataInauguracao, potenciaCadaAno;

        numIdentificacao = fotovolt.size() + 1;
        area = Consola.lerInt("Area da central: ", 1, 999999);
        designacao = Consola.lerString("Designação da central: ");
        localidade = Consola.lerString("Localidade: ");
        numPaineis = Consola.lerInt("Quantidade de paineis instalados: ", 1, 999999);
        potenciaPainel = Consola.lerInt("Potencia de cada painel (MW): ", 1, 999999);
        potenciaAnual = Consola.lerInt("Potencia produzida anualmente (GW): ", 1, 999999);
        System.out.println("Data de inicio de funcionamento: ");
        dataInauguracao = Principal.lerData();
        potenciaCadaAno = potenciaCadaAno(dataInauguracao[2]);
        fotovolt.add(new Fotovoltaica(numIdentificacao, designacao, localidade,numPaineis*potenciaPainel ,dataInauguracao, potenciaAnual, potenciaCadaAno, area, numPaineis, potenciaPainel));
    }

    private static void adicionarEolica(ArrayList<Eolica> eolica){
        int numIdentificacao, potenciaAnual, numAeroger, potCadaAeroger;
        String designacao, localidade;
        int[] dataInauguracao, potenciaCadaAno;

        numIdentificacao = eolica.size() + 1;
        designacao = Consola.lerString("Designação da central: ");
        localidade = Consola.lerString("Localização: ");
        potenciaAnual = Consola.lerInt("Potencia anual (GW): ", 1, 999999);
        numAeroger = Consola.lerInt("Quantidade de aerogeradores: ", 1, 999999);
        potCadaAeroger = Consola.lerInt("Potencia de cada aerogerador (MW): ", 1, 999999);
        System.out.println("Data de inicio de funcionamento:");
        dataInauguracao = Principal.lerData();
        potenciaCadaAno = potenciaCadaAno(dataInauguracao[2]);

        eolica.add(new Eolica(numIdentificacao, designacao, localidade, numAeroger*potCadaAeroger, dataInauguracao, potenciaAnual, potenciaCadaAno, potCadaAeroger, numAeroger));
    }

    private static int[] potenciaCadaAno(int ano){
        Calendar c = Calendar.getInstance();
        int anoAtual = c.get(Calendar.YEAR);
        int indice = 0;
        int[] potencias = new int[anoAtual - ano + 1];

        for(int i = ano; i <= anoAtual; i++){
            potencias[indice] = Consola.lerInt("Potencia produzida em " + ano + ": ", 1, 999999);
            indice ++;
            ano++;
        }
        return potencias;
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

    public static void fotovoltMaisX(ArrayList<Fotovoltaica> foto){
        int conta = 0;
        for(Fotovoltaica f : foto){
            if(f.getNumPaineis() >= 10000){
                System.out.println(f.toString());
                conta ++;
            }
        }
        if(conta == 0)
            System.out.println("Não existem centrais com mais de 10000 paineis!");
    }

    public static void totalProdTipo(ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> foto, ArrayList<Eolica> eoli){
        int contaHidro = 0, contaFoto = 0, contaEolica = 0;

        for(Hidroeletrica h : hidro){
            contaHidro += h.getPotenciaProduzidaAnual();
        }
        for(Fotovoltaica f : foto){
            contaFoto += f.getPotenciaProduzidaAnual();
        }
        for(Eolica e : eoli){
            contaEolica += e.getPotenciaProduzidaAnual();
        }

        System.out.println("Potencia dos Hidroeletricos: " + contaHidro);
        System.out.println("Potencia dos Fotovoltaicos: " + contaFoto);
        System.out.println("Potencia das Eolicas: " + contaEolica);

    }

    private static void mediaHidro(ArrayList<Hidroeletrica> hidro){
        int media, valorBruto = 0;
        int[] potencias;

        for(Hidroeletrica h : hidro){
            potencias =  h.getPotenciaCadaAno();
            for(int i = 0; i < potencias.length; i++){
                valorBruto += potencias[i];
            }
            media = valorBruto / potencias.length;
            h.setMediaProducao(media);
        }
    }

    private static void mediaFoto(ArrayList<Fotovoltaica> foto){
        int media, valorBruto = 0;
        int[] potencias;

        for(Fotovoltaica f : foto){
            potencias = f.getPotenciaCadaAno();
            for(int i = 0; i < potencias.length; i++){
                valorBruto += potencias[i];
            }
            media = valorBruto / potencias.length;
            f.setMediaProducao(media);
        }
    }

    private static void mediaEolica(ArrayList<Eolica> eolica){
        int media, valorBruto = 0;
        int[] potencias;

        for(Eolica e : eolica){
            potencias = e.getPotenciaCadaAno();
            for(int i = 0; i < potencias.length; i++){
                valorBruto += potencias[i];
            }
            media = valorBruto / potencias.length;
            e.setMediaProducao(media);
        }
    }

    public static void mediaOrdenada(ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> foto, ArrayList<Eolica> eolica){
        mediaHidro(hidro);
        mediaFoto(foto);
        mediaEolica(eolica);



    }
}
















