package events.ausloeser;

import events.events.AddHerstellerEvent;
import events.events.AddKuchenEvent;
import events.events.DeleteKuchenEvent;
import events.events.ListingEvent;
import events.handlers.AddHerstellerHandler;
import events.handlers.AddKuchenHandler;
import events.handlers.DeleteKuchenHandler;
import events.handlers.ListingHandler;
import Model.Allergen;
import Model.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static Model.Allergen.*;

public class AusLoeser {
    private AddHerstellerHandler addHerstellerHandler;
    private AddKuchenHandler addKuchenHandler;
    private ListingHandler listingHandler;
    private DeleteKuchenHandler deleteKuchenHandler;
    private Scanner scanner;
    String type;
    String herstellerName;
    BigDecimal price;
    ArrayList<Allergen> allergens;
    int nahrwaert;
    Duration haltbarkeit;
    int count;

    public void setAddHerstellerHandler(AddHerstellerHandler addHerstellerHandler) {
        this.addHerstellerHandler = addHerstellerHandler;
    }

    public void setAddKuchenHandler(AddKuchenHandler addKuchenHandler) {
        this.addKuchenHandler = addKuchenHandler;
    }

    public void setListingHandler(ListingHandler listingHandler) {
        this.listingHandler = listingHandler;
    }

    public void setDeleteKuchenHandler(DeleteKuchenHandler deleteKuchenHandler) {
        this.deleteKuchenHandler = deleteKuchenHandler;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void start() throws Exception {


        System.out.println();
        System.out.println("*******Verkaufsautomat******");


        boolean b = true;
        while (b) {
            System.out.println("-Enter 'a' to enter the add mode");
            System.out.println("-Enter 'd' to enter Delete mode");
            System.out.println("-Enter 'l' to enter Listing mode");
            System.out.println("-Enter 'exit' to exit");
            String firstParameter = scanner.nextLine();
            switch (firstParameter.toLowerCase()) {
                case "a":
                    this.insertionMode();
                    break;
                case "d":
                    this.deleteMode();
                    break;
                case "l":
                    this.listingMode();
                    break;
                case "exit":
                    System.out.println("thank you");
                    return;
                default:
                    System.out.println("you entered an unkonow command... please try again");
                    break;
            }
        }
    }

    private void insertionMode() throws Exception {
        System.out.println("add mode active");
        System.out.println("-if you want to enter a Hersteller, then enter ONLY its name");
        System.out.print("-if you want to enter a Kuchen type : \"Add kuchen\"");
        System.out.println(" then an existing kuchen will be add");
        String secondParameter = scanner.nextLine();
        String[] parts = secondParameter.split(" ");
        if (parts.length == 1) {
            this.addHerstellerCMD(parts[0]);
        } else if (parts.length > 1 && parts[0].equalsIgnoreCase("add") && parts[1].equalsIgnoreCase("kuchen")) {
            this.addKuchenCMD();
        } else {
            System.out.println("you entered an unknown command");
        }
    }

    private void addHerstellerCMD(String herstellerName) throws Exception {
        AddHerstellerEvent addHerstellerEvent = new AddHerstellerEvent(this, herstellerName);
        addHerstellerHandler.handle(addHerstellerEvent);
}

    private void addKuchenCMD() throws Exception {
        System.out.println("adding the kuchen");
        price = BigDecimal.valueOf(2.50);
        herstellerName = "Alex" + count;
        allergens = new ArrayList<>();
        allergens.add(Erdnuss);
        allergens.add(Haselnuss);
        nahrwaert = 1400;
        haltbarkeit = Duration.ofDays(24);
        type = "Sahne";
        count++;
        AddKuchenEvent addKuchenEvent = new AddKuchenEvent(
                this, price, herstellerName, allergens, nahrwaert, haltbarkeit, type);
        addKuchenHandler.handle(addKuchenEvent);
    }


    private void deleteMode() {
        int fach = 0;
        System.out.println("delete mode active");
        System.out.println("if you want to delete a kuchen, enter a fach nummber ");
        System.out.println("please enter 1");
        try{
            fach = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("invalide input.... try again");
            System.out.println();
            return;
        }
        scanner.nextLine();
        DeleteKuchenEvent deleteKuchenEvent = new DeleteKuchenEvent(this, fach);
        deleteKuchenHandler.handle(deleteKuchenEvent);
    }

    private void listingMode() {
        System.out.println("listing mode active");
        System.out.println("the kuchen are going to be listed");
        ListingEvent listingEvent = new ListingEvent(this);
        listingHandler.handle(listingEvent);
    }
}

