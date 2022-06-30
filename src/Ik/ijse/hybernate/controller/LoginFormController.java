package Ik.ijse.hybernate.controller;

import Ik.ijse.hybernate.bo.custom.UserBO;
import Ik.ijse.hybernate.bo.custom.impl.UserBOImpl;
import Ik.ijse.hybernate.entity.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {


    public AnchorPane paneLogin;
    public TextField txtUsrNm;
    public PasswordField txtPwr;


    UserBO userBO=new UserBOImpl();

    public void btnLogin(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        String name=txtUsrNm.getText();
        String password=txtPwr.getText();

        UserLogin user=userBO.searchUser(name);


        if (user!=null) {
            if (txtPwr.getText().equals(user.getPassword())) {
                setUI("DashBoardForm");
                //new Alert(Alert.AlertType.CONFIRMATION, "").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Incorrect Password..!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Incorrect User ID!").show();

        }

    }

    private void setUI(String location) throws IOException {
        Stage stage=(Stage) paneLogin.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }


}
