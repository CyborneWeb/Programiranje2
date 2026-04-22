import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DN07 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(args[0]));
        Planet[] planeti = new Planet[8];
        for (int i = 0; i<8; i++){
            String[] planet = sc.next().split(":");
            planeti[i] = new Planet(planet[0], Integer.parseInt(planet[1]));
        }
        sc.close();

        double vsota = 0;
        for (String imePlaneta: args[1].split("\\+")){
            for (Planet p : planeti){
                if (p.ime.equalsIgnoreCase(imePlaneta)){
                    vsota += p.povrsina();
                    break;
                }
            }

        }

        System.out.printf("Povrsina planetov \"%s\" je %d milijonov km2", args[1], Math.round(vsota / 1000000));
    }
}
class Planet {
    String ime;
    int radij;

    public Planet(String ime, int radij){
        this.ime = ime;
        this.radij = radij;
    }

    double povrsina(){
        return 4 * Math.PI * Math.pow(radij, 2);
    }
}