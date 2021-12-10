import util.Consola;

import java.util.ArrayList;

public class gerirEmpresas {

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

    private static void inserirEmpresa(ArrayList<Empresas> emp){
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

    private static Boolean verNifEmp(ArrayList<Empresas> e, int nif){
        for(Empresas i : e){
            if(nif == i.getNif()){
                System.out.println("Já existe uma empresa com este NIF associado!");
                return true;
            }
        }
        return false;
    }

    public static void assosEmpresa(ArrayList<Empresas> emp, ArrayList<Hidroeletrica> hidro, ArrayList<Fotovoltaica> fotovolt, ArrayList<Eolica> eolica, int totalCentrais) {
        int nifEmp = -1, indiceEmp = -1, opcaoCentral = -1, contaAdi = 0;

        if (emp.size() > 0 && totalCentrais > 0) {
            do {
                nifEmp = Consola.lerInt("Insira NIF da empresa (0 para sair): ", 0, 999999999);
                for(int i = 0; i < emp.size(); i++){
                    if(emp.get(i).getNif() == nifEmp) {
                        indiceEmp = i;
                    }
                }
                if (indiceEmp == -1 && nifEmp != 0)
                    System.out.println("Não existe nenhuma empresa com esse NIF!");
                if(indiceEmp > -1 && nifEmp != 0) {
                    do {
                        opcaoCentral = Consola.lerInt("Insira numero de identificacao da central: ", 1, 999999999);
                        for(Hidroeletrica h :  hidro){
                            if(h.getNumIdentificacao() == opcaoCentral){
                                h.setDonos(emp.get(indiceEmp));
                                contaAdi ++;
                                nifEmp = 0;
                            }
                            for(Fotovoltaica f : fotovolt){
                                if(f.getNumIdentificacao() == opcaoCentral){
                                    f.setDonos(emp.get(indiceEmp));
                                    contaAdi++;
                                    nifEmp = 0;
                                }
                            }
                            for(Eolica e : eolica){
                                if(e.getNumIdentificacao() == opcaoCentral){
                                    e.setDonos(emp.get(indiceEmp));
                                    contaAdi++;
                                    nifEmp = 0;
                                }
                            }
                        }
                        if (contaAdi == 0)
                            System.out.println("Não existe nenhuma central com este numero de identificação: " + opcaoCentral);
                    } while (contaAdi == 0);
                }
            } while (nifEmp != 0);

        }
        else
            System.out.println("Não existem centrais/empresas, adicione primeiro!");
    }

    public static void mediaEmpregados(ArrayList<Empresas> emp) {
        int maior = 0, contador = 0;
        ArrayList<Empresas> empresasOrdenadas = new ArrayList<>();
        ArrayList<Empresas> empresas = new ArrayList<>(emp);

        do {
            for (int i = 0; i < empresas.size(); i++) {
                if (empresas.get(i).getEmpregados().size() > empresas.get(maior).getEmpregados().size()) {
                    maior = i;
                }
            }
            empresasOrdenadas.add(empresas.get(maior));
            empresas.remove(maior);
            maior = 0;
        }while (empresas.size() > 0);

        for(int i = 0; i < empresasOrdenadas.size(); i++){
            System.out.println("Nome empresa: " + empresasOrdenadas.get(i).getNome() + "   Nº Funcionários: " + empresasOrdenadas.get(i).getEmpregados().size());
        }
    }

}
