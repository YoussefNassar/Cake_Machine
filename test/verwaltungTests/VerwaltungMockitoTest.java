//package verwaltungTests;
//
//import Model.Allergen;
//import Model.Kuchen;
//import Model.KuchenImp;
//import Model.Hersteller;
//import Model.HerstellerImp;
//import Logic.Verwaltung;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.math.BigDecimal;
//import java.time.Duration;
//import java.util.ArrayList;
//
//import static Model.Allergen.Erdnuss;
//import static Model.Allergen.Gluten;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//
//class VerwaltungMockitoTest {
//
//    //todo
//    // see the test that you did with lohmann
//    //mockito
//    Verwaltung verwaltung;
//
//    @BeforeEach
//    void setUp() {
//        this.verwaltung = new Verwaltung();
//    }
//
//    KuchenImp setUpKuchen() {
//        BigDecimal bigDecimal = BigDecimal.valueOf(10);
//        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
//        allergens.add(Gluten);
//        allergens.add(Erdnuss);
//        Hersteller hersteller = new HerstellerImp("Lidl");
//        KuchenImp kuchen1 = new KuchenImp(bigDecimal, hersteller, allergens, 10,
//                Duration.ofDays(10), "Kremkchen");
//        return kuchen1;
//    }
////
//
//    /**
//     *this test is not good
//     */
////    @Test
////    void goodDeleteHersteller() throws Exception {
////        Hersteller hersteller = new HerstellerImp("Lidl");
////
////        Verwaltung spyverwaltung = spy(verwaltung);
////
////        spyverwaltung.addHersteller(hersteller);
////        spyverwaltung.deleteHersteller(hersteller.getName());
////
////        verify(spyverwaltung).addHersteller(hersteller);
////
////        assertEquals(verwaltung.herstellerList.size(), 0);
////    }
//
//    @Test
//    void goodAddKuchen() throws Exception {
//        Hersteller hersteller = new HerstellerImp("Lidl");
//
//        KuchenImp kuchen = setUpKuchen();
//
//        Hersteller mockHersteller = mock(Hersteller.class);
//        Kuchen mockKuchen = mock(Kuchen.class);
//        Verwaltung verwaltung = new Verwaltung();
//        verwaltung.addHersteller(mockHersteller);
//        assertEquals(1, verwaltung.getHerstellerList().size());
//
//    }
//
//
//    @Test
//    void goodAddKuchen1() throws Exception{
//        //Hersteller hersteller = new HerstellerImp("Lidl");
//
//        BigDecimal bigDecimal = BigDecimal.valueOf(10);
//        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
//        allergens.add(Gluten);
//        allergens.add(Erdnuss);
//        //Hersteller hersteller = new HerstellerImp("Lidl");
//        Hersteller herstellerMock = Mockito.mock(Hersteller.class);
//
//        Kuchen kuchen1 = Mockito.mock(Kuchen.class);
//        //KuchenImp kuchen1  = new KuchenImp(bigDecimal,herstellerMock,allergens,10,
//        //       Duration.ofDays(10),"Kremkchen");
//
//        Mockito.when(kuchen1.getHersteller()).thenReturn(herstellerMock);
//        verwaltung.addKuchen(kuchen1);
//        assertEquals(1,verwaltung.getKuchenList().size());
//    }
//
//
//    @Test
//    void goodAddKuchen2() throws Exception{
//        //Hersteller hersteller = new HerstellerImp("Lidl");
//
//        BigDecimal bigDecimal = BigDecimal.valueOf(10);
//        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
//        allergens.add(Gluten);
//        allergens.add(Erdnuss);
//        //Hersteller hersteller = new HerstellerImp("Lidl");
//        Hersteller herstellerMock = mock(Hersteller.class);
//
//        Kuchen kuchen1 = mock(Kuchen.class);
//        ArrayList<Hersteller> herstellerArrayList = Mockito.spy(new ArrayList<Hersteller>());
////        verwaltung.herstellerList = herstellerArrayList;
//        verwaltung.setHerstellerList(herstellerArrayList);
//
//        Mockito.when(kuchen1.getHersteller()).thenReturn(herstellerMock);
//        verwaltung.addKuchen(kuchen1);
//
//
//        Mockito.verify(herstellerArrayList).add(herstellerMock);
//
//    }
//}