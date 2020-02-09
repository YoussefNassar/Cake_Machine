package events.event;

import CLI.Main;
import events.events.AddHerstellerEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AddHerstellerEventTest {

    @Test
    void getHerstellerName() {
        Main main = mock(Main.class);
        AddHerstellerEvent addHerstellerEvent = new AddHerstellerEvent(main,"hersteller");
        assertEquals("hersteller",addHerstellerEvent.getHerstellerName());
    }
}