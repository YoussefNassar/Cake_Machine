package Model;

import java.io.Serializable;

public class HerstellerImp implements Hersteller, Serializable {
    private String name;
    //Kuchen kuchen = null;


    public HerstellerImp(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}