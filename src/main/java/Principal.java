import java.io.*;
import java.util.ArrayList;

import util.Consola;

/**
 * Classe onde é apresentado o menu principal, sub-menu de estatisticas, gravação e
 * leitura de dados e leitura de datas
 *
 * @author Rui Barros & Rui Vitorino
 * */


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
                    gerirEmpregados.insConsEmpregado(emp);
                    break;
                case 3: //Inserir, associar e consultar (por tipo) e alterar dados de tipos de equipamento
                    gerirEquipamentos.insConsultarEquipamentos(equipamentos, fotovolt, eolica);
                    break;
                case 4: //Inserir, consultar (por localidade) centrais
                    gerirCentrais.insConsulCentrais(hidro, fotovolt, eolica);
                    break;
                case 5: //Associar empresas a uma central
                    gerirEmpresas.assosEmpresa(emp, hidro, fotovolt, eolica, totalCentrais);
                    break;
                case 6: //Estatisticas
                    do{
                        op = menuStats();
                        switch (op){
                            case 1: //Lista de centrais fotovoltaicas com mais de 10000 painéis
                                gerirCentrais.fotovoltMaisX(fotovolt);
                                break;
                            case 2: //Numero de funcionários por empresa
                                gerirEmpresas.mediaEmpregados(emp);
                                break;
                            case 3: //Médias de produção de energética
                                gerirCentrais.mediaOrdenada(hidro, fotovolt, eolica);
                                break;
                            case 4: //Total de produção energética anual por tipo
                                gerirCentrais.totalProdTipo(hidro, fotovolt, eolica);
                                break;
                            case 0: //Voltar para o menu principal
                                break;
                        }
                    }while(op != 0);
                    break;
                case 0, 7: //Gravar e sair
                    gravarFicheiro(emp, hidro, fotovolt, eolica, equipamentos);
                    break;
            }
        }while(opcao != 0);
    }

    /**
     * Apresenta ao utilizador as funcionalidades principais
     *
     * @param totalEolica quantas centrais Eolicas existem registadas no momento
     * @param totalFotovol quantas centrais Eolicas existem registadas no momento
     * @param totalHidro quantas centrais Hidroelétricas existem registadas no momento
     * @return opção selecionada pelo utilizador
     * */
    public static int menuInicial(int totalHidro, int totalFotovol, int totalEolica){
        System.out.println("\nNúmero de centrais:");
        System.out.println("Hidroeletrica: "+ totalHidro + " Fotovoltaicas: " + totalFotovol + " Eolica: " +totalEolica );
        System.out.println("1 - Inserir e consultar (todas) empresas.");
        System.out.println("2 - Inserir e consultar (por nif) funcionários.");
        System.out.println("3 - Inserir, associar, consultar (por tipo) e alterar dados de tipos de equipamento.");
        System.out.println("4 - Inserir, consultar (por localidade) centrais."); //Mostrar tambem a empresa que gere
        System.out.println("5 - Associar empresas a uma central.");
        System.out.println("6 - Estatisticas.");
        System.out.println("7 - Gravar dados.");
        System.out.println("0 - Gravar e sair.");
        return Consola.lerInt("Opção: ",0,7);
    }

    /**
     * Apresenta ao utilizador as funcionalidades sobre estatisticas
     *
     * @return opção selecionada pelo utilizador
     * */
    public static int menuStats(){
        System.out.println("1 - Lista de centrais fotovoltaicas com mais de 10000 painéis.");
        System.out.println("2 - Numero de funcionários por empresa.");
        System.out.println("3 - Médias de produção de energética.");
        System.out.println("4 - Total de produção energética anual por tipo.");
        System.out.println("0 - Voltar para o menu principal.");
        return Consola.lerInt("Opção: ",0,4);
    }

    /**
     * Grava os dados existentos sobre todas as empresas, funcionarios e centrais existentes
     *
     * @param emp Arraylist que contem todas as empresas e funcionários associados
     * @param eolica Arraylist com todas as centrais eolicas e equipamentos que tem associados
     * @param fotovolt Arraylist com todas as centrais fotovoltaicas e equipamentos que tem associados
     * @param hidro Arraylist com todas as centrais hidroelétricas e equipamentos que tem associados
     * @param equipamentos Arraylist com todos os equipamentos
     * */
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

    /**
     * Pede ao utilizador uma data
     *
     * @return um array contendo a data - dia / mes / ano
     * */
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
        int[] data = {dia, mes, ano};
        return data;
    }

}













