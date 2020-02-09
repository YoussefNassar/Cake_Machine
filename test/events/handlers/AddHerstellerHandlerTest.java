package events.handlers;

import CLI.Main;
import Logic.Verwaltung;
import events.events.AddHerstellerEvent;
import events.listeners.AddHerstellerListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddHerstellerHandlerTest {
    AddHerstellerHandler addKuchenHandler;
    @BeforeEach
    void setUp(){
        addKuchenHandler = new AddHerstellerHandler();
    }


    @Test
    void addingListenerToListAndUsingItTest() throws Exception {
        Verwaltung verwaltung = new Verwaltung();
        AddHerstellerListener addHerstellerListener = new AddHerstellerListener(verwaltung);
        AddHerstellerListener addHerstellerListenerSpy = spy(addHerstellerListener);
        addKuchenHandler.add(addHerstellerListenerSpy);
        addKuchenHandler.handle(new AddHerstellerEvent(Main.class,"hersteller"));
        verify(addHerstellerListenerSpy).onAddHersteller(any());
    }

    @Test
    void removingListenerFromListTest() throws Exception {
        Verwaltung verwaltung = new Verwaltung();
        AddHerstellerListener addHerstellerListener = new AddHerstellerListener(verwaltung);
        AddHerstellerListener addHerstellerListenerSpy = spy(addHerstellerListener);
        addKuchenHandler.add(addHerstellerListenerSpy);
        addKuchenHandler.remove(addHerstellerListenerSpy);
        addKuchenHandler.handle(new AddHerstellerEvent(Main.class,"hersteller"));
        verify(addHerstellerListenerSpy,never()).onAddHersteller(any());
    }
}