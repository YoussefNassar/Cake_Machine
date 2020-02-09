package presistieren_Input_output;

import Logic.Verwaltung;

import java.io.*;

public class Serializing {

    private static String FILE_NAME = "Data";

    public void serialize(String filename, Verwaltung verwaltung){
        try(ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream(FILE_NAME)))){
            serialize(oos,verwaltung);
            //oos.writeObject(verwaltung);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serialize(ObjectOutputStream oos,Verwaltung verwaltung) throws IOException {
        oos.writeObject(verwaltung);
    }

    public Verwaltung deserialize(String filename){
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
            return deserialize(ois);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Verwaltung deserialize(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Verwaltung verwaltung;
        return  verwaltung = (Verwaltung) ois.readObject();
    }
}
