import java.io.Serializable;
import java.util.ArrayList;

public class Eolica extends Central implements Serializable {
    private int numAeroger;
    private int potCadaAeroger;
    private String tipoEquipamento = "A";
    private ArrayList<Equipamento> equip = new ArrayList<Equipamento>();

    public Eolica(){
        super();
    }

    public Eolica(int numIdentificacao, String designacao, String localidade, int potencia, int[] dataInauguracao, int potenciaAnual, int[] potenciaCadaAno, int potCadaAeroger, int numAeroger){
        super(numIdentificacao, designacao, localidade, potencia, dataInauguracao, potenciaAnual, potenciaCadaAno);
        this.potCadaAeroger = potCadaAeroger;
        this.numAeroger =numAeroger;
    }

    public ArrayList<Equipamento> getEquip() {
        return equip;
    }

    public void addEquip(Equipamento e){  //confirmar no main se é do tipo A
        equip.add(e);
    }

    public void setDonos(Empresas donos){
        super.setDonos(donos);
    }

    public void setNumAeroger() {
        numAeroger = equip.size();
    }

    public void setPotencia() {
        int potencia = 0;
        super.setPotencia(0);
        for(Equipamento e: equip){
            potencia += e.getPotencia();
        }
        super.setPotencia(potencia);
    }

    @Override
    public String toString() {
        if(getDonos().size() > 0) {
            return "Designação: " + getDesignacao() +
                    ", Numero de identificação: " + getNumIdentificacao() +
                    ", Data da Inauguração: " + getdataInauguracao() +
                    ", Localidade: " + getLocalidade() +
                    ", Potencia instalada (MW): " + getPotencia() +
                    ", Numero de Aerogeradores: " + numAeroger +
                    ", Potencia cada aerogerador: " + potCadaAeroger +
                    ", Tipo de equipamentos instalados: " + tipoEquipamento +
                    ", Donos: " + getDonos();
        }
        return "Designação: " + getDesignacao() +
                ", Numero de identificação: " + getNumIdentificacao() +
                ", Data da Inauguração: " + getdataInauguracao() +
                ", Localidade: " + getLocalidade() +
                ", Potencia instalada (MW): " + getPotencia() +
                ", Numero de Aerogeradores: " + numAeroger +
                ", Potencia cada aerogerador: " + potCadaAeroger +
                ", Tipo de equipamentos instalados: " + tipoEquipamento;
    }
}
