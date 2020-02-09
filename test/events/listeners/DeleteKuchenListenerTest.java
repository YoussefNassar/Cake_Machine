package events.listeners;

import CLI.Main;
import Logic.Verwaltung;
import Model.*;
import events.events.DeleteKuchenEvent;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

import static Model.Allergen.Erdnuss;
import static Model.Allergen.Gluten;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DeleteKuchenListenerTest {

    Kuchen kuchen;

    void setUpKuchen(){
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);
        Hersteller hersteller = new HerstellerImp("Lidl");
        kuchen  = new KremkuchenImp(bigDecimal,hersteller,allergens,10,
                Duration.ofDays(10),"kremsorte");
    }

    @Test
    void onDeleteKuschen() throws Exception {
        Main mainMock = mock(Main.class);
        Verwaltung verwaltungSpy = spy(Verwaltung.class);
        DeleteKuchenEvent deleteKuchenEvent = new DeleteKuchenEvent(mainMock,1);
        DeleteKuchenListener deleteKuchenListener = new DeleteKuchenListener(verwaltungSpy);
        ArrayList<Kuchen> kuchens = new ArrayList<>();
        kuchens.add(this.kuchen);
        when(verwaltungSpy.getKuchenList()).thenReturn(kuchens);
        deleteKuchenListener.onDeleteKuchen(deleteKuchenEvent);
        verify(verwaltungSpy).deleteKcuhenInFach(1);
    }


    @Test
    void KuchenListEmptyTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Main mainMock = mock(Main.class);
        Verwaltung verwaltungSpy = spy(Verwaltung.class);
        DeleteKuchenEvent deleteKuchenEvent = new DeleteKuchenEvent(mainMock,1);
        DeleteKuchenListener deleteKuchenListener = new DeleteKuchenListener(verwaltungSpy);
        ArrayList<Kuchen> kuchens = new ArrayList<>();
        when(verwaltungSpy.getKuchenList()).thenReturn(kuchens);
        deleteKuchenListener.onDeleteKuchen(deleteKuchenEvent);
        assertTrue(outContent.toString().contains("kuchenautomat is empty"));
    }
}
