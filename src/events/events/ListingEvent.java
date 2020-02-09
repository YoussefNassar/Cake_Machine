package events.events;

import java.util.EventObject;

public class ListingEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ListingEvent(Object source) {
        super(source);
    }
}
