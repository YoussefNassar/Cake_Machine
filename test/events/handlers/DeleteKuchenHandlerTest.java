package events.handlers;

import CLI.Main;
import Logic.Verwaltung;
import events.events.DeleteKuchenEvent;
import events.listeners.DeleteKuchenListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeleteKuchenHandlerTest {
    DeleteKuchenHandler deleteKuchenHandler;

    @BeforeEach
    void setUp(){
        this.deleteKuchenHandler = new DeleteKuchenHandler();
    }

    @Test
    void addingListenerToListAndUsingItTest() {
        Verwaltung verwaltung = new Verwaltung();
        DeleteKuchenListener deleteKuchenListener = new DeleteKuchenListener(verwaltung);
        DeleteKuchenListener deleteKuchenListenerSpy = spy(deleteKuchenListener);
        deleteKuchenHandler.add(deleteKuchenListenerSpy);
        deleteKuchenHandler.handle(new DeleteKuchenEvent(Main.class,1));
        verify(deleteKuchenListenerSpy).onDeleteKuchen(any());
    }

    @Test
    void removingListenerFromListTest() {
        Verwaltung verwaltung = new Verwaltung();
        DeleteKuchenListener deleteKuchenListener = new DeleteKuchenListener(verwaltung);
        DeleteKuchenListener deleteKuchenListenerSpy = spy(deleteKuchenListener);
        deleteKuchenHandler.add(deleteKuchenListenerSpy);
        deleteKuchenHandler.remove(deleteKuchenListenerSpy);
        deleteKuchenHandler.handle(new DeleteKuchenEvent(Main.class,1));
        verify(deleteKuchenListenerSpy,never()).onDeleteKuchen(any());
    }
}