package communication.server.eventPattern.listeners;

import CLI.Main;
import Logic.Verwaltung;
import events.events.AddHerstellerEvent;
import org.junit.jupiter.api.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.mockito.Mockito.*;

class AddHerstellerListenerTest {

    @Test
    void onAddHerstellerListenerGoodTest() throws Exception {
        Verwaltung verwaltungSpy = spy(Verwaltung.class);
        ObjectInputStream oisMok = mock(ObjectInputStream.class);
        ObjectOutputStream ousMock = mock(ObjectOutputStream.class);
        AddHerstellerListener addHerstellerListener = new AddHerstellerListener(verwaltungSpy,oisMok,ousMock);
        addHerstellerListener.onAddHerstellerListener(new AddHerstellerEvent(Main.class,"hersteller"));
        verify(verwaltungSpy).addHersteller(any());
    }
}