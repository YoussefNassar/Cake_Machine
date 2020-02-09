package events.ausloeser;

import Logic.Verwaltung;
import events.handlers.AddHerstellerHandler;
import events.handlers.AddKuchenHandler;
import events.handlers.DeleteKuchenHandler;
import events.handlers.ListingHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *  Quelle : https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
 *
 */

class AusLoeserTest {

    AusLoeser ausLoeser;
    Verwaltung verwaltung;

    @BeforeEach
    void setUp() {
        this.ausLoeser = new AusLoeser();
    }

    @Test
    void setScannerTest() {
        ausLoeser.setScanner(new Scanner(System.in));
        assertNotEquals(null, ausLoeser.getScanner());
    }


    @Test
    void addHerstllerCMDTest() throws Exception {
        AddHerstellerHandler addHerstellerHandler = spy(AddHerstellerHandler.class);
        Scanner scanner = mock(Scanner.class);
        this.ausLoeser.setScanner(scanner);
        ausLoeser.setAddHerstellerHandler(addHerstellerHandler);
        when(ausLoeser.getScanner().nextLine()).thenReturn("a").thenReturn("hersteller").thenReturn("exit");
        ausLoeser.start();
        verify(addHerstellerHandler).handle(any());

    }

    @Test
    void addKuchenCMDTest() throws Exception {
        AddKuchenHandler addKuchenHandler = spy(AddKuchenHandler.class);
        Scanner scanner = mock(Scanner.class);
        this.ausLoeser.setScanner(scanner);
        ausLoeser.setAddKuchenHandler(addKuchenHandler);
        when(ausLoeser.getScanner().nextLine()).thenReturn("a").thenReturn("add kuchen").thenReturn("exit");
        ausLoeser.start();
        verify(addKuchenHandler).handle(any());
    }

    @Test
    void deleteKuchenTest() throws Exception {
        DeleteKuchenHandler deleteKuchenHandler = spy(DeleteKuchenHandler.class);
        Scanner scanner = mock(Scanner.class);
        this.ausLoeser.setScanner(scanner);
        ausLoeser.setDeleteKuchenHandler(deleteKuchenHandler);
        when(ausLoeser.getScanner().nextLine()).thenReturn("d").thenReturn(String.valueOf(1)).thenReturn("exit");
        ausLoeser.start();
        verify(deleteKuchenHandler).handle(any());
    }


    /**
     * no need to be tested
     */
//    //ask if valid
//    @Test
//    void deleteKuchenBadTest() throws Exception {
//        DeleteKuchenHandler deleteKuchenHandler = spy(DeleteKuchenHandler.class);
//        Scanner scanner = mock(Scanner.class);
//        this.ausLoeser.setScanner(scanner);
//        ausLoeser.setDeleteKuchenHandler(deleteKuchenHandler);
//        when(ausLoeser.getScanner().nextLine()).thenReturn("d");
//        assertThrows(Exception.class, ()-> {
//            when(ausLoeser.getScanner().nextInt()).thenReturn(Integer.valueOf("unknown command"));
//        });
//    }
    @Test
    void listCMDTest() throws Exception {
        ListingHandler listingHandler = spy(ListingHandler.class);
        Scanner scanner = mock(Scanner.class);
        this.ausLoeser.setScanner(scanner);
        ausLoeser.setListingHandler(listingHandler);
        when(ausLoeser.getScanner().nextLine()).thenReturn("l").thenReturn("exit");
        ausLoeser.start();
        verify(listingHandler).handle(any());
    }


    @Test
    void unknownCommandTest() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner scanner = mock(Scanner.class);
        this.ausLoeser.setScanner(scanner);
        when(ausLoeser.getScanner().nextLine()).thenReturn("unknownKommand").thenReturn("exit");
        ausLoeser.start();
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("you entered an unkonow command... please try again"));
    }

    @Test
    void exitCommand() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner scanner = mock(Scanner.class);
        this.ausLoeser.setScanner(scanner);
        when(ausLoeser.getScanner().nextLine()).thenReturn("exit");
        ausLoeser.start();
        System.setOut(System.out);
        assertTrue(outContent.toString().contains("thank you"));
    }

    @Test
    void unknownCMDInInsertionModeTest() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner scanner = mock(Scanner.class);
        this.ausLoeser.setScanner(scanner);
        when(ausLoeser.getScanner().nextLine()).thenReturn("a").thenReturn("unknownCommand unknownCommand").thenReturn("exit");
        ausLoeser.start();
        System.setOut(System.out);
        String a = outContent.toString();
        assertTrue(a.contains("you entered an unknown command"));
    }
}
//
//        assertEquals("\r\n"
//                +"*******Verkaufsautomat******\r\n" +
//                "-Enter 'a' to enter the add mode\r\n" +
//                "-Enter 'd' to enter Delete mode\r\n" +
//                "-Enter 'l' to enter Listing mode\r\n" +
//                "-Enter 'exit' to exit\r\n" +
//                "add mode active\r\n" +
//                "-if you want to enter a Hersteller, then enter ONLY its name\r\n" +
//                "-if you want to enter a Kuchen type : \"Add kuchen\" then an existing kuchen will be add\r\n" +
//                "you entered an unknown command\r\n" +
//                "-Enter 'a' to enter the add mode\r\n" +
//                "-Enter 'd' to enter Delete mode\r\n" +
//                "-Enter 'l' to enter Listing mode\r\n" +
//                "-Enter 'exit' to exit\r\n" +
//                "thank you"+"\r\n",outContent.toString());
//    }
//
//}