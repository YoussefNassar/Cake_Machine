package events.handlers;

import events.events.AddKuchenEvent;
import events.listeners.AddKuchenListener;

import java.util.LinkedList;
import java.util.List;

public class AddKuchenHandler {
    private List<AddKuchenListener>  listenersList = new LinkedList<>();

    public void add(AddKuchenListener listener){
        this.listenersList.add(listener);
    }

    public void remove(AddKuchenListener listener){
        this.listenersList.remove(listener);
    }

    public void handle(AddKuchenEvent addKuchenEvent) throws Exception {
        for(AddKuchenListener addKuchenListener : listenersList){
            addKuchenListener.onAddKuchenListener(addKuchenEvent);
        }
    }
}
