package communication.server.eventPattern.handlers;

//import communication.events.AddKuchenEvent;
import communication.server.eventPattern.listeners.AddKuchenListener;
import events.events.AddKuchenEvent;

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

    public void handle(AddKuchenEvent addKuchen) throws Exception {
        for(AddKuchenListener addKuchenListener : listenersList){
            addKuchenListener.onAddKuchenListener(addKuchen);
        }
    }
}
