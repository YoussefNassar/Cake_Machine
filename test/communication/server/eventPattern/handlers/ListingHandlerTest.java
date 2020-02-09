package communication.server.eventPattern.handlers;

import CLI.Main;
import Logic.Verwaltung;
import communication.server.eventPattern.listeners.ListingListener;
import events.events.ListingEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.mockito.Mockito.*;

class ListingHandlerTest {

    ListingHandler listingHandler;
    @BeforeEach
    void setUp(){
        listingHandler = new ListingHandler();
    }

    @Test
    void addingListenerToTheListAndUsingItTest() throws IOException {
        Verwaltung verwaltungMock = mock(Verwaltung.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ListingListener listingListener = new ListingListener(verwaltungMock,oisMock,oosMock);
        ListingListener listingListenerSpy = spy(listingListener);
        listingHandler.add(listingListenerSpy);
        listingHandler.handle(new ListingEvent(Main.class));
        verify(listingListenerSpy).onListing(any());
    }

    @Test
    void remove() throws IOException{
        Verwaltung verwaltungMock = mock(Verwaltung.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ListingListener listingListener = new ListingListener(verwaltungMock,oisMock,oosMock);
        ListingListener listingListenerSpy = spy(listingListener);
        listingHandler.add(listingListenerSpy);
        listingHandler.remove(listingListenerSpy);
        listingHandler.handle(new ListingEvent(Main.class));
        verify(listingListenerSpy,never()).onListing(any());
    }
}