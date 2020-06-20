package com.clinic.royan;

import com.clinic.royan.Model.DataBase;
import com.clinic.royan.Model.Patient;
import com.clinic.royan.Model.PatientList;
import com.clinic.royan.Report.Report;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AdministrationPanelController {

    @FXML
    private TextField P_Insurance_ID;
    @FXML
    private TextField P_name;
    @FXML
    private TextField P_lastName;
    @FXML
    private TextField P_Contact;
    @FXML
    private TextField P_Email;
    @FXML
    private ListView listView;
    @FXML
    private List<Patient> patientListView;

    private ObservableList<List<Patient>> patients_ObservableList = FXCollections.observableArrayList();

    public void initialize() {
        DataBase dataBase = new DataBase();

        try {
            patientListView = dataBase.queryPatients();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println(patientListView.get(0).getPatient_Name()); //it prints ali which is patient name
        listView.getItems().addAll(patientListView);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //            listView.itemsProperty().set(dataBase.queryPatients().get(1).getPatient_Name());
        System.out.println("initialize executed in admin");
    }

    public void updateListView(){
        DataBase db = new DataBase();
        try {
            listView.getItems().clear();
            patientListView = db.queryPatients();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listView.getItems().addAll(patientListView);
    }
    public void btnSubmitPatient(ActionEvent actionEvent) {
        DataBase dataBase = new DataBase();
        Patient patient = new Patient(Integer.parseInt(P_Insurance_ID.getText()),P_name.getText(),P_lastName.getText(),P_Contact.getText(),
                P_Email.getText());
        dataBase.insertPatients(patient);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Patient Saved Successfully!");
        alert.show();
        P_Insurance_ID.setText("");
        P_name.setText("");
        P_lastName.setText("");
        P_Contact.setText("");
        P_Email.setText("");
        updateListView();
    }

    public void btnGetReport(ActionEvent actionEvent) {
        new Report().showReport();
    }
}
