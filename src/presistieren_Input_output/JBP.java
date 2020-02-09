//package presistieren_Input_output;
//
//import Model.KuchenImp;
//import Model.Hersteller;
//import Model.HerstellerImp;
//import Logic.Verwaltung;
//
//import java.beans.DefaultPersistenceDelegate;
//import java.beans.XMLDecoder;
//import java.beans.XMLEncoder;
//import java.io.*;
//import java.util.ArrayList;
//
//public class JBP {
//
//    public static void serialize(ArrayList<Hersteller> herstellerImpArrayList){
//        try(XMLEncoder encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("beanItems.xml")));){
//            encoder.setPersistenceDelegate(HerstellerImp.class,new DefaultPersistenceDelegate(new String[] {"name"}));
////            encoder.setPersistenceDelegate(KuchenImp.class,new DefaultPersistenceDelegate(new String[]{"naehrwert"}));
////            encoder.setPersistenceDelegate(HerstellerImp.class,new DefaultPersistenceDelegate(new String[] {"fachNummer"}));
////            encoder.setPersistenceDelegate(HerstellerImp.class,new DefaultPersistenceDelegate(new String[] {"type"}));
//
//            encoder.writeObject(herstellerImpArrayList);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void serialize(Verwaltung verwaltung){
//        try(XMLEncoder encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream("beanItems.xml")));){
//            encoder.setPersistenceDelegate(HerstellerImp.class,new DefaultPersistenceDelegate(new String[] {"name"}));
//            encoder.setPersistenceDelegate(KuchenImp.class,new DefaultPersistenceDelegate(new String[]{ "hersteller","allergen","naehrwert","type"}));
////            encoder.setPersistenceDelegate(BigDecimal.class, new DefaultPersistenceDelegate(new String[] {}));
////            encoder.setPersistenceDelegate(KuchenImp.class,new DefaultPersistenceDelegate(new String[] {"fachNummer"}));
////            encoder.setPersistenceDelegate(KuchenImp.class,new DefaultPersistenceDelegate(new String[] {"type"}));
//            encoder.writeObject(verwaltung);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static Verwaltung deserialize(String filename){
//        try (XMLDecoder decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("beanItems.xml")));){
//            Verwaltung loadedVerwaltung=(Verwaltung)decoder.readObject();
//            //for (BeanItem i:loadedList) System.out.println(i);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static ArrayList<Hersteller> deserialize(){
//        try (XMLDecoder decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream("beanItems.xml")));){
//            ArrayList<Hersteller> herstellers = (ArrayList<Hersteller>) decoder.readObject();
//
//            return herstellers;
////            Verwaltung loadedVerwaltung=(Verwaltung)decoder.readObject();
//            //for (BeanItem i:loadedList) System.out.println(i);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
