package com.clinic.royan;

import com.clinic.royan.Model.DataBase;
import com.clinic.royan.Model.Users;
import com.clinic.royan.Model.XmlUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.jdom.JDOMException;

import java.io.IOException;


public class Controller {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField pwd_Password;
    @FXML
    private Button btnLogin;
    private DataBase dataBase;

    public void initialize() throws IOException {
        dataBase = new DataBase();

    }

    public void adminPanelDisplay(){
        try {
            Parent adminPanel = FXMLLoader.load(getClass().getResource("AdministrationPanel.fxml"));
            Stage adminStage = new Stage();
            adminStage.setTitle("Admin Panel");

            adminStage.setScene(new Scene(adminPanel, 900, 800));
            adminStage.show();
            hide();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login Successfully!\nWelcome " +
                    txtUserName.getText(), ButtonType.OK);
            alert.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void hide(){
        Stage stage = Main.getPrimaryStage();
        stage.close();

    }
    public void btnLoginAction(ActionEvent actionEvent) {
        Users users = new Users(txtUserName.getText(), pwd_Password.getText());
        Boolean userExist = dataBase.doesUserExist(txtUserName.getText(),pwd_Password.getText());
        if (userExist){
            adminPanelDisplay();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "User Name or Password was wrong!\nOr user does not exists!",
                    ButtonType.OK);
            alert.show();
        }
        txtUserName.setText("");
        pwd_Password.setText("");

    }
}
