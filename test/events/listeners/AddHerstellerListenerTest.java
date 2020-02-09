package events.listeners;

import CLI.Main;
import Logic.Verwaltung;
import events.events.AddHerstellerEvent;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AddHerstellerListenerTest {

    //Todo
    //ask if this test is valid and with this test the event pattern are tested

    @Test
    void onAddHerstellerTest() throws Exception {
        Main mainMock = mock(Main.class);
        Verwaltung verwaltungSpy = spy(Verwaltung.class);
        AddHerstellerEvent addHerstellerEvent = new AddHerstellerEvent(mainMock,"hersteller");
        AddHerstellerListener addHerstellerListener = new AddHerstellerListener(verwaltungSpy);
        addHerstellerListener.onAddHersteller(addHerstellerEvent);
        verify(verwaltungSpy).addHersteller(any());
    }
}