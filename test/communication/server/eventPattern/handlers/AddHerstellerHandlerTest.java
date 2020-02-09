package communication.server.eventPattern.handlers;

import CLI.Main;
import Logic.Verwaltung;
import communication.server.eventPattern.listeners.AddHerstellerListener;
import events.events.AddHerstellerEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.mockito.Mockito.*;

class AddHerstellerHandlerTest {

    AddHerstellerHandler addHerstellerHandler;

    @BeforeEach
    void setUp(){
        addHerstellerHandler = new AddHerstellerHandler();
    }

    @Test
    void addingListenerToListAndUsingIt() throws Exception {
        Verwaltung verwaltungMock = mock(Verwaltung.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        AddHerstellerListener addHerstellerListener = new AddHerstellerListener(verwaltungMock,oisMock,oosMock);
        AddHerstellerListener addHerstellerListenerSpy = spy(addHerstellerListener);
        addHerstellerHandler.add(addHerstellerListenerSpy);
        addHerstellerHandler.handle(new AddHerstellerEvent(Main.class,"hersteller"));
        verify(addHerstellerListenerSpy).onAddHerstellerListener(any());
    }

    @Test
    void removingListenerFromList() throws Exception {
        Verwaltung verwaltungMock = mock(Verwaltung.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        AddHerstellerListener addHerstellerListener = new AddHerstellerListener(verwaltungMock,oisMock,oosMock);
        AddHerstellerListener addHerstellerListenerSpy = spy(addHerstellerListener);
        addHerstellerHandler.add(addHerstellerListenerSpy);
        addHerstellerHandler.remove(addHerstellerListenerSpy);
        addHerstellerHandler.handle(new AddHerstellerEvent(Main.class,"hersteller"));
        verify(addHerstellerListenerSpy,never()).onAddHerstellerListener(any());
    }
}