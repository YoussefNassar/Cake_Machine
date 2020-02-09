package events.listeners;

import events.events.AddKuchenEvent;
import Model.Allergen;
import Model.KuchenImp;
import Model.HerstellerImp;
import Logic.Verwaltung;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

public class AddKuchenListener {
    Verwaltung verwaltung;

    public AddKuchenListener(Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
    }

    public void onAddKuchenListener(AddKuchenEvent addKuchenEvent) {
        BigDecimal price = addKuchenEvent.getPrice();
        String herstellerName = addKuchenEvent.getHerstellerName();
        HerstellerImp hersteller = new HerstellerImp(herstellerName);
        ArrayList<Allergen> allergens = addKuchenEvent.getAllergens();
        int nahrwaert = addKuchenEvent.getNahrwaert();
        Duration haltbarkeit = addKuchenEvent.getHaltbarkeit();
        String type = addKuchenEvent.getType();

        KuchenImp kuchen = new KuchenImp(price, hersteller, allergens, nahrwaert, haltbarkeit, type);

        try {
            verwaltung.addKuchen(kuchen);
            System.out.println("successful");
        } catch (Exception e) {
            System.out.println("something went wrong");
        }
    }
}
