package Logic;

import beobachterMuster.Subjekt.Subjekt;
import beobachter.Beobachter;
import Model.Allergen;
import Model.Kuchen;
import Model.Hersteller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Verwaltung implements Subjekt , Serializable {

//    ArrayList<Allergen> oldAllergens;
    private ArrayList<Allergen> allergens ;

    //maximum capacity of the automat
    final private int MAX_SIZE = 5;

    //Observer List for the Observer-Pattern
    private List<Beobachter> beobachterList = new ArrayList<>();

    private ArrayList<Kuchen> kuchenList =  new ArrayList<Kuchen>();
    private ArrayList<Hersteller> herstellerList =  new ArrayList<Hersteller>();

    @Override
    public void meldeAn(Beobachter beobachter) {
        this.beobachterList.add(beobachter);
    }

    @Override
    public void meldeAb(Beobachter beobachter) {
        this.beobachterList.remove(beobachter);
    }

    @Override
    public void benachrichtige() {
        for(Beobachter b : this.beobachterList){
            b.aktualisiere();
        }
    }

    public List<Beobachter> getBeobachterList() {
        return beobachterList;
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }


//    public ArrayList<Allergen> getAllergens() {
//        return allergens;
//    }

//    public void setAllergens(ArrayList<Allergen> allergens) {
//        this.allergens = allergens;
//    }


    public ArrayList<Kuchen> getKuchenList() {
        return kuchenList;
    }
//
//    public void setKuchenAutomat(ArrayList<Kuchen> kuchenAutomat) {
//        this.kuchenAutomat = kuchenAutomat;
//    }

    public ArrayList<Hersteller> getHerstellerList() {
        return herstellerList;
    }

    public void setHerstellerList(ArrayList<Hersteller> herstellerList) {
        this.herstellerList = herstellerList;
    }

    /**
     * add a new Hersteller to the List
     * @param hersteller
     */
    public void addHersteller(Hersteller hersteller)throws Exception{
        if(hersteller == null){
            throw new NullPointerException();
        }
        herstellerList.add(hersteller);
    }

    /**
     * deletes exciting Herstller
     * @param name
     */
    public void deleteHersteller(String name){
        for(int i = 0 ; i<herstellerList.size() ;i++ ){
            if(herstellerList.get(i).getName() == name){
                herstellerList.remove(i);
            }
        }
    }


    /**
     * this method adds a new kuchen to the Automat
     * and handles where it is going to be stored in the Autmomat by calling another method
     * and handles giving it an insertion date by calling another method
     * @param kuchen
     * @throws Exception
     */
    public void addKuchen(Kuchen kuchen) throws Exception {
        boolean exist = false;
        for(int i = 0 ; i< herstellerList.size();i++){
            if(kuchen.getHersteller().getName().equals(herstellerList.get(i).getName())){
                System.out.println("hersteller already exists");
                exist = true;
            }
        }
        if(kuchenList.size() >= MAX_SIZE){
            throw new Exception("no more space");
        }
        this.kuchenList.add(kuchen);

        if(!exist){
            this.addHersteller(kuchen.getHersteller());
        }
        this.giveFachNummer(kuchen);
        this.setInsertionDate(kuchen);
//        this.availableAllergen();
        this.benachrichtige();
    }

    /**
     * this method decides where the Kuchen is going to be stored
     * @param kuchen
     * @throws Exception if the automat is full
     */


    /**
     * this method decides where the Kuchen is going to be stored
     * @param kuchen
     * @throws Exception if the automat is full
     */
    public void giveFachNummer(Kuchen kuchen) throws Exception{
        int amount = (this.kuchenList.size());
        switch (amount){
            case 1:
                kuchen.setFachNummer(1);
                break;
            case 2:
                kuchen.setFachNummer(2);
                break;
            case 3:
                kuchen.setFachNummer(3);
                break;
            case 4:
                kuchen.setFachNummer(4);
                break;
            case 5:
                kuchen.setFachNummer(5);
                break;
        }
    }


    /**
     * this methode gives the Kuchen a date in which it was stored
     * @param kuchen
     */
    public void setInsertionDate(Kuchen kuchen){
        kuchen.setInsertDate(LocalDate.now());
    }

    /**
     *
     * @return all the kuchen that are stored in the Automat
     */
    public ArrayList<Kuchen> getAllKuchen(){
        return this.kuchenList;
    }

    /**
     * this method looks for all the Kuchens with the given type and return all
     * of them as an ArrayList
     * @param type is the needed type
     * @return an ArrayList with all the kuchen with the given type
     */
    public ArrayList<Kuchen> getKuchenByType(String type){
        ArrayList<Kuchen> kuchenArray= new ArrayList<Kuchen>();
        for(int counter = 0; counter< this.kuchenList.size(); counter++){
            String currentType = kuchenList.get(counter).getType();
            if(currentType.equals(type)){
                kuchenArray.add(kuchenList.get(counter));
            }
        }
        return kuchenArray;
    }

    /**
     *
     * @param hersteller
     * @return the count of the Kuchen to a specific Hersteller
     */
    public int countNachHersteller(String hersteller){
        int counter = 0;
        for(int i = 0; i< this.kuchenList.size(); i++){
            if(kuchenList.get(i).getHersteller().getName().equals(hersteller)){
                counter++;
            }
        }
        return counter;
    }

//    /**
//     *
//     * @param kuchen that we are looking for
//     * @return where the Kuchen is  or (-1) when it doesn't find it
//     */
//    public int whichFachnummer(Kuchen kuchen){
//        for(int i=0;i<this.kuchenAutomat.size();i++) {
//            if(this.kuchenAutomat.get(i) == kuchen){
//                return this.kuchenAutomat.get(i).getFachNummer();
//            }
//            return -1;
//        }
//        return 0;
//    }

    /**
     *
     * @param kuchen
     * @return the remaining shelf-life of the kuchen in minutes
     */
    public Long remainingTime(Kuchen kuchen){
        return kuchen.getHaltbarkeit().toMinutes();
    }

    /**
     * deletes the given Kuchen
     * @param kuchen
     */
    public void deleteKuchen(Kuchen kuchen){
        for(int i = 0; i<this.kuchenList.size(); i++) {
            if (kuchenList.get(i) == kuchen) {
                kuchenList.remove(i);
            }
        }
    }


    /**
     *
     * @return the All Allergen that are to find in the Automat
     */
    public ArrayList<Allergen> availableAllergen(){
        ArrayList<Allergen> availableAllergenArray = new ArrayList<>();
        ArrayList<Allergen> currentArray = new ArrayList<>();
        for(int i = 0; i<this.kuchenList.size(); i++) {
            currentArray = (ArrayList<Allergen>) kuchenList.get(i).getAllergene();
            for(int j=0;j<currentArray.size();j++){
                if(!(availableAllergenArray.contains(currentArray.get(j)))){
                    availableAllergenArray.add(currentArray.get(j));
                }
            }
        }
        this.allergens = availableAllergenArray;
        return availableAllergenArray;
    }



    public ArrayList<Allergen> unavailableAllergen(){
        Allergen allergen;
        ArrayList<Allergen> unavailableAllergenArray = new ArrayList<Allergen>();
        ArrayList<Allergen> availableAllergenArray = new ArrayList<Allergen>();
        ArrayList<Allergen> currentArray = new ArrayList<Allergen>();
        for(int i = 0; i<this.kuchenList.size(); i++) {
            currentArray = (ArrayList<Allergen>) kuchenList.get(i).getAllergene();
            for(int j=0;j<currentArray.size();j++){
                if(!(availableAllergenArray.contains(currentArray.get(j)))){
                    availableAllergenArray.add(currentArray.get(j));
                }
            }
        }
        //checking if the 4 Allergen are availible
        for(int i=0;i<4;i++){
            switch (i){
                case 0:
                    allergen = Allergen.Gluten;
                    if(!(availableAllergenArray.contains(allergen))){
                        unavailableAllergenArray.add(allergen);
                    }
                    break;
                case 1:
                    allergen = Allergen.Erdnuss;
                    if(!(availableAllergenArray.contains(allergen))){
                        unavailableAllergenArray.add(allergen);
                    }
                    break;
                case 2:
                    allergen = Allergen.Haselnuss;
                    if(!(availableAllergenArray.contains(allergen))){
                        unavailableAllergenArray.add(allergen);
                    }
                    break;
                case 3:
                    allergen = Allergen.Sesamsamen;
                    if(!(availableAllergenArray.contains(allergen))){
                        unavailableAllergenArray.add(allergen);
                    }
                    break;
            }
        }
        return unavailableAllergenArray;
    }



    public void deleteKuchenWithName(String herstellerName) {
        for (int i = 0; i < kuchenList.size(); i++) {
            if (kuchenList.get(i).getHersteller().getName().equals(herstellerName)) {
                kuchenList.remove(i);
            }
        }
    }


    public boolean deleteKcuhenInFach(int fach) throws Exception {
        if(fach<1 || fach >5){
            throw new Exception("out of automat");
        }
        if(this.kuchenList.size() < (fach-1)){
            throw new Exception("out of automat2");
        }
        this.kuchenList.remove(fach-1);
        return true;
    }
}

