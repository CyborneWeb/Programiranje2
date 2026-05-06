package vaje9.banka;

public class VarcevalniRacun extends Racun {
    private final double obresti;
    /**
     * Konstruktor: ustvari račun s podano številko računa in začetnim stanjem 0,00 EUR.
     *
     * @param stevilka - številka računa
     */
    VarcevalniRacun(String stevilka,double obresti) {
        super(stevilka);

        this.obresti = obresti;
    }

    void dodajObresti(){
        double stanje = getStanje();
        if (stanje > 0){
        double obresti = stanje * this.obresti;
            polog(obresti);
        }
         else if (stanje < 0){
        dvig(1); // take the 1 EURO you greedy bank
    }
    }

    @Override
    String opisRacuna() {
        return String.format("varčevalni račun z obrestno mero %.2f %%", obresti*100);
    }
}
