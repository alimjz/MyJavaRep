package com.clinic.royan.Report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class Report {
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
    public Report(){
        try {
            Class.forName(dbXMLconnection.getChildText("Driver"));
            connection = DriverManager.getConnection(dbXMLconnection.getChildText("Address"),
                    dbXMLconnection.getChildText("UserName"),
                    dbXMLconnection.getChildText("Password"));
        } catch (Exception e) {
            System.out.println("Error In Db: " + e.getMessage());
        }
    }

    public void showReport(){
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src\\com\\clinic\\royan\\Report\\PatientsReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            HashMap m = new HashMap();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,m,connection);
            JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\report.pdf") ;
            File myFile = new File("C:\\report.pdf");
            Desktop.getDesktop().open(myFile);
//            JasperViewer jv = new JasperViewer(jasperPrint, false);
//            jv.setVisible(true); //print preview namaieshdadeh shavad
            connection.close();


        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
