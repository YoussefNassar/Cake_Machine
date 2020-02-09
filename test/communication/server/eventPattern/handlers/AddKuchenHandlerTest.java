package communication.server.eventPattern.handlers;

import CLI.Main;
import Logic.Verwaltung;
import Model.Allergen;
import communication.server.eventPattern.listeners.AddKuchenListener;
import events.events.AddKuchenEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddKuchenHandlerTest {
    AddKuchenHandler addKuchenHandler;
    BigDecimal price;
    String herstellerName;
    ArrayList<Allergen> allergens;
    int nahrwaert;
    Duration haltbarkeit;
    String type;

    @BeforeEach
    void setUp(){
        addKuchenHandler = new AddKuchenHandler();
        price = BigDecimal.valueOf(10);
        herstellerName = "hersteller";
        allergens = new ArrayList<>();
        nahrwaert =0;
        haltbarkeit = Duration.ofDays(1);
        type = "kremKuchen";
    }

    @Test
    void addingListenerToListAndUsingItTest() throws Exception {
        Verwaltung verwaltungMock = mock(Verwaltung.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        AddKuchenListener addKuchenListener = new AddKuchenListener(verwaltungMock,oisMock,oosMock);
        AddKuchenListener addKuchenListenerSpy = spy(addKuchenListener);
        addKuchenHandler.add(addKuchenListenerSpy);
        addKuchenHandler.handle(new AddKuchenEvent(Main.class,price,herstellerName,allergens,nahrwaert,haltbarkeit,type));
        verify(addKuchenListenerSpy).onAddKuchenListener(any());
    }

    @Test
    void removingListenerFromListTest() throws Exception {
        Verwaltung verwaltungMock = mock(Verwaltung.class);
        ObjectInputStream oisMock = mock(ObjectInputStream.class);
        ObjectOutputStream oosMock = mock(ObjectOutputStream.class);
        AddKuchenListener addKuchenListener = new AddKuchenListener(verwaltungMock,oisMock,oosMock);
        AddKuchenListener addKuchenListenerSpy = spy(addKuchenListener);
        addKuchenHandler.add(addKuchenListenerSpy);
        addKuchenHandler.remove(addKuchenListenerSpy);
        addKuchenHandler.handle(new AddKuchenEvent(Main.class,price,herstellerName,allergens,nahrwaert,haltbarkeit,type));
        verify(addKuchenListenerSpy,never()).onAddKuchenListener(any());
    }
}