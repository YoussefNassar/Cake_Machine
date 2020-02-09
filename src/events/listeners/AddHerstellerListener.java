package events.listeners;

import events.events.AddHerstellerEvent;
import Model.Hersteller;
import Model.HerstellerImp;
import Logic.Verwaltung;

public class AddHerstellerListener {

    Verwaltung verwaltung;
    public AddHerstellerListener(Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
    }

    public void onAddHersteller(AddHerstellerEvent addHerstellerEvent) throws Exception {

        String name = addHerstellerEvent.getHerstellerName();
        Hersteller hersteller = new HerstellerImp(name);
        verwaltung.addHersteller(hersteller);
        System.out.println("successful");
    }
}
