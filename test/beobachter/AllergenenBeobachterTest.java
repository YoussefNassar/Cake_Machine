//package beobachter;
//
//import Model.Allergen;
//import Model.KuchenImp;
//import Model.Hersteller;
//import Model.HerstellerImp;
//import Logic.Verwaltung;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.math.BigDecimal;
//import java.time.Duration;
//import java.util.ArrayList;
//
//import static Model.Allergen.Erdnuss;
//import static Model.Allergen.Gluten;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//
//class AllergenenBeobachterTest {
//    Verwaltung verwaltung =new Verwaltung();
//
//    @Test
//    void aktualisiere() throws Exception {
//        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        AllergenenBeobachter allergenenBeobachter = new AllergenenBeobachter(verwaltung);
//        HerstellerImp herstellerImp = new HerstellerImp("hersteller");
//        BigDecimal bigDecimal = BigDecimal.valueOf(10);
//        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
//        allergens.add(Gluten);
//        allergens.add(Erdnuss);
//        Hersteller hersteller = new HerstellerImp("Lidl");
//        KuchenImp kuchen1  = new KuchenImp(bigDecimal,hersteller,allergens,10,
//                Duration.ofDays(10),"Kremkchen");
//        verwaltung.meldeAn(allergenenBeobachter);
//        for(int i = 0; i<5;i++){
//            verwaltung.addKuchen(kuchen1);
//        }
//        String a = outContent.toString();
//        assertTrue(a.contains("One place left!!!"));
//    }
//}