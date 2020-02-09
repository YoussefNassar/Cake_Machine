package communication.server.eventPattern.listeners;

//import communication.events.DeleteKuchenEvent;
import Logic.Verwaltung;
import events.events.DeleteKuchenEvent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeleteKuchenListener {
    Verwaltung verwaltung;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public DeleteKuchenListener(Verwaltung verwaltung, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.verwaltung = verwaltung;
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
    }

    public void onDeleteKuchenListener(DeleteKuchenEvent deleteKuchenEvent) throws Exception {

        int fach = deleteKuchenEvent.getfach();

        try {
            verwaltung.deleteKcuhenInFach(fach);
            objectOutputStream.writeUTF("ok");
            objectOutputStream.flush();
        }catch (Exception e){
            objectOutputStream.writeUTF("f");
            objectOutputStream.flush();
        }
    }
}
