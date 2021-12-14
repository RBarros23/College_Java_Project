import util.Consola;
import java.util.ArrayList;

/** Classe que disponibiliza um conjunto de metodos para gerir as informações dos empregados
 *
 * @author Rui Barros & Rui Vitorino
 */

public class gerirEmpregados {

    /**
     * Permite Verificar se o nif introduzido jd
     */
    private static Boolean verNifTrab(ArrayList<Funcionarios> f, int nif) {
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

    private static void inserirEmpregado(ArrayList<Empresas> emp){
        String nome, morada, funcao, nomeEmpresa;
        int nifFuncionario, telefone, contador = -1, indice = 0, nifEmpresa;
        boolean check = true;
        int[] dataNascimento;

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
                dataNascimento = Principal.lerData(); //[0] = dia, [1] = mes, [2] = ano
                emp.get(indice).setEmpregados(new Funcionarios(nome, morada, funcao, nifFuncionario, telefone, dataNascimento[0], dataNascimento[1], dataNascimento[2], nomeEmpresa));
            }
        }
        else {
            System.out.println("Não existem empresas para poder ter funcionarios!");
        }
    }

    private static void consultarEmpregado(ArrayList<Empresas> emp){
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
}
