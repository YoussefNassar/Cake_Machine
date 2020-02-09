package communication.client.eventPattern.listeners;

import CLI.Main;
//import communication.events.DeleteKuchenEvent;
import communication.TCPChannel.TCPChannel;
import events.events.DeleteKuchenEvent;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteKuchenListenerTest {

    @Test
    void onDeleteKuchenListenerSuccessTest() throws Exception {

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        DeleteKuchenListener deleteKuchenListener = new DeleteKuchenListener(tcpChannelMock,oisMock,oosMock);
        when(oisMock.readUTF()).thenReturn("ok");
        deleteKuchenListener.onDeleteKuchenListener(new DeleteKuchenEvent(Main.class,1));
        String answer = outContent.toString();
        assertTrue(answer.contains("delete successful"));
    }

    @Test
    void onDeleteKuchenListenerErrorTest() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        DeleteKuchenListener deleteKuchenListener = new DeleteKuchenListener(tcpChannelMock,oisMock,oosMock);
        when(oisMock.readUTF()).thenReturn("f");
        deleteKuchenListener.onDeleteKuchenListener(new DeleteKuchenEvent(Main.class,1));
        String answer = outContent.toString();
        assertTrue(answer.contains("something wrong... try again"));
    }

    @Test
    void onDeleteKuchenListenerUnknownResultTest() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        DeleteKuchenListener deleteKuchenListener = new DeleteKuchenListener(tcpChannelMock,oisMock,oosMock);
        when(oisMock.readUTF()).thenReturn("unknownResult");
        deleteKuchenListener.onDeleteKuchenListener(new DeleteKuchenEvent(Main.class,1));
        String answer = outContent.toString();
        assertTrue(answer.contains("not expected to reach here"));
    }
}
