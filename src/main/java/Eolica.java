import java.io.Serializable;
import java.util.ArrayList;

public class Eolica extends Central implements Serializable {
    private int numAeroger;
    private int potAeroger; //potencia de cada aerogerador
    private ArrayList<Equipamento> equip = new ArrayList<Equipamento>();

    public Eolica(){
        super();
    }

    public Eolica(int numIdentificacao, String designacao, String localidade, int anoInicio, int numAeroger, int potAeroger){
        super(numIdentificacao, designacao, localidade, anoInicio, numAeroger*potAeroger);
        this.numAeroger = numAeroger;
        this.potAeroger = potAeroger;
    }

    @Override
    public String toString() {
        System.out.println("Central Hidroeletrica");
        System.out.println("Nº de aerogeradores: " + numAeroger);
        System.out.println("Potencia de cada aerogerador: " + potAeroger);
        return super.toString();
    }

    public int getNumAeroger() {
        return numAeroger;
    }

    public int getPotAeroger() {
        return potAeroger;
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
}
