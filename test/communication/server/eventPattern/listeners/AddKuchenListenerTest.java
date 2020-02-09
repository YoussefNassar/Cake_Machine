package communication.server.eventPattern.listeners;

import Logic.Verwaltung;
import events.events.AddKuchenEvent;
import org.junit.jupiter.api.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddKuchenListenerTest {

    @Test
    void onAddKuchenListener() throws Exception {
        Verwaltung verwaltungSpy = spy(Verwaltung.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ObjectInputStream oinsMock = mock(ObjectInputStream.class);
        AddKuchenListener addKuchenListener = new AddKuchenListener(verwaltungSpy,oinsMock,oosMock);
        AddKuchenEvent addKuchenEvent = mock(AddKuchenEvent.class);
        addKuchenListener.onAddKuchenListener(addKuchenEvent);
        verify(verwaltungSpy).addKuchen(any());
    }
}