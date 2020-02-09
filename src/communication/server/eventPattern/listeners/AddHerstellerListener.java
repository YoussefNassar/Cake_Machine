package communication.server.eventPattern.listeners;

//import communication.events.AddHerstellerEvent;
import Model.Hersteller;
import Model.HerstellerImp;
import Logic.Verwaltung;
import events.events.AddHerstellerEvent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AddHerstellerListener {
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Verwaltung verwaltung;

    public AddHerstellerListener(Verwaltung verwaltung, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
        this.verwaltung = verwaltung;
    }

    public void onAddHerstellerListener(AddHerstellerEvent addHersteller) throws Exception {

        String name = addHersteller.getHerstellerName();
        Hersteller hersteller = new HerstellerImp(name);
        try {
            verwaltung.addHersteller(hersteller);
            objectOutputStream.writeUTF("ok");
            objectOutputStream.flush();
        } catch (Exception e) {
            objectOutputStream.writeUTF("f");
            objectOutputStream.flush();
        }
    }
}
