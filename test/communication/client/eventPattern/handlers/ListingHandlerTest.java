package communication.client.eventPattern.handlers;

import CLI.Main;
import Model.KuchenImp;
import communication.client.eventPattern.listeners.ListingListener;
//import communication.events.ListingEvent;
import communication.TCPChannel.TCPChannel;
import events.events.ListingEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

class ListingHandlerTest {

    ListingHandler listingHandler;

    @BeforeEach
    void setUp(){
        listingHandler = new ListingHandler();
    }

    @Test
    void add() throws IOException, ClassNotFoundException {
        ListingEvent listingEvent = new ListingEvent(Main.class);
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ListingListener listingListener = new ListingListener(tcpChannelMock,oosMock,oisMock);
        ListingListener listingListenerSpy = spy(listingListener);
        listingHandler.add(listingListenerSpy);
        when(oisMock.readUTF()).thenReturn("ok");
        when(oisMock.readObject()).thenReturn(new ArrayList<KuchenImp>());
        listingHandler.handle(listingEvent);
        verify(listingListenerSpy).onListing(listingEvent);
    }

    @Test
    void remove() throws IOException, ClassNotFoundException {
        ListingEvent listingEvent = new ListingEvent(Main.class);
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        ListingListener listingListener = new ListingListener(tcpChannelMock,oosMock,oisMock);
        ListingListener listingListenerSpy = spy(listingListener);
        listingHandler.add(listingListenerSpy);
        listingHandler.remove(listingListenerSpy);
        listingHandler.handle(listingEvent);
        verify(listingListenerSpy,never()).onListing(listingEvent);
    }
}