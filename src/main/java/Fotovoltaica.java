public class Fotovoltaica extends Central{
    private int area;
    private int numPaineis;
    private int potPainel; //potencia de cada painel
    //falta tipo equipamento


    public Fotovoltaica(){}
    //potencia deste tipo de central = numPaineis * potPainel
    public Fotovoltaica(String designacao, String localidade, int anoInicio, int area, int numPaineis, int potPainel){
        super(designacao, localidade, anoInicio, numPaineis*potPainel);
        this.area = area;
        this.numPaineis = numPaineis;
        this.potPainel = potPainel;
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
