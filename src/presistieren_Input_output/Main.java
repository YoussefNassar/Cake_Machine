package presistieren_Input_output;


import Model.Allergen;
import Model.Kuchen;
import Model.KuchenImp;
import Model.Hersteller;
import Model.HerstellerImp;
import Logic.Verwaltung;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

import static Model.Allergen.Erdnuss;
import static Model.Allergen.Gluten;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "Data";
    static Verwaltung verwaltung;

    public static void main(String[] args) throws Exception {
        verwaltung = new Verwaltung();
        Serializing serializing = new Serializing();
        System.out.println("");
        //scanner.nextLine();

        Hersteller hersteller = new HerstellerImp("KuchenHersteller1");
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);

        Kuchen kuchen1  = new KuchenImp(bigDecimal,hersteller,allergens,
                10, Duration.ofDays(10),"Kremkchen");

        Hersteller hersteller2 = new HerstellerImp("KuchenHersteller2");
        Kuchen kuchen2  = new KuchenImp(bigDecimal,hersteller2,allergens,
                10, Duration.ofDays(10),"Kremkchen");
        try {
            verwaltung.addKuchen(kuchen1);
        } catch(Exception e ){
            System.out.println(e.getMessage());
        }
        try {
            verwaltung.addKuchen(kuchen2);
        } catch(Exception e ){
            System.out.println(e.getMessage());
        }

        System.out.println("kuchenAutomat before saving");

        for (Kuchen k1:verwaltung.getKuchenList()){
            System.out.println(k1.getHersteller().getName() +"   "+ k1);
        }
        System.out.println("--------------------------------------");

        serializing.serialize(FILE_NAME,verwaltung);

        System.out.println("verwaltung has been saved");
        System.out.println("--------------------------------------");
        System.out.println("reading verwaltung object from file : ");
        Verwaltung v2 = serializing.deserialize(FILE_NAME);
        printTest(v2);

    }




    public static void printTest(Verwaltung v2){
        for(Kuchen k : v2.getKuchenList()){
            System.out.println(k.getHersteller().getName() +"  "+ k);
        }
    }
}



//
//        System.out.println("**************************");
//        System.out.println("JBP");
//        System.out.println("saving the herstellerList");
//        JBP.serialize(verwaltung.getHerstellerList());
//        System.out.println("-----------------------");
//        //JBP.serialize(verwaltung.getHerstellerList());
//
//        System.out.println("reading hersteller JBP");
//        ArrayList<Hersteller> herstellers = new ArrayList<>();
//        herstellers = JBP.deserialize();
//        System.out.println(herstellers.size());
