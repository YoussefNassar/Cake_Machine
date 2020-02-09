package communication.client.eventPattern.handlers;

import communication.client.eventPattern.listeners.DeleteKuchenListener;
//import communication.events.DeleteKuchenEvent;
import communication.TCPChannel.TCPChannel;
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
        deleteKuchenHandler = new DeleteKuchenHandler();
    }

    @Test
    void add() throws Exception {
        DeleteKuchenEvent deleteKuchenEvent = mock(DeleteKuchenEvent.class);
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        DeleteKuchenListener deleteKuchenListener = new DeleteKuchenListener(tcpChannelMock,oisMock,oosMock);
        DeleteKuchenListener deleteKuchenListenerMock = spy(deleteKuchenListener);
        deleteKuchenHandler.add(deleteKuchenListenerMock);
        when(oisMock.readUTF()).thenReturn("ok");
        deleteKuchenHandler.handle(deleteKuchenEvent);
        verify(deleteKuchenListenerMock).onDeleteKuchenListener(deleteKuchenEvent);
    }

    @Test
    void remove() throws Exception {
        DeleteKuchenEvent deleteKuchenEvent = mock(DeleteKuchenEvent.class);
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        DeleteKuchenListener deleteKuchenListener = new DeleteKuchenListener(tcpChannelMock,oisMock,oosMock);
        DeleteKuchenListener deleteKuchenListenerMock = spy(deleteKuchenListener);
        deleteKuchenHandler.add(deleteKuchenListenerMock);
        deleteKuchenHandler.remove(deleteKuchenListenerMock);
        when(oisMock.readUTF()).thenReturn("ok");
        deleteKuchenHandler.handle(deleteKuchenEvent);
        verify(deleteKuchenListenerMock,never()).onDeleteKuchenListener(deleteKuchenEvent);
    }
}