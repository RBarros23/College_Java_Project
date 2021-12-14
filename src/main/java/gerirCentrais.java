import util.Consola;
import java.util.ArrayList;
import java.util.Calendar;

/** Classe que disponibiliza um conjunto de metodos para gerir as informações das centrais
 *
 * @author Rui Barros & Rui Vitorino
 * */
public class gerirCentrais {

    /** Apresenta menu para o utilizador escolher se pretende inserir ou consultar centrais
     *
     * @param hidro Arraylist onde estão todas as centrais do tipo Hidroelétricas
     * @param fotovolt  Arraylist onde estão todas as centrais do tipo Fotovoltaicas
     * @param eolica  Arraylist onde estão todas as centrais do tipo eolicas
     * */
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

    /** Pergunta ao utilizador qual o tipo de central que quer adicionar
     *
     *
     * @param hidro Arraylist onde estão todas as centrais do tipo Hidroelétricas
     * @param fotovolt  Arraylist onde estão todas as centrais do tipo Fotovoltaicas
     * @param eolica  Arraylist onde estão todas as centrais do tipo eolicas
     * */
    private static void tipoCentralAdicionar(ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica){
        int opcao;
        opcao = Consola.lerInt("Qual o tipo de central que deseja adicionar (0 para voltar a trás):\n" +
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

    /**Adiciona centrais do tipo Hidroeletricas ao Arraylist hidro
     *
     *
     * @param hidro Arraylist onde será guardada a central
     * */
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

    /**Adiciona centrais do tipo Fotovoltaica ao Arraylist fotovolt
     *
     *
     * @param fotovolt Arraylist onde será guardada a central
     * */
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

    /** Adiciona centrais do tipo Eolica ao Arraylist eolica
     *
     *
     * @param eolica Arraylist onde será guardada a central
     * */
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

    /**Pede ao utilizador a potencia produzida em cada ano desde o ano de fundação
     *
     * @param ano ano de fundação
     * */
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

    /** Apresenta todas as centrais que estejam na localidade escolhida pelo utilizador
     *
     * @param hidro Arraylist onde estão todas as centrais do tipo Hidroelétricas
     * @param fotovolt  Arraylist onde estão todas as centrais do tipo Fotovoltaicas
     * @param eolica  Arraylist onde estão todas as centrais do tipo eolicas
     * */
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

    /** Apresenta todas as centrais fotovoltaicas com mais de 10000 paineis
     *
     * @param foto Arraylist onde estão todas as centrais com todos os paineis associados
     * */
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

    /** Apresenta a energia produzida por cada tipo de central
     *
     * @param hidro Arraylist onde estão todas as centrais do tipo Hidroelétricas
     * @param foto  Arraylist onde estão todas as centrais do tipo Fotovoltaicas
     * @param eoli  Arraylist onde estão todas as centrais do tipo eolicas
     * */
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

        System.out.println("Potencia produzida dos Hidroeletricos: " + contaHidro);
        System.out.println("Potencia produzida dos Fotovoltaicos: " + contaFoto);
        System.out.println("Potencia produzida das Eolicas: " + contaEolica);

    }

    /** Calcula a média de energia produzida por centrais do tipo hidroeletrica ao longo dos anos
     *
     * @param hidro Arraylist onde estão todas as centrais do tipo Hidroelétricas
     * */
    private static void mediaHidro(ArrayList<Hidroeletrica> hidro){
        int media, valorBruto = 0;
        int[] potencias;
        float mediaFloat;

        for(Hidroeletrica h : hidro){
            potencias =  h.getPotenciaCadaAno();
            for(int i = 0; i < potencias.length; i++){
                valorBruto += potencias[i];
            }
            mediaFloat = (float) valorBruto /potencias.length;
            media = (int) mediaFloat;
            h.setMediaProducao(media);
            valorBruto = 0;
        }
    }

    /** Calcula a média de energia produzida por centrais do tipo fotovoltaica ao longo dos anos
     *
     * @param foto Arraylist onde estão todas as centrais do tipo Fotovoltaica
     * */
    private static void mediaFoto(ArrayList<Fotovoltaica> foto){
        int media, valorBruto = 0;
        int[] potencias;
        float mediaFloat;

        for(Fotovoltaica f : foto){
            potencias = f.getPotenciaCadaAno();
            for(int i = 0; i < potencias.length; i++){
                valorBruto += potencias[i];
            }
            mediaFloat = (float) valorBruto /potencias.length;
            media = (int) mediaFloat;
            f.setMediaProducao(media);
            valorBruto = 0;
        }
    }

    /** Calcula a média de energia produzida por centrais do tipo Eolica ao longo dos anos
     *
     * @param eolica Arraylist onde estão todas as centrais do tipo Eolica
     * */
    private static void mediaEolica(ArrayList<Eolica> eolica){
        int media, valorBruto = 0;
        int[] potencias;
        float mediaFloat;

        for(Eolica e : eolica){
            potencias = e.getPotenciaCadaAno();

            for(int i = 0; i < potencias.length; i++){
                valorBruto += potencias[i];
            }
            mediaFloat = (float) valorBruto /potencias.length;
            media = (int) mediaFloat;
            e.setMediaProducao(media);
            valorBruto = 0;
        }
    }

    /** Apresenta a media de produção energética em cada central ordenada por ordem decrescente da média
     * desde que entraram ao serviço
     *
     *
     * @param hidro Arraylist onde estão todas as centrais do tipo Hidroelétricas
     * @param foto  Arraylist onde estão todas as centrais do tipo Fotovoltaicas
     * @param eolica  Arraylist onde estão todas as centrais do tipo eolicas
     * */
    public static void mediaOrdenada(ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> foto, ArrayList<Eolica> eolica){
        int maior = 0;
        mediaHidro(hidro);
        mediaFoto(foto);
        mediaEolica(eolica);

        class nomeMedia{
            String nome;
            int media;

            public nomeMedia(String nome, int media) {
                this.nome = nome;
                this.media = media;
            }

            public int getMedia() {
                return media;
            }

            public String getNome() {
                return nome;
            }

        }

        ArrayList<nomeMedia> listaCompleta = new ArrayList<>();
        ArrayList<nomeMedia> listaOrdenada = new ArrayList<>();

        for(Hidroeletrica h : hidro){
            listaCompleta.add(new nomeMedia(h.getDesignacao(), h.getMediaProducao()));
        }
        for(Fotovoltaica f: foto){
            listaCompleta.add(new nomeMedia(f.getDesignacao(), f.getMediaProducao()));
        }
        for(Eolica e: eolica){
            listaCompleta.add(new nomeMedia(e.getDesignacao(), e.getMediaProducao()));
        }

        do{
            for(int i = 0; i < listaCompleta.size(); i++){
                if(listaCompleta.get(i).getMedia() > listaCompleta.get(maior).getMedia()){
                    maior = i;
                }
            }
            listaOrdenada.add(listaCompleta.get(maior));
            listaCompleta.remove(maior);
            maior = 0;
        }while(listaCompleta.size() > 0);

        for(nomeMedia n : listaOrdenada){
            System.out.println("Designação: " + n.getNome() + "   Producao média: " + n.getMedia());
        }

    }
}
















