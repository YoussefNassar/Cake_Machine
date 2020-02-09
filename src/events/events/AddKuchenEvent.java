package events.events;

import Model.Allergen;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.EventObject;

public class AddKuchenEvent extends EventObject {
    BigDecimal price;
    String herstellerName;
    ArrayList<Allergen> allergens;
    int nahrwaert;
    Duration haltbarkeit;
    String type;



    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public AddKuchenEvent(Object source, BigDecimal price, String herstellerName, ArrayList<Allergen> allergens, int nahrwaert,
                          Duration haltbarkeit, String type) {
        super(source);
        this.price = price;
        this.herstellerName = herstellerName;
        this.allergens = allergens;
        this.nahrwaert =nahrwaert;
        this.haltbarkeit = haltbarkeit;
        this.type = type;
    }

    public String getType() {
        return type;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public String getHerstellerName() {
        return herstellerName;
    }

    public ArrayList<Allergen> getAllergens() {
        return allergens;
    }

    public int getNahrwaert() {
        return nahrwaert;
    }

    public Duration getHaltbarkeit() {
        return haltbarkeit;
    }
}
