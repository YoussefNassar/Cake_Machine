package events.events;

import java.util.EventObject;

public class AddHerstellerEvent extends EventObject {
    String herstellerName;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public AddHerstellerEvent(Object source, String herstellerName) {
        super(source);
        this.herstellerName = herstellerName;
    }

    public String getHerstellerName(){
        return this.herstellerName;
    }
}




