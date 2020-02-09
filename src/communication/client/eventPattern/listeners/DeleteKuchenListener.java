package communication.client.eventPattern.listeners;


//import communication.client.cientCommunication.TCPChannel;
//import communication.events.DeleteKuchenEvent;
import communication.TCPChannel.TCPChannel;
import events.events.DeleteKuchenEvent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeleteKuchenListener {
    TCPChannel tcpChannel;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public DeleteKuchenListener(TCPChannel tcpChannel, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        this.tcpChannel = tcpChannel;
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
    }

    public void onDeleteKuchenListener(DeleteKuchenEvent deleteKuchenEvent) throws Exception {
        System.out.println("-------------");
        System.out.println("the first kuchen will be deleted!");
        objectOutputStream.writeUTF("deletekuchen");
        objectOutputStream.flush();
        objectOutputStream.writeObject(deleteKuchenEvent);
        objectOutputStream.flush();
        String answer = objectInputStream.readUTF();
        if (answer.equals("ok")) {
            System.out.println("delete successful");
            System.out.println("-------------");
        } else if (answer.equals("f")) {
            System.out.println("something wrong... try again");
        } else {
            System.out.println("not expected to reach here");
        }
    }
}
