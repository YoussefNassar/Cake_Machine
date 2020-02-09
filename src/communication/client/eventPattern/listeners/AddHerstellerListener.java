package communication.client.eventPattern.listeners;


//import communication.client.cientCommunication.TCPChannel;
//import communication.events.AddHerstellerEvent;
import communication.TCPChannel.TCPChannel;
import events.events.AddHerstellerEvent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AddHerstellerListener {
    TCPChannel tcpChannel;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public AddHerstellerListener(TCPChannel tcpChannel,ObjectInputStream objectInputStream,ObjectOutputStream objectOutputStream) {
        this.tcpChannel = tcpChannel;
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
    }

    public void onAddHerstellerListener(AddHerstellerEvent addHersteller) throws Exception {
        objectOutputStream.writeUTF("addhersteller");
        objectOutputStream.flush();
        objectOutputStream.writeObject(addHersteller);
        objectOutputStream.flush();

        String answer = objectInputStream.readUTF();
//        System.out.println(answer);

        if (answer.equals("ok")) {
            System.out.println("success");
        } else if (answer.equals("f")) {
            System.out.println("something went wrong");
        } else {
            System.out.println("should not reach the else statement");
        }
    }

}
