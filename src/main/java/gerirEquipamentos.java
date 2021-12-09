import util.Consola;
import java.util.ArrayList;
/**Nesta class estão todas as funções necessárias para tratar dos equipamentos
 * */

public class gerirEquipamentos {

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
                alterarEquip(equipamentos, fotovolt, eolica);
                break;

        }
    }

    private static void insEquipamentos(ArrayList<Equipamento> equipamentos, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica){
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

            if(fotovolt.size() == 0 && tipo.equalsIgnoreCase("P")){
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
    private static String pedirTipoEquip(){
        String tipo;
        do {
            tipo = Consola.lerString("Tipo (P - painel, A - aerogerador): ").toUpperCase();
            if(!tipo.equalsIgnoreCase("P") && !tipo.equalsIgnoreCase("A")){
                System.out.println("Apenas é possivel registar equipamentos do tipo P ou A!");
            }
        }while(!tipo.equalsIgnoreCase("P") && !tipo.equalsIgnoreCase("A"));
        return tipo;
    }

    private static void associarEquip(ArrayList<Equipamento> equipamentos, int indiceEquip , ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica){
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
            equipamentos.get(indiceEquip).setNumCentral(fotovolt.get(numCentral).getNumIdentificacao());
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
            equipamentos.get(indiceEquip).setNumCentral(eolica.get(numCentral).getNumIdentificacao());
        }
    }

    /**Verificar se já existe algum equipamento com essa designação*/
    private static int veriDesignacao(ArrayList<Equipamento> equipamentos, String designacao){
        for(Equipamento equip : equipamentos){
            if(equip.getDesignacao().equalsIgnoreCase(designacao)){
                return 0;
            }
        }
        return 1;

    }

    /**
     * Consultar equipamento por tipo (P ou A)*/
    private static void consultarEquipTipo(ArrayList<Equipamento> equipamentos){
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
                for(Equipamento equip : equipamentos){
                    if(equip.getTipo().equalsIgnoreCase(opcao)){
                        System.out.println("TESTE");
                        System.out.println(equip.toString());
                        System.out.println("TESTE");
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

    /**FALTA ALTERAR TIPO*/
    private static void alterarEquip(ArrayList<Equipamento> equipamentos, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica){
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
                    "1 - Designação\n" + "2 - Fabricante\n" + "3 - Modelo\n" + "4 - Potencia\n" + "5 - Tipo ", 1, 5);
        }
        switch (opcao){
            case 1: //Designação
                alterarDesignacao(equipamentos, indice);
                break;
            case 2: //Fabricante
                alterarFabricante(equipamentos, indice);
                break;
            case 3: //modelo
                alterarModelo(equipamentos, indice);
                break;
            case 4: //potencia
                alterarPotencia(equipamentos, indice);
                break;
            case 5: //tipo
                //alterarTipo(equipamentos, indice, fotovolt, eolica);
                break;
        }
    }
    /**Alterar a designacao*/
    private static void alterarDesignacao(ArrayList<Equipamento> equipamentos, int indiceEquip){
        String novaDesignacao;
        int check;
        do {
            novaDesignacao = Consola.lerString("Qual a nova designação: ");
            check = veriDesignacao(equipamentos, novaDesignacao);
            if(check == 0)
                System.out.println("Já existe um equipamento com essa designação!");
        }while(check == 0);
        equipamentos.get(indiceEquip).setDesignacao(novaDesignacao);
    }

    private static void alterarFabricante(ArrayList<Equipamento> equipamentos, int indiceEquip){
        String novoFabricante;
        novoFabricante = Consola.lerString("Qual o novo nome do fabricante: ");
        equipamentos.get(indiceEquip).setFabricante(novoFabricante);
    }

    private static void alterarModelo(ArrayList<Equipamento> equipamentos, int indiceEquip){
        String novoModelo;
        novoModelo = Consola.lerString("Qual o novo nome do modelo: ");
        equipamentos.get(indiceEquip).setModelo(novoModelo);
    }

    private static void alterarPotencia(ArrayList<Equipamento> equipamentos, int indiceEquip){
        int novaPotencia;
        novaPotencia = Consola.lerInt("Nova potencia: ", 1, 99999);
        equipamentos.get(indiceEquip).setPotencia(novaPotencia);
    }

//    private static void alterarTipo(ArrayList<Equipamento> equipamentos, int indiceEquip, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica){
//        int indiceCentrais;
//        String novoTipo;
//        novoTipo = pedirTipoEquip();
//        if(novoTipo != equipamentos.get(indiceEquip).getTipo()){
//            if(equipamentos.get(indiceEquip).getTipo().equalsIgnoreCase("P")){
//                for(Eolica e : eolica){
//                    if(e.getEquip().contains(equipamentos.get(indiceEquip))){
//                        e.getEquip().contains(equipamentos.get(indiceEquip)) = null;
//                    }
//                }
//                equipamentos.get(indiceEquip).setTipo(novoTipo);
//            }
//        }
//    }


}
