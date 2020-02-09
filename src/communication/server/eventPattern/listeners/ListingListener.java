package communication.server.eventPattern.listeners;

//import communication.events.ListingEvent;
import Model.Kuchen;
import Logic.Verwaltung;
import events.events.ListingEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ListingListener {
    Verwaltung verwaltung;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public ListingListener(Verwaltung verwaltung, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        this.verwaltung = verwaltung;
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
    }

    public void onListing(ListingEvent listingEvent) throws IOException {
        try{
            objectOutputStream.reset();
//            objectInputStream.reset();
            ArrayList<Kuchen> kuchenArrayList = this.verwaltung.getKuchenList();
//            if (kuchenArrayList.size() == 0){
//                objectOutputStream.writeUTF("empty");
//                objectOutputStream.flush();
//                return;
//            }
            objectOutputStream.writeUTF("ok");
            objectOutputStream.flush();
            objectOutputStream.writeObject(kuchenArrayList);
            objectOutputStream.flush();
        }catch (Exception e){
            objectOutputStream.writeUTF("f");
            objectOutputStream.flush();
        }
    }
}
