import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
//Potencia é a soma das potencias dos equipamentos guardados no Arraylist equip
public class Fotovoltaica extends Central implements Serializable {
    private int area;
    private int numPaineis;
    private String tipoEquipamento = "P";
    private ArrayList<Equipamento> equip = new ArrayList<Equipamento>();


    public Fotovoltaica(){
        super();
    }

    public Fotovoltaica(int numIdentificacao, String designacao, String localidade, int[] dataInauguracao,int potenciaAnual, int area){
        super(numIdentificacao, designacao, localidade, dataInauguracao, potenciaAnual);
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
        int potencia = 0;
        super.setPotencia(0);
        for(Equipamento e: equip){
            potencia += e.getPotencia();
        }
        super.setPotencia(potencia);
    }

    @Override
    public String toString() {
        if(getDonos().size() > 0){
            return "Designação: " + getDesignacao() +
                    ", Numero de identificação: " + getNumIdentificacao() +
                    ", Data da Inauguração: " + getdataInauguracao() +
                    ", Localidade: " + getLocalidade() +
                    ", Potencia instalada (MW): " + getPotencia() +
                    ", Area (km^2): " + area +
                    ", Numero de paineis instalados: " + numPaineis +
                    ", Tipo de equipamentos instalados: " + tipoEquipamento +
                    ", Donos: " + getDonos()
                    ;
        }
        return "Designação: " + getDesignacao() +
                ", Numero de identificação: " + getNumIdentificacao() +
                ", Data da Inauguração: " + getdataInauguracao() +
                ", Localidade: " + getLocalidade() +
                ", Potencia instalada (MW): " + getPotencia() +
                ", Area (km^2): " + area +
                ", Numero de paineis instalados: " + numPaineis +
                ", Tipo de equipamentos instalados: " + tipoEquipamento;
    }
}
