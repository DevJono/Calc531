package com.example.calc531;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().add(null);
        try (ObjectInputStream personFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("people.dat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    PersonalStats person = (PersonalStats) personFile.readObject();
                    personMap.put(person.getName(), person);
                    choiceBox.getItems().add(person.getName());
                } catch (EOFException e) {
                    eof = true;
                } catch (ClassNotFoundException e) {
                    System.out.println("ClassNotFoundException " + e.getMessage());
                }
            }

        } catch (IOException io) {
            System.out.println("IO Exception");
        }

        choiceBox.getSelectionModel().selectFirst();

        choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue ov, String string, String string2) {
                updateLifts();
                setAll();
            }
        });

        exerciseSelector.getItems().addAll(exercises);

        oneRepMaxField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue ov, String string, String string2) {
                setAll();
            }
        });

        exerciseSelector.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue ov, String string, String string2) {
                setAll();
            }
        });

    }

    private final String[] exercises={null,"Deadlift","Squat","Bench"};

    QuickCalculator quick531 = new QuickCalculator();
    public static Map<String, PersonalStats> personMap = new HashMap<>();

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private ChoiceBox<String> exerciseSelector;

    @FXML
    private TextField oneRepMaxField;

    @FXML
    protected TextField nameField;

    @FXML
    protected TextField oneRepMaxDeadField;

    @FXML
    protected TextField oneRepMaxSquatField;

    @FXML
    protected TextField oneRepMaxBenchField;

    @FXML
    private Label s1w1;

    @FXML
    private Label s2w1;

    @FXML
    private Label s3w1;

    @FXML
    private Label s1w2;

    @FXML
    private Label s2w2;

    @FXML
    private Label s3w2;

    @FXML
    private Label s1w3;

    @FXML
    private Label s2w3;

    @FXML
    private Label s3w3;

    @FXML
    private Label s4w1;

    @FXML
    private Label s4w2;

    @FXML
    private Label s4w3;

    @FXML
    private Label deadLabel;

    @FXML
    private Label squatLabel;

    @FXML
    private Label benchLabel;

    @FXML
    protected void setAll(){
        if (isNumeric(oneRepMaxField.getText())&&oneRepMaxField.getText()!=null&&Double.parseDouble(oneRepMaxField.getText())>=0) {
            quick531.oneRepMax = Double.parseDouble(oneRepMaxField.getText());
        }else if (oneRepMaxField.getText().isEmpty()&&exerciseSelector.getValue()==null){
            quick531.oneRepMax=0;
        }else if(deadLabel.getText()==null||squatLabel.getText()==null||benchLabel.getText()==null){
            quick531.oneRepMax=0;
        }
        else if(exerciseSelector.getValue().equals("Deadlift")){
            quick531.oneRepMax = Double.parseDouble(deadLabel.getText());
        }else if(exerciseSelector.getValue().equals("Squat")){
            quick531.oneRepMax = Double.parseDouble(squatLabel.getText());
        }else if(exerciseSelector.getValue().equals("Bench")){
            quick531.oneRepMax = Double.parseDouble(benchLabel.getText());
        }else{
            quick531.oneRepMax=0;
        }
        s1w1.setText(String.format("%.2f", quick531.s1w1()));
        s1w2.setText(String.format("%.2f", quick531.s2w1()));
        s1w3.setText(String.format("%.2f", quick531.s3w1()));
        s2w1.setText(String.format("%.2f", quick531.s1w2()));
        s2w2.setText(String.format("%.2f", quick531.s2w2()));
        s2w3.setText(String.format("%.2f", quick531.s3w2()));
        s3w1.setText(String.format("%.2f", quick531.s1w3()));
        s3w2.setText(String.format("%.2f", quick531.s2w3()));
        s3w3.setText(String.format("%.2f", quick531.s3w3()));
        s4w1.setText(String.format("%.2f", quick531.s1w4()));
        s4w2.setText(String.format("%.2f", quick531.s2w4()));
        s4w3.setText(String.format("%.2f", quick531.s3w4()));
    }

    @FXML
    public void saveButton() throws IOException {
        PersonalStats newPerson = new PersonalStats();
        newPerson.setName(nameField.getText());
        newPerson.setDead1Rm(Double.parseDouble(oneRepMaxDeadField.getText()));
        newPerson.setSquat1Rm(Double.parseDouble(oneRepMaxSquatField.getText()));
        newPerson.setBench1Rm(Double.parseDouble(oneRepMaxBenchField.getText()));
        personMap.put(newPerson.getName(), newPerson);
        choiceBox.getItems().add(newPerson.getName());
        try (ObjectOutputStream personFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("people.dat")))) {
            for (PersonalStats person : personMap.values()) {
                personFile.writeObject(person);
            }
        }
    }

    @FXML
    public void deleteButton() throws IOException {
        personMap.remove(choiceBox.getValue());
        try (ObjectOutputStream personFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("people.dat")))) {
            for (PersonalStats person : personMap.values()) {
                personFile.writeObject(person);
            }
        }
        choiceBox.getItems().remove(choiceBox.getValue());

    }


    public void updateLifts() {
        if (choiceBox.getValue()==null) {
            deadLabel.setText(null);
            squatLabel.setText(null);
            benchLabel.setText(null);
        } else {
            deadLabel.setText(String.format("%.2f", personMap.get(choiceBox.getValue()).getDead1Rm()));
            squatLabel.setText(String.format("%.2f", personMap.get(choiceBox.getValue()).getSquat1Rm()));
            benchLabel.setText(String.format("%.2f", personMap.get(choiceBox.getValue()).getBench1Rm()));
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
