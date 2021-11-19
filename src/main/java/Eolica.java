import java.util.ArrayList;

public class Eolica extends Central{
    private int numAeroger;
    private int potAeroger; //potencia de cada aerogerador
    //falta tipo de equipamento

    public Eolica(){}

    public Eolica(String designacao, String localidade, int anoInicio, ArrayList<String> emp, int numAeroger, int potAeroger){
        super(designacao, localidade, anoInicio, numAeroger*potAeroger, emp);
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
}
