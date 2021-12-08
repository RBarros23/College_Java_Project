import java.io.*;
import java.util.ArrayList;

import util.Consola;


public class Principal {

    public static void main(String[] args) {
        ArrayList<Empresas> emp = new ArrayList<Empresas>();
        ArrayList<Hidroeletrica> hidro = new ArrayList<Hidroeletrica>();
        ArrayList<Fotovoltaica> fotovolt = new ArrayList<Fotovoltaica>();
        ArrayList<Eolica> eolica = new ArrayList<Eolica>();
        ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
        int totalCentrais;
        totalCentrais = hidro.size() + fotovolt.size() + eolica.size();

        int opcao, op;

        try {
            FileInputStream carregarGestao = new FileInputStream("Gestao.dat");
            ObjectInputStream in = new ObjectInputStream(carregarGestao);
            emp = (ArrayList<Empresas>) in.readObject();
//            hidro = (ArrayList<Hidroeletrica>) in.readObject();
//            fotovolt = (ArrayList<Fotovoltaica>) in.readObject();
//            eolica = (ArrayList<Eolica>) in.readObject();
            in.close();
            carregarGestao.close();
        } catch (IOException | ClassNotFoundException ex) {
        }

        do {
            opcao = menuInicial(totalCentrais, hidro.size(), fotovolt.size(), eolica.size());
            switch (opcao) {
                case 1: //Inserir e consultar empresas.
                    insConsEmpresa(emp);
                    break;
                case 2: //Inserir e consultar (por nif) funcionários
                    insConsEmpregado(emp);
                    break;
                case 3: //Inserir, consultar (por tipo) e alterar dados de tipos de equipamento
                    //gerirEquipamentos.insConsultarEquipamentos(equipamentos, fotovolt, eolica);
                    //System.out.println(equipamentos.toString());
                    insConsultarEquipamentos(equipamentos, fotovolt, eolica);
                    System.out.println(equipamentos.toString());
                    break;
                case 4: //Inserir, consultar (por localidade) centrais
                        //sempre que adicionar uma central incrementar contCentrais e associar ao numIdentificacao
                    break;
                case 5: //Associar empresas a uma central
                    assosEmpresa(emp, hidro, fotovolt, eolica, totalCentrais);
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
                    gravarFicheiro(emp, hidro, fotovolt, eolica);
                    break;
                case 0: //Gravar e sair
                    break;
            }
        }while(opcao != 0);
    }

    public static int menuInicial(int totalCentrais, int totalHidro, int totalFotovol, int totalEolica){
        System.out.println("Número de centrais existentes: " + totalCentrais);
        System.out.println("Hidroeletrica: "+ totalHidro + " Fotovoltaicas: " + totalFotovol + " Eolica: " +totalEolica );
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

    public static void insConsEmpresa(ArrayList<Empresas> emp){
        int opcao = 0;
        opcao = Consola.lerInt("Inserir (1) ou Consultar (2) (Voltar ao menu principal (0)): ", 0, 2);
        switch (opcao){
            case 0:
                break;
            case 1:
                inserirEmpresa(emp);
                break;
            case 2:
                System.out.println(emp.toString());
                break;
        }
    }

    public static void inserirEmpresa(ArrayList<Empresas> emp){
        String nome, morada;
        int nif = 0;
        boolean check = true;

        nome = Consola.lerString("Nome da empresa: ");
        morada = Consola.lerString("Morada da empresa: ");
        do {
            nif = Consola.lerInt("NIF da empresa:(Cancelar 0): ", 0, 999999999);
            if(nif == 0) {
                check = false;
            }
            else {
                check = verNifEmp(emp, nif);
                if(!check){
                    emp.add(new Empresas(nome, morada, nif));
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

    public static void insConsEmpregado(ArrayList<Empresas> emp){
        int opcao = 0;
        opcao = Consola.lerInt("Inserir (1) ou Consultar por NIF (2) (Voltar ao menu principal (0)): ", 0, 2);
        switch (opcao){
//            case 0:
//                break;
            case 1:
                inserirEmpregado(emp);
                break;
            case 2:
                //System.out.println(emp.get(0).getEmpregados().size());
                consultarEmpregado(emp);
                break;
        }
    }

    public static void inserirEmpregado(ArrayList<Empresas> emp){
        String nome, morada, funcao;
        int nifFuncionario, telefone, contador = -1, indice = 0, nifEmpresa;
        boolean check = true;
        int dataNascimento[];


        do {
            nifEmpresa = Consola.lerInt("NIF da empresa: ",1,999999999);

            for(int i = 0; i < emp.size(); i++){
                if(emp.get(i).getNif() == nifEmpresa){
                    indice = i;
                    i = emp.size();
                    contador = 0;
                }
            }
            if(contador == -1)
                System.out.println("Não existe nenhuma empresa com esse NIF!");
        }while(contador == -1);


        do{
            nifFuncionario = Consola.lerInt("NIF do trabalhador: ", 1, 999999999);
            for(int i = 0; i < emp.size(); i++) {
                check = verNifTrab(emp.get(i).getEmpregados(), nifFuncionario);
            }
        }while(!check);

        if(check){
            nome = Consola.lerString("Nome: ");
            morada = Consola.lerString("Morada: ");
            funcao = Consola.lerString("Função: ");
            telefone = Consola.lerInt("Telefone: ", 1, 999999999);
            dataNascimento = lerDataNascimento(); //[0] = dia, [1] = mes, [2] = ano
            emp.get(indice).setEmpregados(new Funcionarios(nome, morada, funcao, nifFuncionario, telefone, dataNascimento[0], dataNascimento[1], dataNascimento[2]));


        }

    }

    public static void consultarEmpregado(ArrayList<Empresas> emp){
        int nif, contador = 0, contaEmpregados = 0;

        for(int i = 0; i < emp.size(); i++){
            if(emp.get(i).getEmpregados().size() != 0)
                contaEmpregados ++;
        }

        if(contaEmpregados != 0) {
            nif = Consola.lerInt("NIF do trabalhador que quer procurar: ", 1, 999999999);
            for (int i = 0; i < emp.size(); i++) {
                for (int j = 0; j < emp.get(i).getEmpregados().size(); j++) {
                    if (emp.get(i).getEmpregados().get(j).getNif() == nif) {
                        System.out.println(emp.get(i).getEmpregados().get(j).toString());
                        contador++;
                        j = emp.get(i).getEmpregados().size();
                        i = emp.size();
                        break;

                    }
                }
            }
            if (contador == 0)
                System.out.println("Não existe nenhum trabalhador com esse NIF!");
        }
        else{
            System.out.println("Ainda não existem funcionários registados!");
        }
    }

    public static void assosEmpresa(ArrayList<Empresas> emp, ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica, int totalCentrais) {
        int opcao = -2, contador = 0, opcaoCentral = 0, contaAdi = 0;

        if (emp.size() != 0 && totalCentrais != 0) {
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
                        for (int j = 0; j < totalCentrais; j++) {
                            if (opcaoCentral == hidro.get(j).getNumIdentificacao()) {
                                hidro.get(j).setDonos(emp.get(contador));
                                contaAdi++;
                                j = totalCentrais;
                            } else if (opcaoCentral == fotovolt.get(j).getNumIdentificacao()) {
                                fotovolt.get(j).setDonos(emp.get(contador));
                                contaAdi++;
                                j = totalCentrais;
                            } else if (opcaoCentral == eolica.get(j).getNumIdentificacao()) {
                                eolica.get(j).setDonos(emp.get(contador));
                                contaAdi++;
                                j = totalCentrais;
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

    /////////////////////////////////////////////////
    public static void insConsultarEquipamentos(ArrayList<Equipamento> equipamentos, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica){
        int opcao = Consola.lerInt("1 - Inserir, 2 - Consultar (por tipo) 3 - Alterar equipamento (0 para voltar)", 0, 3);

        switch (opcao){
            case 1:
                insEquipamentos(equipamentos, fotovolt, eolica);
                break;
            case 2:
                consultarEquipTipo(equipamentos);
                break;
            case 3:
                alterarEquip(equipamentos);
                break;

        }
    }

    public static void insEquipamentos(ArrayList<Equipamento> equipamentos, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica){
        String designacao, fabricante, modelo, tipo = null;
        int potencia, check, adicionar;

        do {
            designacao = Consola.lerString("Designação: ");
            check = veriDesignacao(equipamentos, designacao);
            if(check == 0)
                System.out.println("Já existe um equipamento com essa designação!");
        }while(check == 0);

        if(check == 1){
            fabricante = Consola.lerString("Fabricante: ");
            modelo = Consola.lerString("Modelo: ");
            tipo = pedirTipoEquip();

            potencia = Consola.lerInt("Potencia (KW): ", 1, 99999);
            equipamentos.add(new Equipamento(designacao, fabricante, modelo, potencia, tipo));
            if(fotovolt.size() == 0 && eolica.size() == 0) {
                System.out.println("Ainda não existem centrais fotovoltaicas nem eolicas para poder associar o equipamento a uma central!");
            }
            else if(fotovolt.size() == 0 && tipo.equalsIgnoreCase("P")){
                System.out.println("Ainda nao existem centrais fotovoltaicas por isso não é possivel adicionar o equipamento a uma central!");
            }
            else if(eolica.size() == 0 && tipo.equalsIgnoreCase("A")){
                System.out.println("Ainda não existem centrais eolicas por isso não é possivel adicionar o equipamento a uma central!");
            }
            else{
                adicionar = Consola.lerInt("Quer associar o equipamento a alguma central? (Sim (1) ou Não (2)) ", 1, 2);
                if (adicionar == 1) {
                    associarEquip(equipamentos, equipamentos.size() - 1, fotovolt, eolica);
                }
            }
        }
    }

    /**
     * Funcao utilizada para pedir o tipo de equipamento
     * Foi criada esta função porque é necessário pedir o tipo de equipamento em vários locais
     * */
    public static String pedirTipoEquip(){
        String tipo;
        do {
            tipo = Consola.lerString("Tipo (P - painel, A - aerogerador): ").toUpperCase();
            if(!tipo.equalsIgnoreCase("P") && !tipo.equalsIgnoreCase("A")){
                System.out.println("Apenas é possivel registar equipamentos do tipo P ou A!");
            }
        }while(!tipo.equalsIgnoreCase("P") && !tipo.equalsIgnoreCase("A"));
        return tipo;
    }

    public static void associarEquip(ArrayList<Equipamento> equipamentos, int indiceEquip , ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica){
        int numCentral;
        if(equipamentos.get(indiceEquip).getTipo().equalsIgnoreCase("P")){
            System.out.println("Como o equipamento é do tipo P:");
            System.out.println("Ver lista de centrais fotovoltaicas - 0.");
            do {
                numCentral = Consola.lerInt("Indique o numero da central fotovoltaica a adicionar: ", 0, fotovolt.size());
                if (numCentral == 0) {
                    System.out.println(fotovolt.toString());
                }
            }while(numCentral == 0);
            fotovolt.get(numCentral).addEquip(equipamentos.get(indiceEquip));
        }
        else{
            System.out.println("Como o equipamento é do tipo A:");
            System.out.println("Ver lista de centrais eolicas - 0.");
            do {
                numCentral = Consola.lerInt("Indique o numero da central fotovoltaica a adicionar: ", 0, eolica.size());
                if (numCentral == 0) {
                    System.out.println(eolica.toString());
                }
            }while(numCentral == 0);
            eolica.get(numCentral).addEquip(equipamentos.get(indiceEquip));
        }
        System.out.println(fotovolt.toString());
        System.out.println(eolica.toString());
    }

    public static int veriDesignacao(ArrayList<Equipamento> equipamentos, String designacao){
        for(Equipamento equip : equipamentos){
            if(equip.getDesignacao().equalsIgnoreCase(designacao)){
                return 0;
            }
        }
        return 1;

    }

    /**
     * Consultar equipamento por tipo (P ou A)*/
    public static void consultarEquipTipo(ArrayList<Equipamento> equipamentos){
        int contador = 0;
        String opcao = null;
        opcao = pedirTipoEquip();

        switch (opcao){
            case "P":
                for(int i = 0; i < equipamentos.size(); i++){
                    if(equipamentos.get(i).getTipo().equalsIgnoreCase(opcao)){
                        System.out.println(equipamentos.get(i).toString());
                        contador ++;
                    }
                }
                if (contador == 0)
                    System.out.println("Não existem equipamentos do tipo P");
                break;
            case "A":
                for(int i = 0; i < equipamentos.size(); i++){
                    if(equipamentos.get(i).getTipo().equalsIgnoreCase(opcao)){
                        System.out.println(equipamentos.get(i).toString());
                        contador ++;
                    }
                }
                if (contador == 0)
                    System.out.println("Não existem equipamentos do tipo A");
                break;
            default:
                break;
        }
    }

    /**TERMINAR*/
    public static void alterarEquip(ArrayList<Equipamento> equipamentos){
        String designacao;
        int check = 0, opcao = 0, indice = 0;
        do {
            designacao = Consola.lerString("Qual a designação do equipamento a alterar (0 para sair): ");
            if(designacao.equalsIgnoreCase("0"))
                break;
            for(int i = 0; i < equipamentos.size(); i++){
                if(equipamentos.get(i).getDesignacao().equalsIgnoreCase(designacao)){
                    check ++;
                    indice = i;
                    i = equipamentos.size();
                }
            }
            if(check == 0)
                System.out.println("Não existe nenhum equipamento com essa designação!");
        }while(check == 0);
        if(!designacao.equalsIgnoreCase("0")) {
            opcao = Consola.lerInt("Que informação quer alterar:\n" +
                    "1 - Designação\n" + "2 - Fabricante\n" + "3 - Modelo\n" + "4 - Potencia\n" + "5 - Tipo", 1, 5);
        }
        switch (opcao){
            case 1: //Designação
                break;
            case 2: //Fabricante
                break;
            case 3: //modelo
                break;
            case 4: //potencia
                break;
            case 5: //tipo
                break;
        }
    }


    ////////////////////////////////////////////////////
    public static void gravarFicheiro(ArrayList<Empresas> emp, ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica){
        try{
            FileOutputStream gestao = new FileOutputStream("Gestao.dat");
            ObjectOutputStream out = new ObjectOutputStream(gestao);
            out.writeObject(emp);
            out.close();
            gestao.close();
            System.out.println("Gravado!");
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void lerFicheiro(ArrayList<Empresas> emp) { //Meter este codigo ao dentro do main ao iniciar o programa

        try {
            FileInputStream carregarGestao = new FileInputStream("Gestao.dat");
            ObjectInputStream in = new ObjectInputStream(carregarGestao);
            emp = (ArrayList<Empresas>) in.readObject();
            in.close();
            carregarGestao.close();
            System.out.println("Ficheiro carregado!");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static int[] lerDataNascimento(){
        int maxDiasMes,dia, mes, ano;
        ano = Consola.lerInt("Ano de nascimento: ", 1910, 2022);
        mes = Consola.lerInt("Mes de nascimento: ", 1, 12);
        switch (mes){
            case 4, 6, 9, 11:
                maxDiasMes = 30;
                break;
            case 2:
                if((ano % 400 == 0) || (ano % 4 == 0 && ano % 100 != 0)){
                    maxDiasMes = 29;
                }
                else{
                    maxDiasMes = 28;
                }
                break;
            default:
                maxDiasMes = 31;
        }
        dia = Consola.lerInt("Dia de nascimento: ", 1, maxDiasMes);
        int data[] = {dia, mes, ano};
        return data;
    }

}













