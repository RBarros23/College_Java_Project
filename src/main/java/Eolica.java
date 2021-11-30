import java.util.ArrayList;

public class Eolica extends Central{
    private int numAeroger;
    private int potAeroger; //potencia de cada aerogerador
    private ArrayList<Equipamento> equip = new ArrayList<>();

    public Eolica(){}

    public Eolica(int numIdentificacao, String designacao, String localidade, int anoInicio, int nifDonos, int numAeroger, int potAeroger){
        super(numIdentificacao, designacao, localidade, anoInicio, numAeroger*potAeroger);
        this.numAeroger = numAeroger;
        this.potAeroger = potAeroger;
        super.setDonos(nifDonos);
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


    public void addEquip(Equipamento e){  //confirmar no main se é do tipo A
        equip.add(e);
    }
}
