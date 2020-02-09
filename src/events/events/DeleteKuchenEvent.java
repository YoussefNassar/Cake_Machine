package events.events;

import java.util.EventObject;

public class DeleteKuchenEvent extends EventObject {
    int fach ;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public DeleteKuchenEvent(Object source, int fach) {
        super(source);
        this.fach = fach;
    }

    public int getfach() {
        return fach;
    }
}
