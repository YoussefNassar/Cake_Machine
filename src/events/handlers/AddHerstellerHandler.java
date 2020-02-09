package events.handlers;

import events.events.AddHerstellerEvent;
import events.listeners.AddHerstellerListener;

import java.util.LinkedList;
import java.util.List;

public class AddHerstellerHandler{
    private List<AddHerstellerListener> listenerList = new LinkedList<>();

    public void add(AddHerstellerListener listener){
        this.listenerList.add(listener);
    }

    public void remove(AddHerstellerListener listener){
        this.listenerList.remove(listener);
    }

    public void handle(AddHerstellerEvent addHerstellerEvent) throws Exception {
        for(AddHerstellerListener addHerstellerListener : listenerList){
            addHerstellerListener.onAddHersteller(addHerstellerEvent);
        }
    }
}