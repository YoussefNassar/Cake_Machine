package events.event;

import CLI.Main;
import events.events.ListingEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListingEventTest {

    @Test
    void constructorTest(){
        ListingEvent listingEvent = new ListingEvent(Main.class);
        assertNotEquals(null,listingEvent.getSource());
    }
}