package communication.server.eventPattern.handlers;

import CLI.Main;
import Logic.Verwaltung;
import communication.server.eventPattern.listeners.DeleteKuchenListener;
import events.events.DeleteKuchenEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.mockito.Mockito.*;

class DeleteKuchenHandlerTest {

    DeleteKuchenHandler deleteKuchenHandler;

    @BeforeEach
    void setUp(){
        this.deleteKuchenHandler = new DeleteKuchenHandler();
    }

    @Test
    void addingListenerToTheListAndUsingItTest() throws Exception {
        Verwaltung verwaltungMock = mock(Verwaltung.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        DeleteKuchenListener deleteKuchenListener = new DeleteKuchenListener(verwaltungMock,oosMock,oisMock);
        DeleteKuchenListener deleteKuchenListenerSpy = spy(deleteKuchenListener);
        this.deleteKuchenHandler.add(deleteKuchenListenerSpy);
        this.deleteKuchenHandler.handle(new DeleteKuchenEvent(Main.class,1));
        verify(deleteKuchenListenerSpy).onDeleteKuchenListener(any());
    }

    @Test
    void removingListenerFromListTest() throws Exception {
        Verwaltung verwaltungMock = mock(Verwaltung.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        DeleteKuchenListener deleteKuchenListener = new DeleteKuchenListener(verwaltungMock,oosMock,oisMock);
        DeleteKuchenListener deleteKuchenListenerSpy = spy(deleteKuchenListener);
        this.deleteKuchenHandler.add(deleteKuchenListenerSpy);
        this.deleteKuchenHandler.remove(deleteKuchenListenerSpy);
        this.deleteKuchenHandler.handle(new DeleteKuchenEvent(Main.class,1));
        verify(deleteKuchenListenerSpy,never()).onDeleteKuchenListener(any());
    }
}