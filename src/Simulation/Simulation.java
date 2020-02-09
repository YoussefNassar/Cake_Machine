package Simulation;

import Model.Allergen;
import Model.KuchenImp;
import Model.Hersteller;
import Model.HerstellerImp;
import Logic.Verwaltung;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

import static Model.Allergen.Gluten;

public class Simulation implements Runnable {
    Verwaltung verwaltung;

    public Simulation(Verwaltung verwaltung){
        this.verwaltung = verwaltung;
    }

    public static void main(String[] args) {
        Verwaltung verwaltung = new Verwaltung();

        Simulation simulation = new Simulation(verwaltung);
        Thread einlagerungThread = new Thread(simulation);
        einlagerungThread.start();
    }


    @Override
    public void run() {
        int i = 0;
        while(true) {
            BigDecimal bigDecimal = BigDecimal.valueOf(10);
            ArrayList<Allergen> allergens = new ArrayList<Allergen>();
            allergens.add(Gluten);
            Hersteller hersteller = new HerstellerImp("Lidl"+i);
            i++;
            KuchenImp kuchen1 = new KuchenImp(bigDecimal, hersteller, allergens, 10,
                    Duration.ofDays(10), "Kremkchen");
            try {
                verwaltung.addKuchen(kuchen1);
                System.out.println("success");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
