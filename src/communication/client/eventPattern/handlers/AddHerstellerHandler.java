package communication.client.eventPattern.handlers;



import communication.client.eventPattern.listeners.AddHerstellerListener;
import events.events.AddHerstellerEvent;
//import communication.events.AddHerstellerEvent;

import java.util.LinkedList;
import java.util.List;

public class AddHerstellerHandler {
    private List<AddHerstellerListener> listenerList = new LinkedList<>();

    public void add(AddHerstellerListener listener){
        this.listenerList.add(listener);
    }

    public void remove(AddHerstellerListener listener){
        this.listenerList.remove(listener);
    }

    public void handle(AddHerstellerEvent addHersteller) throws Exception {
        for(AddHerstellerListener addHerstellerListener : listenerList){
            addHerstellerListener.onAddHerstellerListener(addHersteller);
        }
    }
}