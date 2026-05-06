package vaje9.banka;

public class TekociRacun extends Racun {
    /**
     * Konstruktor: ustvari račun s podano številko računa in začetnim stanjem 0,00 EUR.
     *
     * @param stevilka - številka računa
     */
    private final double limit;
    public TekociRacun(String stevilka, double limit) {
        super(stevilka);
        this.limit = limit;
    }

    @Override
    public boolean dvig(double znesek) {
        if (znesek <= limit){
        return super.dvig(znesek);
        }
        return false;
    }

    @Override
    String opisRacuna() {
        return String.format("tekoči z limitom dviga do %.2f EUR", limit);
    }
}
