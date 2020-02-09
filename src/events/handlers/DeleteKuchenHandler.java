package events.handlers;

import events.events.DeleteKuchenEvent;
import events.listeners.DeleteKuchenListener;

import java.util.LinkedList;
import java.util.List;

public class DeleteKuchenHandler {
    private List<DeleteKuchenListener> listeners = new LinkedList<>();

    public void add(DeleteKuchenListener listener){
        this.listeners.add(listener);
    }

    public void remove(DeleteKuchenListener listener){
        this.listeners.remove(listener);
    }

    public void handle(DeleteKuchenEvent deleteKuchenEvent){
        for(DeleteKuchenListener deleteKuchenListener : this.listeners){
            deleteKuchenListener.onDeleteKuchen(deleteKuchenEvent);
        }
    }
}
