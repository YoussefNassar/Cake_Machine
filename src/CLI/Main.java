package CLI;

import beobachter.Beobachter;
import beobachter.LetztePlatzBeobachter;
import events.ausloeser.AusLoeser;
import events.handlers.AddHerstellerHandler;
import events.handlers.AddKuchenHandler;
import events.handlers.DeleteKuchenHandler;
import events.handlers.ListingHandler;
import events.listeners.AddHerstellerListener;
import events.listeners.AddKuchenListener;
import events.listeners.DeleteKuchenListener;
import events.listeners.ListingListener;
import Model.Allergen;
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

    public static void main(String[] args) throws Exception {

        /**
         * setting up a kuchen
         */
        Hersteller hersteller = new HerstellerImp("KuchenHersteller1");
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);

        KuchenImp kuchen1  = new KuchenImp(bigDecimal,hersteller,allergens,
                10, Duration.ofDays(10),"Kremkchen");

        /**
         * setting the Event-Pattern
         */
        Verwaltung verwaltung = new Verwaltung();
        AusLoeser ausLoeser = new AusLoeser();

        AddKuchenHandler addKuchenHandler = new AddKuchenHandler();
        AddHerstellerHandler addHerstellerHandler = new AddHerstellerHandler();
        DeleteKuchenHandler deleteKuchenHandler = new DeleteKuchenHandler();
        ListingHandler listingHandler = new ListingHandler();

        AddHerstellerListener addHerstellerListener = new AddHerstellerListener(verwaltung);
        AddKuchenListener addKuchenListener = new AddKuchenListener(verwaltung);
        ListingListener listingListener = new ListingListener(verwaltung);
        DeleteKuchenListener deleteKuchenListener = new DeleteKuchenListener(verwaltung);

        addHerstellerHandler.add(addHerstellerListener);
        addKuchenHandler.add(addKuchenListener);
        listingHandler.add(listingListener);
        deleteKuchenHandler.add(deleteKuchenListener);

        ausLoeser.setAddHerstellerHandler(addHerstellerHandler);
        ausLoeser.setAddKuchenHandler(addKuchenHandler);
        ausLoeser.setDeleteKuchenHandler(deleteKuchenHandler);
        ausLoeser.setListingHandler(listingHandler);



        ausLoeser.setScanner(new Scanner(System.in));
        ausLoeser.start();



        /**
         * setting the Observer-Pattern
         */
        Verwaltung verwaltung1 = new Verwaltung();
        Beobachter beobachter = new LetztePlatzBeobachter(verwaltung);
        verwaltung.addKuchen(kuchen1);
    }
}
