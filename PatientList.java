package com.clinic.royan.Model;

import java.util.List;

public class PatientList  {
    private Patient patient;
    private List<Patient> patientList;

    public PatientList(Patient patient) {
        this.patient = patient;
        patientList.add(this.patient);
    }

    public PatientList() {

    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Patient> getPatientByList(){
        return this.patientList;
    }

    public void addPatient(Patient patient){
        this.patient = patient;
        patientList.add(patient);
    }
}
