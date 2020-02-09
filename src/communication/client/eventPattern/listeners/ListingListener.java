package communication.client.eventPattern.listeners;

//import communication.client.cientCommunication.TCPChannel;
//import communication.events.ListingEvent;
import Model.Kuchen;
import communication.TCPChannel.TCPChannel;
import events.events.ListingEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ListingListener {
    TCPChannel tcpChannel;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public ListingListener(TCPChannel tcpChannel, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.tcpChannel = tcpChannel;
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
    }

    public void onListing(ListingEvent listingEvent) throws IOException, ClassNotFoundException {
//        objectInputStream.reset();
        objectOutputStream.reset();
        objectOutputStream.writeUTF("listing");
        objectOutputStream.flush();
        objectOutputStream.writeObject(listingEvent);
        objectOutputStream.flush();
        String answer = objectInputStream.readUTF();
        if(answer.equals("ok")){
            System.out.println("----------------");
            System.out.println("list received");
            ArrayList<Kuchen> kuchenautomat =(ArrayList<Kuchen>) objectInputStream.readObject();
            System.out.println("the size of the kuchenautomat :");
            System.out.println(kuchenautomat.size());
            System.out.println(kuchenautomat.toString());
            System.out.println("---------------");
        }else if(answer.equals("f")) {
            System.out.println("something went wrong");
        }
    }
}