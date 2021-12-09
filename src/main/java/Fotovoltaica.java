import java.io.Serializable;
import java.util.ArrayList;

public class Fotovoltaica extends Central implements Serializable {
    private int area;
    private int numPaineis;
    private int potencia; //soma de todas os paineis
    private String tipoEquipamento = "P";
    private ArrayList<Equipamento> equip = new ArrayList<Equipamento>();


    public Fotovoltaica(){
        super();
    }


    public Fotovoltaica(int numIdentificacao, String designacao, String localidade, int[] dataInauguracao, int area){
        super(numIdentificacao, designacao, localidade, dataInauguracao);
        this.area = area;
    }

    public void addEquip(Equipamento e){  //confirmar no main se é do tipo P
        equip.add(e);
    }

    public void setDonos(Empresas donos){
        super.setDonos(donos);
    }

    public void setNumPaineis() {
        numPaineis = equip.size();
    }

    public void setPotencia(){
        potencia = 0;
        for(Equipamento e: equip){
            potencia += e.getPotencia();
        }
    }
}
