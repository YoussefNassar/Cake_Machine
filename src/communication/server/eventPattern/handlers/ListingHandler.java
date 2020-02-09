package communication.server.eventPattern.handlers;


//import communication.events.ListingEvent;
import communication.server.eventPattern.listeners.ListingListener;
import events.events.ListingEvent;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ListingHandler {
    private List<ListingListener> listenerList = new LinkedList<>();

    public void add(ListingListener listingListener){
        this.listenerList.add(listingListener);
    }

    public void remove(ListingListener listingListener){
        this.listenerList.remove(listingListener);
    }

    public void handle(ListingEvent listingEvent) throws IOException {
        for (ListingListener listingListener : this.listenerList){
            listingListener.onListing(listingEvent);
        }
    }
}
