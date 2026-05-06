package vaje9.banka;

public class Banka {
    private final Racun[] racuni = new Racun[500];
    private int steviloRacunov = 0;

    public boolean dodajTekociRacun(String stevilka, double limit) {
        if (preveriStevilko(stevilka) || steviloRacunov == racuni.length) {
            return false;
        }

        racuni[steviloRacunov] = new TekociRacun(stevilka, limit);
        steviloRacunov++;
        return true;
    }

    public boolean dodajVarcevalniRacun(String stevilka, double obresti) {
        if (preveriStevilko(stevilka) || steviloRacunov == racuni.length) {
            return false;
        }

        racuni[steviloRacunov] = new VarcevalniRacun(stevilka, obresti);
        steviloRacunov++;
        return true;
    }

    public boolean preveriStevilko(String stevilka) {
        for (int i = 0; i < steviloRacunov; i++) {
            if (racuni[i].getStevilka().equals(stevilka)) {
                return true;
            }
        }
        return false;
    }

    public void izpisiRacune() {
        for (int i = 0; i < steviloRacunov; i++) {
            System.out.println(racuni[i]);
        }
    }

    public void izpisiRacune(boolean varcevalni) {
        for (int i = 0; i < steviloRacunov; i++) {
            if (varcevalni && racuni[i] instanceof VarcevalniRacun) {
                System.out.println(racuni[i]);
            } else if (!varcevalni && racuni[i] instanceof TekociRacun) {
                System.out.println(racuni[i]);
            }
        }
    }

    public boolean dvig(String stevilka, double znesek) {
        for (int i = 0; i < steviloRacunov; i++) {
            if (racuni[i].getStevilka().equals(stevilka)) {
                return racuni[i].dvig(znesek);
            }
        }
        return false;
    }

    public boolean polog(String stevilka, double znesek) {
        for (int i = 0; i < steviloRacunov; i++) {
            if (racuni[i].getStevilka().equals(stevilka)) {
                return racuni[i].polog(znesek);
            }
        }
        return false;
    }

    public void dodajObresti() {
        for (int i = 0; i < steviloRacunov; i++) {
            if (racuni[i] instanceof VarcevalniRacun) {
                ((VarcevalniRacun) racuni[i]).dodajObresti();
            }
        }
    }
}
