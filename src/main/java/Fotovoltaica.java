import java.util.ArrayList;

public class Fotovoltaica extends Central{
    private int area;
    private int numPaineis;
    private int potPainel; //potencia de cada painel
    private ArrayList<Equipamento> equip = new ArrayList<>();


    public Fotovoltaica(){}
    //potencia deste tipo de central = numPaineis * potPainel
    public Fotovoltaica(int numIdentificacao, String designacao, String localidade, int anoInicio, int nifDonos, int area, int numPaineis, int potPainel){
        super(numIdentificacao, designacao, localidade, anoInicio, numPaineis*potPainel);
        this.area = area;
        this.numPaineis = numPaineis;
        this.potPainel = potPainel;
        super.setDonos(nifDonos);
    }

    public void addEquip(Equipamento e){  //confirmar no main se é do tipo P
        equip.add(e);
    }

    @Override
    public String toString() {
        System.out.println("Central Fotovoltaica{" +
                "area=" + area +
                ", numPaineis=" + numPaineis +
                ", potPainel=" + potPainel +
                '}');
                return super.toString();
    }
}
