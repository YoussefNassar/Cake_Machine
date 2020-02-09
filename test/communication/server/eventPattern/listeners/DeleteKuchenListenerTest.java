package communication.server.eventPattern.listeners;

import CLI.Main;
import Logic.Verwaltung;
import events.events.DeleteKuchenEvent;
import org.junit.jupiter.api.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.mockito.Mockito.*;

class DeleteKuchenListenerTest {

    @Test
    void onDeleteKuchenListenerGoodTest() throws Exception {
        Verwaltung verwaltungSpy = spy(Verwaltung.class);
        ObjectInputStream oisMok = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        DeleteKuchenListener deleteKuchenListener = new DeleteKuchenListener(verwaltungSpy, oosMock, oisMok);
        deleteKuchenListener.onDeleteKuchenListener(new DeleteKuchenEvent(Main.class, 1));
        verify(verwaltungSpy).deleteKcuhenInFach(1);
    }
}