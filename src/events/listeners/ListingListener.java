package events.listeners;

import events.events.ListingEvent;
import Logic.Verwaltung;

public class ListingListener {
    Verwaltung verwaltung;

    public ListingListener(Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
    }

    public void onListing(ListingEvent listingEvent){
        System.out.println("the kuchen automat has : ");
        System.out.println(this.verwaltung.getKuchenList().size());
        System.out.println("kuchen");
        System.out.println("the kuchen");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(this.verwaltung.getKuchenList().toString());
        System.out.println("--------------------------------------------------------------------------------------------");
    }
}
