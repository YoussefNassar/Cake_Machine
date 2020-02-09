package verwaltungTests;

import Model.Allergen;
import Model.Kuchen;
import Model.KuchenImp;
import Model.Hersteller;
import Model.HerstellerImp;
import Logic.Verwaltung;
import beobachter.Beobachter;
import beobachter.LetztePlatzBeobachter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

import static Model.Allergen.Erdnuss;
import static Model.Allergen.Gluten;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class VerwaltungTest {
    private Verwaltung verwaltung;


    @BeforeEach
    void setUp(){
        this.verwaltung = new Verwaltung();
    }

    KuchenImp setUpKuchen(){
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);
        Hersteller hersteller = new HerstellerImp("Lidl");
        KuchenImp kuchen1  = new KuchenImp(bigDecimal,hersteller,allergens,10,
                Duration.ofDays(10),"Kremkchen");
        return  kuchen1;
    }

    KuchenImp setUpKuchen2(){
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
        allergens.add(Allergen.Haselnuss);
        allergens.add(Allergen.Sesamsamen);
        Hersteller hersteller = new HerstellerImp("Lidl");
        KuchenImp kuchen1  = new KuchenImp(bigDecimal,hersteller,allergens,10,
                Duration.ofDays(10),"Kremkchen");
        return  kuchen1;
    }


    @Test
    void meldeAn(){
        LetztePlatzBeobachter letztePlatzBeobachter = new LetztePlatzBeobachter(verwaltung);
        verwaltung.meldeAn(letztePlatzBeobachter);
        assertEquals(2,verwaltung.getBeobachterList().size());
    }

    @Test
    void meldeAb(){
        LetztePlatzBeobachter letztePlatzBeobachter = new LetztePlatzBeobachter(verwaltung);
        verwaltung.meldeAb(letztePlatzBeobachter);
        assertEquals(0,verwaltung.getBeobachterList().size());
    }

    @Test
    void benachrichtigeTest(){
        Beobachter beobachter = spy(Beobachter.class);
        verwaltung.meldeAn(beobachter);
        verwaltung.benachrichtige();
        verify(beobachter).aktualisiere();
    }

    @Test
    void getBeobachterListTest(){
        LetztePlatzBeobachter letztePlatzBeobachter = new LetztePlatzBeobachter(verwaltung);
        assertEquals(1,verwaltung.getBeobachterList().size());
    }

    @Test
    void gerMAX_SIZETest(){
        assertEquals(5,verwaltung.getMAX_SIZE());
    }

    @Test
    void getKuchenTestGood(){
        assertNotEquals(null,verwaltung.getKuchenList());
    }

    @Test
    void getKuchenWhenNotEmptyTest() throws Exception {
        Kuchen kuchen = setUpKuchen();
        verwaltung.addKuchen(kuchen);
        assertEquals(1,verwaltung.getKuchenList().size());
    }


    @Test
    void getHerstellerListTest(){
        assertNotEquals(null,verwaltung.getHerstellerList());
    }

    @Test
    void getHerstellerListWhenNotEmptyTest() throws Exception {
        Hersteller hersteller = new HerstellerImp("hersteller");
        verwaltung.addHersteller(hersteller);
        assertEquals(1, verwaltung.getHerstellerList().size());
    }

    @Test
    void setHerstellerListTest(){
        ArrayList<Hersteller> herstellers = new ArrayList<>();
        Hersteller hersteller =  new HerstellerImp("hersteller1");
        Hersteller hersteller1 =  new HerstellerImp("hersteller1");
        herstellers.add(hersteller);
        herstellers.add(hersteller1);
        verwaltung.setHerstellerList(herstellers);
        assertEquals(2,verwaltung.getHerstellerList().size());
    }

    @Test
    void goodAddHerstellerTest() throws Exception {
        Hersteller hersteller = new HerstellerImp("Lidl");
        verwaltung.addHersteller(hersteller);
        assertEquals(1,verwaltung.getHerstellerList().size());
    }

    @Test
    void badAddHerstellerTest() throws Exception {
        Hersteller hersteller = null;
        assertThrows(NullPointerException.class,() -> verwaltung.addHersteller(hersteller));
    }


    @Test
    void goodDeleteHerstellerTest(){
        Hersteller hersteller = new HerstellerImp("Lidl");
        verwaltung.getHerstellerList().add(hersteller);
        verwaltung.deleteHersteller("Lidl");
        assertEquals(0,verwaltung.getHerstellerList().size());
    }


    @Test
    void goodAddKuchenTest() throws Exception {
        KuchenImp kuchen1= this.setUpKuchen();
        verwaltung.addKuchen(kuchen1);
        assertEquals(1,verwaltung.getKuchenList().size());
    }

    @Test
    void edgeAddKuchenTest() throws Exception {
        KuchenImp kuchen1= this.setUpKuchen();
        for (int i =0;i<5;i++){
            verwaltung.addKuchen(kuchen1);
        }
        assertEquals(5,verwaltung.getKuchenList().size());

    }

    @Test
    void badAddKuchenTest() throws Exception {
        Kuchen kuchen1= this.setUpKuchen();
        Exception thrown = assertThrows(Exception.class, ()-> {
            for (int i =0;i<=4;i++){
                verwaltung.addKuchen(kuchen1);
            }
            //no more space for a kuchen
            verwaltung.addKuchen(kuchen1);
        },"Automat is full");
    }


    @Test
    void goodGiveFachNummerTest() throws Exception {
        KuchenImp kuchen1 = setUpKuchen();
        verwaltung.addKuchen(kuchen1);
        assertEquals(1,kuchen1.getFachNummer());
    }

    @Test
    void goodSetInsertionDateTest() {
        KuchenImp kuchen1 = setUpKuchen();
        verwaltung.setInsertionDate(kuchen1);
        assertEquals(LocalDate.now(),kuchen1.getInsertDate());
    }

    @Test
    void goodGetAllKuchenTest() {
        verwaltung.getKuchenList().add(setUpKuchen());
        ArrayList<Kuchen> allKuchen =  verwaltung.getAllKuchen();
        assertEquals(1,allKuchen.size());
    }

    @Test
    void goodGetKuchenByTypeTest() throws Exception {
        KuchenImp kuchen = setUpKuchen();
        verwaltung.addKuchen(kuchen);
        ArrayList<Kuchen> kuchenArray = verwaltung.getKuchenByType("Kremkchen");
        assertEquals(1, kuchenArray.size());
    }

    @Test
    void goodCountHerstellerTest() throws Exception {
        Kuchen kuchen = setUpKuchen();
        verwaltung.addKuchen(kuchen);
        int count = verwaltung.countNachHersteller("Lidl");
        assertEquals(1,count);
    }

//    @Test
//    void goodCountHersteller2() throws Exception {
//        Hersteller hersteller = new HerstellerImp("Lidl");
//        verwaltung.addHersteller(hersteller);
//        int count = verwaltung.countNachHersteller("Lidl");
//        assertEquals(1,count);
//    }



//    @Test
//    void whichFachnummer() throws Exception {
//        KuchenImp kuchen1 = setUpKuchen();
//        verwaltung.addKuchen(kuchen1);
//        int fachNummer = verwaltung.whichFachnummer(kuchen1);
//        assertEquals(1,fachNummer);
//    }

    @Test
    void goodRemainingTimeTest() {
        //in set Kuchen I set the Duration to 10 days
        // there is 14400 minutes in 10 days
        KuchenImp kuchen = setUpKuchen();
        long minutesLeft = verwaltung.remainingTime(kuchen);
        assertEquals(14400,minutesLeft);
    }

    @Test
    void deleteKuchenTest() {
        KuchenImp kuchen1 = setUpKuchen();
        verwaltung.getKuchenList().add(kuchen1);
        verwaltung.deleteKuchen(kuchen1);
        assertEquals(0,verwaltung.getKuchenList().size());
    }

    @Test
    void availableAllergenGoodTest() {
        ArrayList<Allergen> availableAllergen;
        KuchenImp kuchen = setUpKuchen(); // kuchen has two Allergen[ Gluten ,Erdnuss]
        verwaltung.getKuchenList().add(kuchen);
        availableAllergen = verwaltung.availableAllergen();
        assertEquals(2,availableAllergen.size());
    }

    @Test
    void unavailibleAllergenGoodTest() {
        ArrayList<Allergen> unavailableAllergen;
        KuchenImp kuchen = setUpKuchen();
        // kuchen has two Allergen[ Gluten, Erdnuss]
        //that means two are remaining [ Haselnuss, Sesamsamen ]
        verwaltung.getKuchenList().add(kuchen);
        unavailableAllergen = verwaltung.unavailableAllergen();
        assertEquals(2,unavailableAllergen.size());
    }


    @Test
    void unavailibleAllergen2() {
        ArrayList<Allergen> unavailableAllergen;
        KuchenImp kuchen = setUpKuchen2();
        // kuchen has two Allergen  [ Haselnuss, Sesamsamen ]
        //that means two are remaining [ Gluten, Erdnuss]
        verwaltung.getKuchenList().add(kuchen);
        unavailableAllergen = verwaltung.unavailableAllergen();
        assertEquals(2,unavailableAllergen.size());
    }


    @Test
    void goodDeleteKuchenWithHerstellerNameTest(){
        Kuchen kuchen = setUpKuchen();
        verwaltung.getKuchenList().add(kuchen);
        verwaltung.deleteKuchenWithName("Lidl");
        assertEquals(0,verwaltung.getKuchenList().size());
    }


    @Test
    void GoodDeleteKuchenInFachTest() throws Exception {
        this.verwaltung.addKuchen(setUpKuchen());
        this.verwaltung.deleteKcuhenInFach(1);
        assertEquals(0,this.verwaltung.getKuchenList().size());
    }


    @Test
    void badIndexDeleteKuchenInFachTest(){
        assertThrows(Exception.class,()->{
            this.verwaltung.deleteKcuhenInFach(0);
        });
    }

    @Test
    void outOfArrayDeleteKuchenInFachTest() throws Exception {
        assertThrows(Exception.class,()->{
            this.verwaltung.deleteKcuhenInFach(2);
        });
    }
    @Test
    void outOfTheAutomatIndexDeleteKuchenInFachTest(){
        assertThrows(Exception.class,()->{
            this.verwaltung.deleteKcuhenInFach(6);
        });
    }


    /**
     * a couple mockito tests
     */


    @Test
    void goodAddKuchen1() throws Exception{
        //Hersteller hersteller = new HerstellerImp("Lidl");

        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);
        //Hersteller hersteller = new HerstellerImp("Lidl");
        Hersteller herstellerMock = Mockito.mock(Hersteller.class);

        Kuchen kuchen1 = Mockito.mock(Kuchen.class);
        //KuchenImp kuchen1  = new KuchenImp(bigDecimal,herstellerMock,allergens,10,
        //       Duration.ofDays(10),"Kremkchen");

        Mockito.when(kuchen1.getHersteller()).thenReturn(herstellerMock);
        verwaltung.addKuchen(kuchen1);
        assertEquals(1,verwaltung.getKuchenList().size());
    }


    @Test
    void goodAddKuchen2() throws Exception{
        //Hersteller hersteller = new HerstellerImp("Lidl");

        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);
        //Hersteller hersteller = new HerstellerImp("Lidl");
        Hersteller herstellerMock = mock(Hersteller.class);

        Kuchen kuchen1 = mock(Kuchen.class);
        ArrayList<Hersteller> herstellerArrayList = Mockito.spy(new ArrayList<Hersteller>());
//        verwaltung.herstellerList = herstellerArrayList;
        verwaltung.setHerstellerList(herstellerArrayList);

        Mockito.when(kuchen1.getHersteller()).thenReturn(herstellerMock);
        verwaltung.addKuchen(kuchen1);


        Mockito.verify(herstellerArrayList).add(herstellerMock);

    }
}