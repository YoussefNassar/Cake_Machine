package communication.server.eventPattern.listeners;

import CLI.Main;
import Logic.Verwaltung;
import events.events.ListingEvent;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.mockito.Mockito.*;

class ListingListenerTest {

    @Test
    void onListing() throws IOException {
        Verwaltung verwaltungSpy = spy(Verwaltung.class);
        ObjectInputStream oisMok = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ListingListener listingListener = new ListingListener(verwaltungSpy,oisMok,oosMock);
        listingListener.onListing(new ListingEvent(Main.class));
        verify(verwaltungSpy).getKuchenList();
    }
}