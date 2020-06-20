package com.clinic.royan.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlUtility {
    public void createXMLFile() throws IOException {
        Element root = new Element("ServerConfiguration");
        Document document = new Document();
        document.setRootElement(root);
        Element connection = new Element("DatabaseConnection");
        root.addContent(connection);
        Element driver = new Element("Driver");
        driver.setText("com.mysql.jdbc.Driver");
        connection.addContent(driver);
        Element address = new Element("Address");
        address.setText("jdbc:mysql://localhost:3306/royan_clinic");
        connection.addContent(address);
        Element user = new Element("UserName");
        user.setText("root");
        connection.addContent(user);
        Element password = new Element("Password");
        password.setText("12345");
        connection.addContent(password);

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(document, new FileWriter("DatabaseConfiguration.xml"));
    }

    public static void readXML() throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        File xmlFile = new File("DatabaseConfiguration.xml");
        Document document = saxBuilder.build(xmlFile);
        Element root = document.getRootElement();
        root.getChild("Driver");
    }
}
