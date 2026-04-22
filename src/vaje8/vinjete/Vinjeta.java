package vaje8.vinjete;

public class Vinjeta {
    String oznaka;
    String razred;
    String zacetek_veljavnosti;
    String vrsta;

    public Vinjeta(String oznaka, String razred, String zacetek_veljavnosti, String vrsta) {
        this.oznaka = oznaka;
        this.razred = razred;
        this.zacetek_veljavnosti = zacetek_veljavnosti;
        this.vrsta = vrsta;

    }

    public String toString(){
        return String.format("%s [%s]: %s (%s)", oznaka, razred, vrsta, zacetek_veljavnosti);
    }
}
