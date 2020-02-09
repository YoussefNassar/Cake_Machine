package communication.client.eventPattern.listeners;

import CLI.Main;
//import communication.events.AddHerstellerEvent;
import communication.TCPChannel.TCPChannel;
import events.events.AddHerstellerEvent;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddHerstellerListenerConnectionTest {


    @Test
    void onAddHerstellerListenerOKTest() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        AddHerstellerListener addHerstellerListener = new AddHerstellerListener(tcpChannelMock,oisMock,oosMock);
        when(oisMock.readUTF()).thenReturn("ok");
        addHerstellerListener.onAddHerstellerListener(new AddHerstellerEvent(Main.class,"hersteller"));
        System.setOut(System.out);
        String answer = outContent.toString();
        assertTrue(answer.contains("success"));
    }


    @Test
    void onAddHerstellerListenerErrorTest() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        AddHerstellerListener addHerstellerListener = new AddHerstellerListener(tcpChannelMock,oisMock,oosMock);
        when(oisMock.readUTF()).thenReturn("f");
        addHerstellerListener.onAddHerstellerListener(new AddHerstellerEvent(Main.class,"hersteller"));
        System.setOut(System.out);
        String answer = outContent.toString();
        assertTrue(answer.contains("something went wrong"));
    }

    @Test
    void onAddHerstellerListenerUnexpectedTest() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ObjectInputStream oismock = mock(ObjectInputStream.class);
        AddHerstellerListener addHerstellerListener = new AddHerstellerListener(tcpChannelMock,oismock,oosMock);
        when(oismock.readUTF()).thenReturn("unknown answer");
        addHerstellerListener.onAddHerstellerListener(new AddHerstellerEvent(Main.class,"hersteller"));
        System.setOut(System.out);
        String answer = outContent.toString();
        assertTrue(answer.contains("should not reach the else statement"));
    }
}