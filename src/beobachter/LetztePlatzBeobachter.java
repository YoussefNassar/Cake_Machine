package beobachter;

import Logic.Verwaltung;

public class LetztePlatzBeobachter implements Beobachter{

    Verwaltung verwaltung;

    public LetztePlatzBeobachter(Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
        this.verwaltung.meldeAn(this);
    }

    /**
     * checks if one place left
     */
    @Override
    public void aktualisiere() {
        if(this.verwaltung.getKuchenList().size() == (verwaltung.getMAX_SIZE() - 1)){
            System.out.println("One place left!!!");
        }
    }
}
