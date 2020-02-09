package events.event;

import CLI.Main;
import events.events.AddHerstellerEvent;
import events.events.DeleteKuchenEvent;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteKuchenEventTest {


    //todo
    // ask if this is coorect
    /**
     * new Test 29.01.20
     */


    @Test
    void getFachTest(){
        Main main = mock(Main.class);
        DeleteKuchenEvent deleteKuchenEvent = new DeleteKuchenEvent(main,1);
        assertEquals(1,deleteKuchenEvent.getfach());
    }
}