package communication.client.eventPattern.handlers;

import communication.client.eventPattern.listeners.AddKuchenListener;
//import communication.events.AddKuchenEvent;
import communication.TCPChannel.TCPChannel;
import events.events.AddKuchenEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.mockito.Mockito.*;

class AddKuchenHandlerTest {

    AddKuchenHandler addKuchenHandler;

    @BeforeEach
    void setUp(){
        this.addKuchenHandler = new AddKuchenHandler();
    }

    @Test
    void addingListenerToListTest() throws Exception {
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        AddKuchenEvent addKuchenEvent = mock(AddKuchenEvent.class);
        AddKuchenListener addKuchenListener = new AddKuchenListener(tcpChannelMock,oisMock,oosMock);
        AddKuchenListener addKuchenListenerSpy = spy(addKuchenListener);
        when(oisMock.readUTF()).thenReturn("ok");
        addKuchenHandler.add(addKuchenListenerSpy);
        addKuchenHandler.handle(addKuchenEvent);
        verify(addKuchenListenerSpy).onAddKuchenListener(addKuchenEvent);
    }

    @Test
    void removingListenerfromList() throws Exception {
        TCPChannel tcpChannelmock = mock(TCPChannel.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        AddKuchenEvent addKuchenEvent = mock(AddKuchenEvent.class);
        AddKuchenListener addKuchenListener = new AddKuchenListener(tcpChannelmock,oisMock,oosMock);
        AddKuchenListener addKuchenListenerSpy = spy(addKuchenListener);
        addKuchenHandler.add(addKuchenListenerSpy);
        addKuchenHandler.remove(addKuchenListenerSpy);
        addKuchenHandler.handle(addKuchenEvent);
        verify(addKuchenListenerSpy,never()).onAddKuchenListener(addKuchenEvent);
    }
}