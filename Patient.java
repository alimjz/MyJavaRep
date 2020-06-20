package com.clinic.royan.Model;

import javafx.collections.ObservableList;

public class Patient {

    private int patient_id;
    private int patient_insurance_ID;
    private String patient_Name;
    private String patient_LastName;
    private String patient_Contact;
    private String patient_Email;

    private static Patient instance = null;


    public Patient() {
    }

    public Patient(int patient_insurance_ID, String patient_Name, String patient_LastName, String patient_Contact, String patient_Email) {
        this.patient_insurance_ID = patient_insurance_ID;
        this.patient_Name = patient_Name;
        this.patient_LastName = patient_LastName;
        this.patient_Contact = patient_Contact;
        this.patient_Email = patient_Email;

    }

    public Patient(int patient_id, int patient_insurance_ID, String patient_Name, String patient_LastName, String patient_Contact, String patient_Email) {
        this.patient_id = patient_id;
        this.patient_insurance_ID = patient_insurance_ID;
        this.patient_Name = patient_Name;
        this.patient_LastName = patient_LastName;
        this.patient_Contact = patient_Contact;
        this.patient_Email = patient_Email;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public int getPatient_insurance_ID() {
        return patient_insurance_ID;
    }

    @Override
    public String toString() {
        return  patient_id +
                ". " + patient_Name +
                " " + patient_LastName ;
    }

    public String getPatient_Name() {
        return patient_Name;
    }

    public String getPatient_LastName() {
        return patient_LastName;
    }

    public String getPatient_Contact() {
        return patient_Contact;
    }

    public String getPatient_Email() {
        return patient_Email;
    }
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public void setPatient_insurance_ID(int patient_insurance_ID) {
        this.patient_insurance_ID = patient_insurance_ID;
    }

    public void setPatient_Name(String patient_Name) {
        this.patient_Name = patient_Name;
    }

    public void setPatient_LastName(String patient_LastName) {
        this.patient_LastName = patient_LastName;
    }

    public void setPatient_Contact(String patient_Contact) {
        this.patient_Contact = patient_Contact;
    }

    public void setPatient_Email(String patient_Email) {
        this.patient_Email = patient_Email;
    }

    public Patient getInstance(){
        instance = new Patient();
        return instance;
    }

}
