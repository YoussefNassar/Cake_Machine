package communication.client.eventPattern.listeners;

//import communication.events.AddKuchenEvent;
import communication.TCPChannel.TCPChannel;
import events.events.AddKuchenEvent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AddKuchenListener {
    TCPChannel tcpChannel;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public AddKuchenListener(TCPChannel tcpChannel,ObjectInputStream objectInputStream,ObjectOutputStream objectOutputStream) {
        this.tcpChannel = tcpChannel;
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
    }

    public void onAddKuchenListener(AddKuchenEvent addKuchen) throws Exception {
        objectOutputStream.writeUTF("addKuchen");
        objectOutputStream.flush();
        objectOutputStream.writeObject(addKuchen);
        objectOutputStream.flush();
        String answer = objectInputStream.readUTF();
        if (answer.equals("ok")) {
            System.out.println("success");
        } else if (answer.equals("f")) {
            System.out.println("something went wrong");
        } else {
            System.out.println("should not reach the else statement");
        }
    }
}
