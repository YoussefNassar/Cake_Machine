package events.handlers;

import events.events.ListingEvent;
import events.listeners.ListingListener;

import java.util.LinkedList;
import java.util.List;

public class ListingHandler {
    private List<ListingListener> listenerList = new LinkedList<>();

    public void add(ListingListener listner){
        this.listenerList.add(listner);
    }

    public void remove(ListingListener listener){
        this.listenerList.remove(listener);
    }

    public void handle(ListingEvent listingEvent){
        for (ListingListener listingListener : this.listenerList){
            listingListener.onListing(listingEvent);
        }
    }
}
