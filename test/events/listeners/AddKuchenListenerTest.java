package events.listeners;

import Logic.Verwaltung;
import events.events.AddKuchenEvent;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AddKuchenListenerTest {

    //Todo
    //ask if this is a good test and with this test the event pattern is tested


    @Test
    void onAddKuchenListener() throws Exception {
        Verwaltung verwaltungSpy = spy(Verwaltung.class);
        AddKuchenEvent addKuchenEventMock = mock(AddKuchenEvent.class);
        AddKuchenListener addKuchenListener = new AddKuchenListener(verwaltungSpy);
        addKuchenListener.onAddKuchenListener(addKuchenEventMock);
        verify(verwaltungSpy).addKuchen(any());
    }
}