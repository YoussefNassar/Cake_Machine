package events.listeners;

import CLI.Main;
import Logic.Verwaltung;
import events.events.ListingEvent;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ListingListenerTest {

    /**
     * Quelle : https://stackoverflow.com/questions/14889951/how-to-verify-a-method-is-called-two-times-with-mockito-verify
     */

    @Test
    void onListing() {
        Main mainMock = mock(Main.class);
        Verwaltung verwaltungSpy = spy(Verwaltung.class);
        ListingEvent listingEvent = new ListingEvent(mainMock);
        ListingListener listingListener = new ListingListener(verwaltungSpy);
        listingListener.onListing(listingEvent);
        verify(verwaltungSpy,times(2)).getKuchenList();
    }
}