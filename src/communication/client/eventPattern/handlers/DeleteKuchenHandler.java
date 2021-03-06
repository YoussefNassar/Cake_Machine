package communication.client.eventPattern.handlers;

import communication.client.eventPattern.listeners.DeleteKuchenListener;
import events.events.DeleteKuchenEvent;
//import communication.events.DeleteKuchenEvent;

import java.util.LinkedList;
import java.util.List;

public class DeleteKuchenHandler {
    private List<DeleteKuchenListener> listenerList = new LinkedList<>();

    public void add(DeleteKuchenListener listener){
        this.listenerList.add(listener);
    }

    public void remove(DeleteKuchenListener listener){
        this.listenerList.remove(listener);
    }

    public void handle(DeleteKuchenEvent deleteKuchenEvent) throws Exception {
        for(DeleteKuchenListener deleteKuchenListener : listenerList){
            deleteKuchenListener.onDeleteKuchenListener(deleteKuchenEvent);
        }
    }
}
