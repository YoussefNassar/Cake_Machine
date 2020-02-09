package communication.client.eventPattern.handlers;

import communication.client.eventPattern.listeners.AddHerstellerListener;
//import communication.events.AddHerstellerEvent;
import communication.TCPChannel.TCPChannel;
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
    void addingListenerAndUsingItTest() throws Exception {
        AddHerstellerEvent addHerstellerEvent = mock(AddHerstellerEvent.class);
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectInputStream objectInputStreamMock = mock(ObjectInputStream.class);
        ObjectOutputStream objectOutputStreamMock = mock(ObjectOutputStream.class);
        AddHerstellerListener addHerstellerListener = new AddHerstellerListener(tcpChannelMock,objectInputStreamMock,objectOutputStreamMock);
        AddHerstellerListener addHerstellerListenerSpy = spy(addHerstellerListener);
        addHerstellerHandler.add(addHerstellerListenerSpy);
        when(objectInputStreamMock.readUTF()).thenReturn("ok");
        addHerstellerHandler.handle(addHerstellerEvent);
        verify(addHerstellerListenerSpy).onAddHerstellerListener(addHerstellerEvent);
    }

    @Test
    void removingListenerFromTheListTest() throws Exception {
        AddHerstellerEvent addHerstellerEvent = mock(AddHerstellerEvent.class);
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectInputStream objectInputStreamMock = mock(ObjectInputStream.class);
        ObjectOutputStream objectOutputStreamMock = mock(ObjectOutputStream.class);
        AddHerstellerListener addHerstellerListener = new AddHerstellerListener(tcpChannelMock,objectInputStreamMock,objectOutputStreamMock);
        AddHerstellerListener addHerstellerListenerSpy = spy(addHerstellerListener);
        addHerstellerHandler.add(addHerstellerListenerSpy);
        addHerstellerHandler.remove(addHerstellerListenerSpy);
        addHerstellerHandler.handle(addHerstellerEvent);
        verify(addHerstellerListenerSpy,never()).onAddHerstellerListener(addHerstellerEvent);
    }
}