package communication;

//import communication.events.AddHerstellerEvent;
//import communication.events.DeleteKuchenEvent;
//import communication.events.ListingEvent;

import Model.Allergen;
import communication.server.servercommunication.Initializing;
import communication.TCPChannel.TCPChannel;
import Model.KuchenImp;
import CLI.Main;
import events.events.AddHerstellerEvent;
import events.events.AddKuchenEvent;
import events.events.DeleteKuchenEvent;
import events.events.ListingEvent;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InitializingTest {
    Initializing initializing;


    @Test
    public void addHerstellerTest() throws Exception {
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        doNothing().when(tcpChannelMock).checkConnected();


        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeUTF("addhersteller");
        oos.writeObject(new AddHerstellerEvent(Main.class, "hersteller"));
        oos.writeUTF("exit");
        oos.flush();

        when(tcpChannelMock.getInputStream()).thenReturn(new ByteArrayInputStream(bos.toByteArray()));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        when(tcpChannelMock.getOutputStream()).thenReturn(out);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(tcpChannelMock.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(tcpChannelMock.getInputStream());
        this.initializing = new Initializing(objectInputStream, objectOutputStream);
        this.initializing.initMethode();
        this.initializing.serverFunctions();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));

        assertEquals("ok", ois.readUTF());
    }


    @Test
    public void ListingTest() throws Exception {
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        doNothing().when(tcpChannelMock).checkConnected();


        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeUTF("listing");
        oos.writeObject(new ListingEvent(Main.class));
        oos.writeUTF("exit");
        oos.flush();

        when(tcpChannelMock.getInputStream()).thenReturn(new ByteArrayInputStream(bos.toByteArray()));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        when(tcpChannelMock.getOutputStream()).thenReturn(out);


        ObjectOutputStream objectOutputStream = new ObjectOutputStream(tcpChannelMock.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(tcpChannelMock.getInputStream());
        this.initializing = new Initializing(objectInputStream, objectOutputStream);
        this.initializing.initMethode();
        this.initializing.serverFunctions();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));
        assertEquals("ok", ois.readUTF());

        ArrayList<KuchenImp> kuchenImps = (ArrayList<KuchenImp>) ois.readObject();
        assertEquals(2, kuchenImps.size());
    }


    @Test
    public void DeleteKuchenTest() throws Exception {
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        doNothing().when(tcpChannelMock).checkConnected();


        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        ObjectOutputStream oos = new ObjectOutputStream(bos);


        oos.writeUTF("deletekuchen");
        oos.writeObject(new DeleteKuchenEvent(Main.class, 1));
        oos.writeUTF("exit");
        oos.flush();

        when(tcpChannelMock.getInputStream()).thenReturn(new ByteArrayInputStream(bos.toByteArray()));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        when(tcpChannelMock.getOutputStream()).thenReturn(out);


        ObjectOutputStream objectOutputStream = new ObjectOutputStream(tcpChannelMock.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(tcpChannelMock.getInputStream());
        this.initializing = new Initializing(objectInputStream, objectOutputStream);
        this.initializing.initMethode();
        this.initializing.serverFunctions();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));

        assertEquals("ok", ois.readUTF());
    }

    @Test
    public void AddKuchenTest() throws Exception {
        BigDecimal preis = BigDecimal.valueOf(10);
        String hersteller = "hersteller";
        ArrayList<Allergen> allergen = new ArrayList<>();
        int naehrwert =1;
        Duration haltbarkeit = Duration.ofDays(1);
        String type="kremKuchen";

        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        doNothing().when(tcpChannelMock).checkConnected();


        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        ObjectOutputStream oos = new ObjectOutputStream(bos);


        oos.writeUTF("addKuchen");
        oos.writeObject(new AddKuchenEvent(Main.class,preis,hersteller,allergen,naehrwert,haltbarkeit,type));
        oos.writeUTF("exit");
        oos.flush();

        when(tcpChannelMock.getInputStream()).thenReturn(new ByteArrayInputStream(bos.toByteArray()));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        when(tcpChannelMock.getOutputStream()).thenReturn(out);


        ObjectOutputStream objectOutputStream = new ObjectOutputStream(tcpChannelMock.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(tcpChannelMock.getInputStream());
        this.initializing = new Initializing(objectInputStream, objectOutputStream);
        this.initializing.initMethode();
        this.initializing.serverFunctions();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));

        assertEquals("ok", ois.readUTF());
    }

    @Test
    void serverFunctionDefaultStatement() throws Exception {
        TCPChannel tcpChannelMock = mock(TCPChannel.class);
        doNothing().when(tcpChannelMock).checkConnected();


        ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
        ObjectOutputStream oos = new ObjectOutputStream(bos);


        oos.writeUTF("unvalidCommand");
        oos.flush();
        oos.writeUTF("exit");
        oos.flush();

        when(tcpChannelMock.getInputStream()).thenReturn(new ByteArrayInputStream(bos.toByteArray()));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        when(tcpChannelMock.getOutputStream()).thenReturn(out);


        ObjectOutputStream objectOutputStream = new ObjectOutputStream(tcpChannelMock.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(tcpChannelMock.getInputStream());
        this.initializing = new Initializing(objectInputStream, objectOutputStream);
        this.initializing.initMethode();
        this.initializing.serverFunctions();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));

        assertEquals("unknowncommand", ois.readUTF());
    }

}


////    @Test
////    public void firstTest() throws Exception {
////
////        TCPChannel tcpChannelMoch = mock(TCPChannel.class);
////
////        ByteArrayOutputStream bos = new ByteArrayOutputStream(14);
////        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);  // for the client
////
////        objectOutputStream.writeUTF("addhersteller");
////        objectOutputStream.flush();
////        AddHerstellerEvent herstellerEvent = new AddHerstellerEvent(Main.class,"hersteller");
////        objectOutputStream.writeObject(herstellerEvent);
////        objectOutputStream.flush();
////
////        when(tcpChannelMoch.getInputStream()).thenReturn(new ByteArrayInputStream(bos.toByteArray()));
////        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
////        when(tcpChannelMoch.getOutputStream()).thenReturn(out);
////
////
////
////        //this.initializing = new Initializing(tcpChannelMoch);
////
////        this.initializing.initMethode();
////        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(out.toByteArray()));
////
////        this.initializing.serverFunctions();
////        assertEquals("ok",dis.readUTF());
////
////
////
////
////
////
////    }
//
//
//    @Test
//    public void secondTest() throws Exception {
//
//        TCPChannel tcpChannelMock = mock(TCPChannel.class);
//        doNothing().when(tcpChannelMock).checkConnected();
//
//        InputStream inputStreamMock = mock(InputStream.class);
//        OutputStream outputStreamMock = mock(OutputStream.class);
//
////        when(tcpChannelMock.getInputStream()).thenReturn(inputStreamMock);
////        when(tcpChannelMock.getOutputStream()).thenReturn(outputStreamMock);
//
//
//        ObjectInputStream objectInputStream = mock(ObjectInputStream.class);
//        ObjectOutputStream objectOutputStream = mock(ObjectOutputStream.class);
//
//        this.initializing = new Initializing(objectInputStream,objectOutputStream);
//
//        AddHerstellerEvent addHerstellerEvent = new AddHerstellerEvent(Main.class,"hersteller");
//
//        when(objectInputStream.readUTF()).thenReturn("addhersterller");
//        when(objectInputStream.readObject()).thenReturn(addHerstellerEvent);
//
//        this.initializing.serverFunctions();
//
//        String answer = this.initializing.objectInputStream.readUTF();
//        assertEquals("ok",answer);
//
//        when(objectInputStream.readUTF()).thenReturn("exit");
//    }