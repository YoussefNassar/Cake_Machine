package events.listeners;

import events.events.DeleteKuchenEvent;
import Logic.Verwaltung;

public class DeleteKuchenListener {
    Verwaltung verwaltung;

    public DeleteKuchenListener(Verwaltung verwaltung) {
        this.verwaltung = verwaltung;
    }

    public void onDeleteKuchen(DeleteKuchenEvent deleteKuchenEvent){
        int fach = deleteKuchenEvent.getfach();
        try {
            if(verwaltung.getKuchenList().size() == 0){
                System.out.println("kuchenautomat is empty");
                return;
            }
            verwaltung.deleteKcuhenInFach(fach);
            System.out.println("successful");
        } catch (Exception e) {
            System.out.println("something went wrong");
        }
    }
}
