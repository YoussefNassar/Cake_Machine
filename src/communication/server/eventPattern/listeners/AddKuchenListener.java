package communication.server.eventPattern.listeners;

//import communication.events.AddKuchenEvent;
import Model.Allergen;
import Model.KuchenImp;
import Model.HerstellerImp;
import Logic.Verwaltung;
import events.events.AddKuchenEvent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

public class AddKuchenListener {
    Verwaltung verwaltung;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public AddKuchenListener(Verwaltung verwaltung,ObjectInputStream objectInputStream,ObjectOutputStream objectOutputStream) {
        this.verwaltung = verwaltung;
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
    }

    public void onAddKuchenListener(AddKuchenEvent addKuchen) throws Exception {
        objectOutputStream.reset();
        BigDecimal price = addKuchen.getPrice();
        String herstellerName = addKuchen.getHerstellerName();
        HerstellerImp hersteller = new HerstellerImp(herstellerName);
        ArrayList<Allergen> allergens = addKuchen.getAllergens();
        int nahrwaert = addKuchen.getNahrwaert();
        Duration haltbarkeit = addKuchen.getHaltbarkeit();
        String type = addKuchen.getType();
        KuchenImp kuchen = new KuchenImp(price,hersteller,allergens,nahrwaert,haltbarkeit,type);
        try{
            verwaltung.addKuchen(kuchen);
            objectOutputStream.writeUTF("ok");
            objectOutputStream.flush();
        }catch (Exception e){
            objectOutputStream.writeUTF("f");
            objectOutputStream.flush();
        }

    }
}
