package Ik.ijse.hybernate.controller;

import Ik.ijse.hybernate.bo.custom.UserBO;
import Ik.ijse.hybernate.bo.custom.impl.UserBOImpl;
import Ik.ijse.hybernate.dto.UserLOginDTO;
import Ik.ijse.hybernate.view.tdm.UserTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class LoginDetailFormController {
    public TableView<UserTM> tblLogInDetail;
    public TableColumn colUserID;
    public TableColumn colUserName;
    public TableColumn colPassword;
    public JFXTextField txtUserID;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassward;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public JFXButton btnAddNewUser;

    UserBO userBO=new UserBOImpl();


    public void btnSave(ActionEvent actionEvent) throws IOException {

        String id = txtUserID.getText();
        String name = txtUserName.getText();
        String password = txtPassward.getText();

        if (!id.matches("^(U00)[0-9]{1,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
            txtUserID.requestFocus();
            return;

        }else if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtUserName.requestFocus();
            return;
        } else if (!password.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            txtPassward.requestFocus();
            return;
        }
        if (btnSave.getText().equalsIgnoreCase("Save")) {


            if (userBO.saveUser(new UserLOginDTO(id, name, password)))
            {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                tblLogInDetail.getItems().add(new UserTM(id, name, password));
                loadAllUsers();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

            }


        } else {
            try {
                if (userBO.updateUser(new UserLOginDTO(id, name, password))){
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                    loadAllUsers();
                    clearFields();
                }
            } catch (Exception e) {
                // System.out.println("Exception 2");
                //System.out.println(e);
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            }
        }
    }

    public void btndelete(ActionEvent actionEvent) {

        String id=tblLogInDetail.getSelectionModel().getSelectedItem().getUserID();
        try {
            userBO.deleteUser(id);
            tblLogInDetail.getItems().remove(tblLogInDetail.getSelectionModel().getSelectedItem());
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted..!").show();

            tblLogInDetail.getSelectionModel().clearSelection();
            clearFields();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something Happened.Try again Carefully..!").show();
        }



    }

    public void btnAddNew(ActionEvent actionEvent) {
        txtUserID.setDisable(false);
        txtUserName.setDisable(false);
        txtPassward.setDisable(false);
        txtUserID.clear();
        txtUserName.clear();
        txtPassward.clear();

        txtUserID.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblLogInDetail.getSelectionModel().clearSelection();
    }

    public void initialize() throws IOException {


        tblLogInDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userID"));
        tblLogInDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblLogInDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("password"));

        initUI();

        tblLogInDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnDelete.setDisable(false);

            if(newValue != null){
                txtUserID.setText(newValue.getUserID());
                txtUserName.setText(newValue.getUserName());
                txtPassward.setText(newValue.getPassword());


                txtUserID.setDisable(false);
                txtUserName.setDisable(false);
                txtPassward.setDisable(false);


                btnSave.setDisable(false);
            }
        });

        loadAllUsers();
    }

    private void loadAllUsers(){
        tblLogInDetail.getItems().clear();
        try {
            List<UserLOginDTO> userLoginDTOS = userBO.getAllUser();
            for(UserLOginDTO u : userLoginDTOS) {
                tblLogInDetail.getItems().add(new UserTM(
                        u.getUserID(),
                        u.getUserName(),
                        u.getPassword()
                ));
            }
        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }
    private void clearFields(){
        txtUserID.clear();
        txtUserName.clear();
        txtPassward.clear();

    }

    private void initUI() {
        txtUserID.clear();
        txtUserName.clear();
        txtPassward.clear();
        txtUserID.setDisable(true);
        txtUserName.setDisable(true);
        txtPassward.setDisable(true);
        txtUserID.setEditable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }



}
