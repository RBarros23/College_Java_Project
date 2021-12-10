import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;

import util.Consola;


public class Principal {

    public static void main(String[] args) {
        ArrayList<Empresas> emp = new ArrayList<Empresas>();
        ArrayList<Hidroeletrica> hidro = new ArrayList<Hidroeletrica>();
        ArrayList<Fotovoltaica> fotovolt = new ArrayList<Fotovoltaica>();
        ArrayList<Eolica> eolica = new ArrayList<Eolica>();
        ArrayList<Equipamento> equipamentos = new ArrayList<Equipamento>();
        int totalCentrais;


        int opcao, op;

        try {
            FileInputStream carregarGestao = new FileInputStream("Gestao.dat");
            ObjectInputStream in = new ObjectInputStream(carregarGestao);
            emp = (ArrayList<Empresas>) in.readObject();
            equipamentos = (ArrayList<Equipamento>) in.readObject();
            hidro = (ArrayList<Hidroeletrica>) in.readObject();
            fotovolt = (ArrayList<Fotovoltaica>) in.readObject();
            eolica = (ArrayList<Eolica>) in.readObject();
            in.close();
            carregarGestao.close();
        } catch (IOException | ClassNotFoundException ex) {}


        do {
            totalCentrais = hidro.size() + fotovolt.size() + eolica.size();
            opcao = menuInicial(hidro.size(), fotovolt.size(), eolica.size());
            switch (opcao) {
                case 1: //Inserir e consultar empresas.
                    gerirEmpresas.insConsEmpresa(emp);
                    break;
                case 2: //Inserir e consultar (por nif) funcionários
                    insConsEmpregado(emp);
                    break;
                case 3: //Inserir, associar e consultar (por tipo) e alterar dados de tipos de equipamento
                    gerirEquipamentos.insConsultarEquipamentos(equipamentos, fotovolt, eolica);
                    break;
                case 4: //Inserir, consultar (por localidade) centrais
                        //sempre que adicionar uma central incrementar contCentrais e associar ao numIdentificacao
                    gerirCentrais.insConsulCentrais(hidro, fotovolt, eolica);
                    break;
                case 5: //Associar empresas a uma central
                    gerirEmpresas.assosEmpresa(emp, hidro, fotovolt, eolica, totalCentrais);
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
                case 0, 8: //Gravar e sair
                    gravarFicheiro(emp, hidro, fotovolt, eolica, equipamentos);
                    break;
            }
        }while(opcao != 0);
    }

    public static int menuInicial(int totalHidro, int totalFotovol, int totalEolica){
        System.out.println("\nNúmero de centrais:");
        System.out.println("Hidroeletrica: "+ totalHidro + " Fotovoltaicas: " + totalFotovol + " Eolica: " +totalEolica );
        System.out.println("1 - Inserir e consultar (todas) empresas.");
        System.out.println("2 - Inserir e consultar (por nif) funcionários.");
        System.out.println("3 - Inserir, associar, consultar (por tipo) e alterar dados de tipos de equipamento.");
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
            case 1:
                inserirEmpregado(emp);
                break;
            case 2:
                consultarEmpregado(emp);
                break;
        }
    }

    public static void inserirEmpregado(ArrayList<Empresas> emp){
        String nome, morada, funcao, nomeEmpresa;
        int nifFuncionario, telefone, contador = -1, indice = 0, nifEmpresa;
        boolean check = true;
        int dataNascimento[];

        if(emp.size() > 0) {
            do {
                nifEmpresa = Consola.lerInt("NIF da empresa: ", 1, 999999999);

                for (int i = 0; i < emp.size(); i++) {
                    if (emp.get(i).getNif() == nifEmpresa) {
                        indice = i;
                        i = emp.size();
                        contador = 0;
                    }
                }
                if (contador == -1)
                    System.out.println("Não existe nenhuma empresa com esse NIF!");
            } while (contador == -1);


            do {
                nifFuncionario = Consola.lerInt("NIF do trabalhador: ", 1, 999999999);
                for (int i = 0; i < emp.size(); i++) {
                    check = verNifTrab(emp.get(i).getEmpregados(), nifFuncionario);
                }
            } while (!check);

            if (check) {
                nome = Consola.lerString("Nome: ");
                morada = Consola.lerString("Morada: ");
                funcao = Consola.lerString("Função: ");
                telefone = Consola.lerInt("Telefone: ", 1, 999999999);
                System.out.printf("Data de nascimento:\n");
                nomeEmpresa = emp.get(indice).getNome();
                dataNascimento = lerData(); //[0] = dia, [1] = mes, [2] = ano
                emp.get(indice).setEmpregados(new Funcionarios(nome, morada, funcao, nifFuncionario, telefone, dataNascimento[0], dataNascimento[1], dataNascimento[2], nomeEmpresa));
            }
        }
        else {
            System.out.println("Não existem empresas para poder ter funcionarios!");
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

    public static void gravarFicheiro(ArrayList<Empresas> emp, ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica, ArrayList<Equipamento> equipamentos){
        try{
            FileOutputStream gestao = new FileOutputStream("Gestao.dat");
            ObjectOutputStream out = new ObjectOutputStream(gestao);
            out.writeObject(emp);
            out.writeObject(equipamentos);
            out.writeObject(hidro);
            out.writeObject(fotovolt);
            out.writeObject(eolica);
            out.close();
            gestao.close();
            System.out.println("Gravado!");
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static int[] lerData(){
        int maxDiasMes,dia, mes, ano;
        ano = Consola.lerInt("Ano: ", 1910, 2022);
        mes = Consola.lerInt("Mes: ", 1, 12);
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
        dia = Consola.lerInt("Dia: ", 1, maxDiasMes);
        int data[] = {dia, mes, ano};
        return data;
    }

}













