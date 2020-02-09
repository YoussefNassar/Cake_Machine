package communication.server.servercommunication;



//import communication.events.AddHerstellerEvent;
//import communication.events.DeleteKuchenEvent;
//import communication.events.ListingEvent;
import communication.server.eventPattern.handlers.AddHerstellerHandler;
import communication.server.eventPattern.handlers.AddKuchenHandler;
import communication.server.eventPattern.handlers.DeleteKuchenHandler;
import communication.server.eventPattern.handlers.ListingHandler;
import communication.server.eventPattern.listeners.AddHerstellerListener;
import communication.server.eventPattern.listeners.AddKuchenListener;
import communication.server.eventPattern.listeners.DeleteKuchenListener;
import communication.server.eventPattern.listeners.ListingListener;

//import events.ausloeser.AusLoeser;
import Model.Allergen;
import Model.KuchenImp;
import Model.Hersteller;
import Model.HerstellerImp;
import Logic.Verwaltung;
import events.events.AddHerstellerEvent;
import events.events.AddKuchenEvent;
import events.events.DeleteKuchenEvent;
import events.events.ListingEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

import static Model.Allergen.Erdnuss;
import static Model.Allergen.Gluten;

public class Initializing {
    //TCPChannel tcpChannel;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    AddKuchenHandler addKuchenHandler;
    AddHerstellerHandler addHerstellerHandler;
    DeleteKuchenHandler deleteKuchenHandler;
    ListingHandler listingHandler;

    AddHerstellerListener addHerstellerListener;
    DeleteKuchenListener deleteKuchenListener;
    ListingListener listingListener;
    AddKuchenListener addKuchenListener;


    public Initializing(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) throws IOException {
        //this.tcpChannel = tcpChannel;
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
        //this.objectInputStream = new ObjectInputStream(tcpChannel.getInputStream());
        //this.objectOutputStream =  new ObjectOutputStream(tcpChannel.getOutputStream());

    }




    public void initMethode() throws Exception {

        /**
         * setting the Event-Pattern
         */
        Verwaltung verwaltung = new Verwaltung();
//        AusLoeser ausLoeser = new AusLoeser();

        addKuchenHandler = new AddKuchenHandler();
        addHerstellerHandler = new AddHerstellerHandler();
        deleteKuchenHandler = new DeleteKuchenHandler();
        listingHandler = new ListingHandler();

        addKuchenListener = new AddKuchenListener(verwaltung,objectInputStream,objectOutputStream);
        addHerstellerListener = new AddHerstellerListener(verwaltung, objectInputStream, objectOutputStream);
        deleteKuchenListener = new DeleteKuchenListener(verwaltung,objectOutputStream,objectInputStream);
        listingListener = new ListingListener(verwaltung,objectInputStream,objectOutputStream);

        addHerstellerHandler.add(addHerstellerListener);
        addKuchenHandler.add(addKuchenListener);
        deleteKuchenHandler.add(deleteKuchenListener);
        listingHandler.add(listingListener);

//        ausLoeser.setAddHerstellerHandler(addHerstellerHandler);
//        ausLoeser.setAddKuchenHandler(addKuchenHandler);
//        ausLoeser.setDeleteKuchenHandler(deleteKuchenHandler);


        /**
         * adding kuchen for debugging purposes
         */
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);
        Hersteller hersteller = new HerstellerImp("Lidl");
        KuchenImp kuchen1  = new KuchenImp(bigDecimal,hersteller,allergens,10,
                Duration.ofDays(10),"Kremkchen");

        KuchenImp kuchen2  = new KuchenImp(bigDecimal,hersteller,allergens,10,
                Duration.ofDays(10),"Kremkchen");

        verwaltung.addKuchen(kuchen1);
        verwaltung.addKuchen(kuchen2);

    }


    public void serverFunctions() throws Exception {
        boolean b = true;
        while (b) {
            String received = objectInputStream.readUTF();
            switch (received) {
                case "addhersteller":
                    AddHerstellerEvent addHerstellerEvent = (AddHerstellerEvent) objectInputStream.readObject();
                    addHerstellerListener.onAddHerstellerListener(addHerstellerEvent);
                    break;
                case "listing":
                    ListingEvent listingEvent = (ListingEvent) objectInputStream.readObject();
                    listingListener.onListing(listingEvent);
                    break;
                case "deletekuchen":
                    DeleteKuchenEvent deleteKuchenEvent = (DeleteKuchenEvent) objectInputStream.readObject();
                    deleteKuchenListener.onDeleteKuchenListener(deleteKuchenEvent);
                    break;
                case "addKuchen":
                    AddKuchenEvent addKuchenEvent = (AddKuchenEvent) objectInputStream.readObject();
                    addKuchenListener.onAddKuchenListener(addKuchenEvent);
                    break;
                case "exit":
                    b = false;
                    break;
                default:
                    objectOutputStream.writeUTF("unknowncommand");
                    objectOutputStream.flush();
                    break;
            }
        }
    }
}