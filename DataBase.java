package com.clinic.royan.Model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    Connection connection;
    PreparedStatement preparedStatement;
    private File xmlFile = new File("DatabaseConfiguration.xml");
    private SAXBuilder saxBuilder = new SAXBuilder();
    private Document document;

    {
        try {
            document = saxBuilder.build(xmlFile);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Element root = document.getRootElement();
    private Element dbXMLconnection = root.getChild("DatabaseConnection");


    public DataBase() {
        try {
//            System.out.println(dbXMLconnection.getChildText("Driver"));
//            System.out.println(dbXMLconnection.getChildText("Address"));
//            System.out.println(dbXMLconnection.getChildText("UserName"));
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName(dbXMLconnection.getChildText("Driver"));
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/royan_clinic","root","12345");
            connection = DriverManager.getConnection(dbXMLconnection.getChildText("Address"),
                    dbXMLconnection.getChildText("UserName"),
                    dbXMLconnection.getChildText("Password"));
            System.out.println("Connection Success!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void insetUsers(Users users) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO `tbl_users`(`Account`, `Name`, `LastName`, `Email`, `Password`) VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setString(1, users.getEmp_Account());
            preparedStatement.setString(2, users.getEmp_Name());
            preparedStatement.setString(3, users.getEmp_Lname());
            preparedStatement.setString(4, users.getEmp_email());
            preparedStatement.setString(5, users.getEmp_password());

            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPatients(Patient patient) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO `tbl_patient`(`Patient_Insurance_ID`, `Patient_Name`," +
                    " `Patient_LastName`, `Patient_Contact`, `Patient_Email`) VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, patient.getPatient_insurance_ID());
            preparedStatement.setString(2, patient.getPatient_Name());
            preparedStatement.setString(3, patient.getPatient_LastName());
            preparedStatement.setString(4, patient.getPatient_Contact());
            preparedStatement.setString(5, patient.getPatient_Email());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getRowCount(ResultSet resultSet) {
        int rowcount = 0;
        try {
            resultSet.last();
            rowcount = resultSet.getRow();
            resultSet.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowcount;
    }

    public List<Patient> queryPatients() throws SQLException {
        String query = "SELECT patient_id,patient_insurance_id,patient_name,patient_lastname,patient_contact,patient_email FROM tbl_patient";
        preparedStatement = connection.prepareStatement(query);
        //preparedStatement.setString(1,users.getEmp_Account());
        ResultSet resultSet = preparedStatement.executeQuery();
        int rowcount = getRowCount(resultSet);
        List<Patient> patientsList = new ArrayList<Patient>(getRowCount(resultSet));

        for (int i = 1; i <= rowcount; i++) {
            Patient patient = new Patient(resultSet.getInt("patient_id"),resultSet.getInt("patient_insurance_id"),
                    resultSet.getString("patient_name"),resultSet.getString("patient_lastname"),
                    resultSet.getString("patient_contact"),resultSet.getString("patient_email"));
//            patient.setPatient_id(resultSet.getInt("patient_id"));
//            patient.setPatient_insurance_ID(Integer.parseInt(resultSet.getString("Patient_Insurance_ID")));
//            patient.setPatient_Name(resultSet.getString("patient_name"));
//            patient.setPatient_LastName(resultSet.getString("patient_lastname"));
//            patient.setPatient_Contact(resultSet.getString("patient_contact"));
//            patient.setPatient_Email(resultSet.getString("patient_email"));
            patientsList.add(patient);
            resultSet.next();
        }

        connection.close();

        return patientsList;

    }

    public String queryUsers(Users users) {
        try {
            String query = "select Account, Password, Name from tbl_users where Account = '" + users.getEmp_Account() + "' and Password = '"
                    + users.getEmp_password() + "'";
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setString(1,users.getEmp_Account());
            ResultSet resultSet = preparedStatement.executeQuery();
            //System.out.println(resultSet.getString("Account"));
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
            connection.close();
            return "-1";


        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(),
                    ButtonType.OK);
            alert.show();

            return e.getMessage();
        }
    }

    public boolean doesUserExist(String userName, String password) {
        String result = queryUsers(new Users(userName, password));
        if (!result.equals("-1")) {
            return true;

        } else {

            return false;
        }
    }
}
