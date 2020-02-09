package communication.client.eventPattern.listeners;

import communication.TCPChannel.TCPChannel;
import events.events.AddKuchenEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddKuchenListenerTest {
    TCPChannel tcpChannelMock;
    ObjectInputStream oisMock;
    ObjectOutputStream oosMock;

    @BeforeEach
    void setUp(){
        tcpChannelMock = mock(TCPChannel.class);
        oisMock = mock(ObjectInputStream.class);
        oosMock = mock(ObjectOutputStream.class);
    }

    @Test
    void onAddKuchenListenerOkTest() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        AddKuchenListener addKuchenListener = new AddKuchenListener(tcpChannelMock,oisMock,oosMock);
        when(oisMock.readUTF()).thenReturn("ok");
        AddKuchenEvent addKuchenEvent = mock(AddKuchenEvent.class);
        addKuchenListener.onAddKuchenListener(addKuchenEvent);
        assertTrue(outContent.toString().contains("success"));
    }

    @Test
    void onAddKuchenListenerErrorTest() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        AddKuchenListener addKuchenListener = new AddKuchenListener(tcpChannelMock,oisMock,oosMock);
        when(oisMock.readUTF()).thenReturn("f");
        AddKuchenEvent addKuchenEvent = mock(AddKuchenEvent.class);
        addKuchenListener.onAddKuchenListener(addKuchenEvent);
        assertTrue(outContent.toString().contains("something went wrong"));
    }

    @Test
    void onAddKuchenListenerUnknownResultTest() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        AddKuchenListener addKuchenListener = new AddKuchenListener(tcpChannelMock,oisMock,oosMock);
        when(oisMock.readUTF()).thenReturn("unknown result");
        AddKuchenEvent addKuchenEvent = mock(AddKuchenEvent.class);
        addKuchenListener.onAddKuchenListener(addKuchenEvent);
        assertTrue(outContent.toString().contains("should not reach the else statement"));
    }
}