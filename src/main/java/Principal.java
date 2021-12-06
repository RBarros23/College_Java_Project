import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import util.Consola;

public class Principal {

    public static void main(String[] args) {
        ArrayList<Empresas> emp = new ArrayList<>();
        ArrayList<Hidroeletrica> hidro = new ArrayList<>();
        ArrayList<Fotovoltaica> fotovolt = new ArrayList<>();
        ArrayList<Eolica> eolica = new ArrayList<>();

        int contCentrais = 0, contHidro = 0, contFotovolt = 0, contEolica = 0;
        int opcao, op;
        do {
            opcao = menuInicial();
            switch (opcao) {
                case 1: //Inserir e consultar empresas.
                    insConslt(emp);
                    break;
                case 2: //Inserir e consultar (por nif) funcionários
                    break;
                case 3: //Inserir, consultar (por tipo) e alterar dados de tipos de equipamento
                    break;
                case 4: //Inserir, consultar (por localidade) centrais
                        //sempre que adicionar uma central incrementar contCentrais e associar ao numIdentificacao
                    break;
                case 5: //Associar empresas a uma central
                    assosEmpresa( emp,  hidro,  fotovolt,  eolica, contCentrais);
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
        System.out.println("Hidroeletrica: " + "Fotovoltaicas: " + "Eolica: " ); //falta contadores de centrais // utilizar tamanho das listas
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

    public static void insConslt(ArrayList<Empresas> emp){
        int opcao = 0;
        opcao = Consola.lerInt("Inserir (1) ou Consultar (2) (Voltar ao menu principal (0)): ", 0, 2);
        switch (opcao){
            case 0:
                break;
            case 1:
                inserirEmp(emp);
                break;
            case 2:
                break;
        }
    }

    public static void inserirEmp(ArrayList<Empresas> emp){
        String nome, morada;
        int nif = 0;
        boolean check = true;

        nome = Consola.lerString("Nome da empresa: ");
        morada = Consola.lerString("Morada da empresa: ");
        do {
            nif = Consola.lerInt("NIF da empresa:(Cancelar -1)", -1, 999999999);
            if(nif == -1) {
                check = false;
            }
            else {
                check = verNifEmp(emp, nif);
                if(!check){
                    emp.add(new Empresas(nome, morada, nif));
                    System.out.println(emp.toString());
                }
            }
        }while(check);

    }

    public static Boolean verNifEmp(ArrayList<Empresas> e, int nif){
        for(Empresas i : e){
            if(nif == i.getNif()){
                System.out.println("Já existe uma empresa com este NIF associado!");
                return true;
            }
        }
        return false;
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


    public static void assosEmpresa(ArrayList<Empresas> emp, ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica, int contaCentrais) {
        int opcao = -2, contador = 0, opcaoCentral = 0, contaAdi = 0;

        if (emp.size() != 0 && contaCentrais != 0) {
            do {
                opcao = Consola.lerInt("Insira NIF da empresa (-1 para sair): ", -1, 999999999);
                for (int i = 0; i < emp.size(); i++) {
                    if (emp.get(i).getNif() == opcao) {
                        contador = i;
                        i = emp.size();
                    }
                }
                if (contador == 0)
                    System.out.println("Não existe nenhuma empresa com esse NIF!");
                else {
                    do {
                        opcaoCentral = Consola.lerInt("Insira numero de identificacao da central (-1 para sair): ", -1, 999999999);
                        for (int j = 0; j < contaCentrais; j++) {
                            if (opcaoCentral == hidro.get(j).getNumIdentificacao()) {
                                hidro.get(j).setDonos(emp.get(contador));
                                contaAdi++;
                                j = contaCentrais;
                            } else if (opcaoCentral == fotovolt.get(j).getNumIdentificacao()) {
                                fotovolt.get(j).setDonos(emp.get(contador));
                                contaAdi++;
                                j = contaCentrais;
                            } else if (opcaoCentral == eolica.get(j).getNumIdentificacao()) {
                                eolica.get(j).setDonos(emp.get(contador));
                                contaAdi++;
                                j = contaCentrais;
                            }
                        }
                        if (contaAdi == 0)
                            System.out.println("Não existe nenhuma central com este numero de identificação: " + opcaoCentral);
                    } while (opcaoCentral != -1);

                }
            } while (opcao != -1 || opcaoCentral != -1);

        }
        else
            System.out.println("Não existem centrais/empresas, adicione primeiro!");
    }

    public void gravarFicheiro(ArrayList<Empresas> emp, ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Gestao.dat"));
            out.writeObject(emp);
            out.writeObject(hidro);
            out.writeObject(fotovolt);
            out.writeObject(eolica);
        }catch (IOException ex){

        }
    }

}













