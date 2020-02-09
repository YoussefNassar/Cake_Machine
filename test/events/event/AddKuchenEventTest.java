package events.event;

import CLI.Main;
import Model.Allergen;
import events.events.AddKuchenEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AddKuchenEventTest {

    Main main;
    BigDecimal price;
    String herstellerName;
    ArrayList<Allergen> allergens;
    int nahrwaert;
    Duration haltbarkeit;
    String type;

    AddKuchenEvent addKuchenEvent;

    @BeforeEach
    void setUp(){
        this.main = mock(Main.class);
        price = BigDecimal.valueOf(10);
        herstellerName = "hersteller";
        allergens = new ArrayList<>();
        allergens.add(Allergen.Erdnuss);
        nahrwaert = 1;
        haltbarkeit = Duration.ofDays(1);
        type = "kremKuchen";

        this.addKuchenEvent = new AddKuchenEvent(main,price,herstellerName,allergens,
                nahrwaert,haltbarkeit,type);
    }


    @Test
    void getType() {
        assertEquals("kremKuchen",addKuchenEvent.getType());
    }

    @Test
    void getPrice() {
        assertEquals(BigDecimal.valueOf(10),addKuchenEvent.getPrice());
    }

    @Test
    void getHerstellerName() {
        assertEquals("hersteller",addKuchenEvent.getHerstellerName());
    }

    @Test
    void getAllergens() {
        assertEquals(1,addKuchenEvent.getAllergens().size());
    }

    @Test
    void getNahrwaert() {
        assertEquals(1,addKuchenEvent.getNahrwaert());
    }

    @Test
    void getHaltbarkeit() {
        assertEquals(Duration.ofDays(1),addKuchenEvent.getHaltbarkeit());
    }
}