package communication.server.servercommunication;

import Model.Allergen;
import Model.KuchenImp;
import Model.Hersteller;
import Model.HerstellerImp;
import communication.TCPChannel.TCPChannel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

import static Model.Allergen.Erdnuss;
import static Model.Allergen.Gluten;

public class MainServer {
    static Scanner scanner = new Scanner(System.in);
    static TCPChannel tcpChannel;

    public static void main(String[] args) throws Exception {

        tcpChannel = new TCPChannel(8080, true, "server");
        tcpChannel.run();


//        ObjectInputStream objectInputStream = new ObjectInputStream(tcpChannel.getInputStream());
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(tcpChannel.getOutputStream());

        /**
         * setting up a kuchen
         */
        Hersteller hersteller = new HerstellerImp("KuchenHersteller1");
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);

        KuchenImp kuchen1 = new KuchenImp(bigDecimal, hersteller, allergens,
                10, Duration.ofDays(10), "Kremkchen");


        ObjectOutputStream objectOutputStream = new ObjectOutputStream(tcpChannel.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(tcpChannel.getInputStream());
        Initializing initializing = new Initializing(objectInputStream, objectOutputStream);
        initializing.initMethode();
        initializing.serverFunctions();
    }


}
