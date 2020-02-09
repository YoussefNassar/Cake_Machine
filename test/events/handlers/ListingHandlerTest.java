package events.handlers;

import CLI.Main;
import Logic.Verwaltung;
import events.events.ListingEvent;
import events.listeners.ListingListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ListingHandlerTest {

    ListingHandler listingHandler;

    //todo
    //ask if you should test this class
    //$ yes you should

    @BeforeEach
    void setUp(){
        listingHandler = new ListingHandler();
    }

    @Test
    void addingListenerTest() {
        Verwaltung verwaltung = mock(Verwaltung.class);
        Main main = mock(Main.class);
        ListingEvent listingEvent = new ListingEvent(Main.class);
        ListingListener listingListener = new ListingListener(verwaltung);
        ListingListener listingListener1 = spy(listingListener);
        listingHandler.add(listingListener1);
        listingHandler.handle(listingEvent);
        verify(listingListener1).onListing(listingEvent);

    }

    @Test
    void verifyingOnListingNotCalledWhenThereIsNoListener() {
        Verwaltung verwaltung = mock(Verwaltung.class);
        Main main = mock(Main.class);
        ListingEvent listingEvent = new ListingEvent(Main.class);
        ListingListener listingListener = new ListingListener(verwaltung);
        ListingListener listingListener1 = spy(listingListener);
        listingHandler.add(listingListener1);
        listingHandler.remove(listingListener1);
        listingHandler.handle(listingEvent);
        verify(listingListener1,never()).onListing(listingEvent);
    }

}