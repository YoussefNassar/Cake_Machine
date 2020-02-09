package Model;

import Logic.Verwaltung;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import static Model.Allergen.*;
import static org.junit.jupiter.api.Assertions.*;

class KuchenImpTest {

    KuchenImp kuchen;

    @BeforeEach
    void setUpKuchen(){
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);
        Hersteller hersteller = new HerstellerImp("Lidl");
        kuchen  = new KuchenImp(bigDecimal,hersteller,allergens,10,
                Duration.ofDays(10),"Kremkuchen");
    }

    @Test
    void setPricetest(){
        BigDecimal b = new BigDecimal(10);
        this.kuchen.setPreis(b);
        assertEquals(BigDecimal.valueOf(10),this.kuchen.getPreis());
    }

    @Test
    void setAllergen(){
        ArrayList<Allergen> allergens = new ArrayList<>();
        allergens.add(Sesamsamen);
        this.kuchen.setAllergen(allergens);
        assertEquals(1,kuchen.getAllergene().size());
    }

    @Test
    void setNaehrwert(){
        this.kuchen.setNaehrwert(10);
        assertEquals(10,this.kuchen.getNaehrwert());
    }


    @Test
    void setHaltbarkeitTest(){
     Duration duration = Duration.ofDays(1);
     this.kuchen.setHaltbarkeit(duration);
     assertEquals(duration.toDays(),this.kuchen.getHaltbarkeit().toDays());
    }

    @Test
    void setTypeTest(){
        this.kuchen.setType("apfelKuchen");
        assertEquals("apfelKuchen",this.kuchen.getType());
    }

    @Test
    void getTypeTest(){
        assertEquals("Kremkuchen",kuchen.getType());
    }

    @Test
    void getFachNummerTest() throws Exception {
        Verwaltung verwaltung = new Verwaltung();
        verwaltung.addKuchen(this.kuchen);
        assertEquals(1,kuchen.getFachNummer());
    }

    @Test
    void setFachNummerTest(){
        this.kuchen.setFachNummer(3);
        assertEquals(3,this.kuchen.getFachNummer());
    }

    @Test
    void getInsertDateTest(){
        assertEquals(LocalDate.now(),this.kuchen.getInsertDate());
    }

    @Test
    void setInsetDate(){
        this.kuchen.setInsertDate(LocalDate.now());
        assertEquals(LocalDate.now(),this.kuchen.getInsertDate());
    }

    @Test
    void getHersteller(){
        assertEquals("Lidl",this.kuchen.getHersteller().getName());
    }

    @Test
    void setHerstellerTest(){
        Hersteller hersteller = new HerstellerImp("herstellerTest");
        this.kuchen.setHersteller(hersteller);
        assertEquals("herstellerTest",this.kuchen.getHersteller().getName());
    }

    @Test
    void toStringTest(){
        assertEquals("Lidl",this.kuchen.toString());
    }

}