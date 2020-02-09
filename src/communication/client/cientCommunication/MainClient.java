package communication.client.cientCommunication;

//import communication.client.events.ausloeser.AusLoeser;

import CLI.Main;
import communication.client.eventPattern.handlers.AddHerstellerHandler;
import communication.client.eventPattern.handlers.AddKuchenHandler;
import communication.client.eventPattern.handlers.DeleteKuchenHandler;
import communication.client.eventPattern.handlers.ListingHandler;
import communication.client.eventPattern.listeners.AddHerstellerListener;
import communication.client.eventPattern.listeners.AddKuchenListener;
import communication.client.eventPattern.listeners.DeleteKuchenListener;
import communication.client.eventPattern.listeners.ListingListener;

//import communication.events.AddHerstellerEvent;
//import communication.events.DeleteKuchenEvent;
//import communication.events.ListingEvent;


import Model.Allergen;
import Model.KuchenImp;
import Model.Hersteller;
import Model.HerstellerImp;
import Logic.Verwaltung;
import communication.TCPChannel.TCPChannel;
import events.events.AddHerstellerEvent;
import events.events.AddKuchenEvent;
import events.events.DeleteKuchenEvent;
import events.events.ListingEvent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static Model.Allergen.Erdnuss;
import static Model.Allergen.Gluten;
import static org.mockito.Mockito.mock;

public class MainClient {
    static TCPChannel tcpChannel;
    static Scanner scanner = new Scanner(System.in);
    private static AddHerstellerListener addHerstellerListener;
    private static ListingListener listingListener;
    private static DeleteKuchenListener deleteKuchenListener;
    private static AddKuchenListener addKuchenListener;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;

    public static void main(String[] args) throws Exception {

        tcpChannel = new TCPChannel(8080, false, "client");
        tcpChannel.run();

        objectOutputStream = new ObjectOutputStream(tcpChannel.getOutputStream());
        objectInputStream = new ObjectInputStream(tcpChannel.getInputStream());
        /**
         * setting up a kuchen
         */
        Hersteller hersteller = new HerstellerImp("KuchenHersteller1");
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);

        KuchenImp kuchen1 = new KuchenImp(bigDecimal, hersteller, allergens,
                10, Duration.ofDays(10), "Kremkchen");

        /**
         * setting the Event-Pattern
         */

        Verwaltung verwaltung = new Verwaltung();
//        AusLoeser ausLoeser = new AusLoeser();

        AddKuchenHandler addKuchenHandler = new AddKuchenHandler();
        AddHerstellerHandler addHerstellerHandler = new AddHerstellerHandler();
        DeleteKuchenHandler deleteKuchenHandler = new DeleteKuchenHandler();
        ListingHandler listingHandler = new ListingHandler();

        addHerstellerListener = new AddHerstellerListener(tcpChannel, objectInputStream, objectOutputStream);
        addKuchenListener = new AddKuchenListener(tcpChannel, objectInputStream, objectOutputStream);
        deleteKuchenListener = new DeleteKuchenListener(tcpChannel, objectInputStream, objectOutputStream);
        listingListener = new ListingListener(tcpChannel, objectOutputStream, objectInputStream);

        addHerstellerHandler.add(addHerstellerListener);
        addKuchenHandler.add(addKuchenListener);
        deleteKuchenHandler.add(deleteKuchenListener);
        listingHandler.add(listingListener);

//        ausLoeser.setAddHerstellerHandler(addHerstellerHandler);
//        ausLoeser.setAddKuchenHandler(addKuchenHandler);
//        ausLoeser.setDeleteKuchenHandler(deleteKuchenHandler);
        System.out.println();
        System.out.println("********IMPORTANT**********");
        System.out.println("Es gibt ZWEI kuchen in der kuchenAutomat");
        System.out.println("***************************");
        MainClient mainClient = new MainClient();
        mainClient.startClient();

    }

    private void startClient() throws Exception {

        BigDecimal price=BigDecimal.valueOf(10);;
        String herstellerName="hersteller";
        ArrayList<Allergen> allergens=new ArrayList<>();
        int nahrwaert=1;
        Duration haltbarkeit=Duration.ofDays(1);
        String type = "kremKuchen";;
        allergens.add(Allergen.Erdnuss);

        while (true) {
            int cmd = 10;
            System.out.println("enter a number and make an Action");
            System.out.println("1 -add Hersteller");
            System.out.println("2 -list kuchen");
            System.out.println("3 -delete kuchen");
            System.out.println("4 -add Kuchen");
            System.out.println("5 -to exit the program");
            try {
                cmd = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("you didn't enter an integer... try again");
            }
            scanner.nextLine();
            switch (cmd) {
                case 1:
                    addHerstellerListener.onAddHerstellerListener(new AddHerstellerEvent(CLI.Main.class, "hersteller1"));
                    break;
                case 2:
                    listingListener.onListing(new ListingEvent(CLI.Main.class));
                    break;
                case 3:
                    deleteKuchenListener.onDeleteKuchenListener(new DeleteKuchenEvent(CLI.Main.class, 1));
                    break;
                case 4:
                    addKuchenListener.onAddKuchenListener(new AddKuchenEvent(Main.class, price,herstellerName,allergens,nahrwaert,haltbarkeit,type ));
                    break;
                case 5:
                    objectOutputStream.writeUTF("exit");
                    objectOutputStream.flush();
                    System.exit(1);
                default:
                    System.out.println("unknown command");
            }
        }
    }
}
