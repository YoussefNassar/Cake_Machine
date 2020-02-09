package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import Model.Allergen;
import Model.KuchenImp;
import Model.Hersteller;
import Model.HerstellerImp;
import Logic.Verwaltung;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;

import static Model.Allergen.Erdnuss;
import static Model.Allergen.Gluten;

/**
 * quelle:
 * https://www.udemy.com/course/java-the-complete-java-developer-course/learn/lecture/12092508#overview
 * https://www.udemy.com/course/java-the-complete-java-developer-course/
 */

public class Controller {
    @FXML private TextField tfSecond;

    @FXML
    private ListView todoListView;
    Verwaltung verwaltung ;

    int count = 3;

    public void initialize() throws Exception {
        verwaltung = new Verwaltung();
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);
        Hersteller hersteller = new HerstellerImp("Lidl1");
        KuchenImp kuchen1  = new KuchenImp(bigDecimal,hersteller,allergens,10,
                Duration.ofDays(10),"Kremkchen");
        BigDecimal bigDecimal1 = BigDecimal.valueOf(10);
        ArrayList<Allergen> allergens1 = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);
        Hersteller hersteller1 = new HerstellerImp("Lidl2");
        KuchenImp kuchen2  = new KuchenImp(bigDecimal,hersteller1,allergens,10,
                Duration.ofDays(10),"Kremkchen");
        tfSecond.setPromptText("enter the name of the kuchen you want to delete");

//        verwaltung.addKuchen(kuchen1);
//        verwaltung.addKuchen(kuchen2);
        verwaltung.getKuchenList().add(kuchen1);
        verwaltung.getKuchenList().add(kuchen2);

        todoListView.getItems().setAll(verwaltung.getKuchenList());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public void onAddButton(ActionEvent actionEvent)  {
//        int i = Integer.parseInt(tfFirst.getText().toString());
        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        ArrayList<Allergen> allergens = new ArrayList<Allergen>();
        allergens.add(Gluten);
        allergens.add(Erdnuss);
        Hersteller hersteller = new HerstellerImp("Lidl"+count);
        KuchenImp kuchen1  = new KuchenImp(bigDecimal,hersteller,allergens,10,
                Duration.ofDays(10),"Kremkchen");
        count++;
        try {
            verwaltung.addKuchen(kuchen1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"automat is full, please stop entering new Kuchen");
            //e.printStackTrace();
        }
        todoListView.getItems().setAll(verwaltung.getKuchenList());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }


    public void onDeleteButton(ActionEvent actionEvent) {
        String kuchenName = tfSecond.getText();
        verwaltung.deleteKuchenWithName(kuchenName);
        todoListView.getItems().setAll(verwaltung.getKuchenList());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
