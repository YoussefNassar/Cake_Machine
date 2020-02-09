package communication.client.eventPattern.listeners;

import CLI.Main;
import Model.KuchenImp;
import communication.TCPChannel.TCPChannel;
import events.events.ListingEvent;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ListingListenerTest {

    @Test
    void onListingSuccessTest() throws IOException, ClassNotFoundException {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ListingListener listingListener = new ListingListener(tcpChannelMock,oosMock,oisMock);
        when(oisMock.readUTF()).thenReturn("ok");
        when(oisMock.readObject()).thenReturn(new ArrayList<KuchenImp>());
        listingListener.onListing(new ListingEvent(Main.class));
        String answer = outContent.toString();
        assertTrue(answer.contains("list received"));
    }

    @Test
    void onListingErrorTest() throws IOException, ClassNotFoundException {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ListingListener listingListener = new ListingListener(tcpChannelMock,oosMock,oisMock);
        when(oisMock.readUTF()).thenReturn("f");
        listingListener.onListing(new ListingEvent(Main.class));
        String answer = outContent.toString();
        assertTrue(answer.contains("something went wrong"));
    }

}