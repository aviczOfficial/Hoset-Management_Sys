package Ik.ijse.hybernate.controller;

import Ik.ijse.hybernate.bo.BOFactory;
import Ik.ijse.hybernate.bo.BOTypes;
import Ik.ijse.hybernate.bo.custom.RoomBO;
import Ik.ijse.hybernate.bo.custom.impl.PurchaseReserveBOImpl;
import Ik.ijse.hybernate.dto.ReservationDTO;
import Ik.ijse.hybernate.dto.RoomDTO;
import Ik.ijse.hybernate.dto.StudentsDTO;
import Ik.ijse.hybernate.entity.Room;
import Ik.ijse.hybernate.entity.Student;
import Ik.ijse.hybernate.view.tdm.ReservationTM;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationFormController {
    public AnchorPane PaneResevation;
    public TableView <ReservationTM>tblReservation;
    public TableColumn colReservationID;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colStudentQty;
    public TableColumn colKeyMny;
    public TableColumn colStatus;
    public TableColumn colDelete;
    public JFXTextField txtStudentName;
    public JFXTextField txtRoomQty;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXComboBox cmbStudentID;
    public JFXComboBox cmbRoomID;
    public JFXButton btnAddToRemain;
    public JFXTextField txtStatus;
    public JFXTextField txtStudentQty;
    public Label lblRoomId;
    public Label lblRoomType;
    public Label lblRoomQty;
    public Label lblReserveID;
    String rId;
    int Qty;
    ArrayList<RoomDTO> allrooms;


    PurchaseReserveBOImpl purchaseReserveBO = BOFactory.getInstance().getBO(BOTypes.PERCHASE_RESERVE);
    RoomBO roomBO = BOFactory.getInstance().getBO(BOTypes.ROOM);

    public void initialize() {

        ab();

        tblReservation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("reserveID"));
        tblReservation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("roomID"));
        tblReservation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("roomType"));
        tblReservation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("studentQty"));
        tblReservation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblReservation.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<ReservationTM, Button> lastCol = (TableColumn<ReservationTM, Button>) tblReservation.getColumns().get(6);
        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(event -> {
                if(tblReservation.getSelectionModel().getSelectedItem()!=null){
                    try {
                        if(purchaseReserveBO.deleteReservation(rId)){
                            new Alert(Alert.AlertType.CONFIRMATION,"Deleted.....").show();
                            ////ReservationDTO reservationDTO = purchaseReserveBO.searchReservation(lblReserveID.getText());
                            //  Room roomID = reservationDTO.getRoomID();
                            System.out.println("qry room: "+Qty);

                            RoomDTO roomDTO1 = purchaseReserveBO.searchRooms((String) cmbRoomID.getValue());

                            roomDTO1.setRoomQty(Qty);

                            roomBO.updateRoom(roomDTO1);

                            tblReservation.getItems().remove(param.getValue());
                            tblReservation.getSelectionModel().clearSelection();
                            clearFields();

                        }else {

                            new Alert(Alert.AlertType.ERROR,"Try Again.....").show(); ;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR,"Please Select Row....").show(); ;
                }








            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        try {
            loadAllReservation();
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadStudentIds();
        loadRoomIds();

        cmbStudentID.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                StudentsDTO studentDTO = purchaseReserveBO.searchStudent((String) newValue);
                txtStudentName.setText(studentDTO.getStudentName());
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        cmbRoomID.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                RoomDTO roomDTO = purchaseReserveBO.searchRooms((String) newValue);
                lblRoomId.setText(roomDTO.getRoomID());
                lblRoomType.setText(roomDTO.getRoomType());
                lblRoomQty.setText(String.valueOf(roomDTO.getRoomQty()));

                txtRoomType.setText(roomDTO.getRoomType());
                txtRoomQty.setText((String.valueOf(roomDTO.getRoomQty())));
                txtKeyMoney.setText(roomDTO.getKeyMoney());
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));


    }

    private  void ab(){

        rId=generateNewOrderId();
        lblReserveID.setText(rId);

    }
    private void clearFields(){
        cmbStudentID.setValue(null);
        txtStudentName.clear();
        cmbRoomID.setValue(null);
        txtRoomType.clear();
        txtKeyMoney.clear();
        txtRoomQty.clear();
        txtStatus.clear();
        txtStudentQty.clear();
    }


    public void loadStudentIds() {
        try {
            cmbStudentID.getItems().addAll(purchaseReserveBO.getStudentIds());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRoomIds() {
        try {
            cmbRoomID.getItems().addAll(purchaseReserveBO.getRoomIds());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAllReservation() throws Exception {
        ObservableList<ReservationTM> observableList = FXCollections.observableArrayList();
        List<ReservationDTO> list = purchaseReserveBO.getAllReservation();

        for (ReservationDTO r : list) {
            String reserveID = r.getRes_id();
            Room room = r.getRoomID();
            String roomID = room.getRoom_type_id();
            String roomType = room.getType();
            int studentQty = r.getQty();
            double keyMoney = r.getKey_money();
            String status = r.getStatus();


            ReservationTM reservationTM = new ReservationTM(reserveID, roomID, roomType, studentQty, keyMoney, status);
            observableList.add(reservationTM);
            tblReservation.setItems(observableList);
        }
    }

    public String generateNewOrderId() {

        try {
            return purchaseReserveBO.generateNewOrderID();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();

        return "R001";
    }

    public void btnBack(ActionEvent actionEvent) throws IOException {
        setUI("Dashboardform");
    }


    private void setUI(String location) throws IOException {
        Stage stage=(Stage) PaneResevation.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

    public void updateRoomQty(String id) throws SQLException, IOException, ClassNotFoundException {
        RoomDTO roomDTO = purchaseReserveBO.searchRooms(id);
        int newqty=roomDTO.getRoomQty()-Integer.parseInt(txtStudentQty.getText());

        roomDTO.setRoomQty(newqty);
        roomBO.updateRoom(roomDTO);
    }

    public void btnReserve(ActionEvent actionEvent) throws Exception {
        String res_id = lblReserveID.getText();
        LocalDate date = DashboardFormController.date;
        StudentsDTO studentDTO = purchaseReserveBO.searchStudent((String) cmbStudentID.getValue());
        Student student = new Student(studentDTO.getStudentID(), studentDTO.getStudentName(), studentDTO.getAddress(), studentDTO.getContactNo(), studentDTO.getDob(), studentDTO.getGender());
        RoomDTO roomDTO = purchaseReserveBO.searchRooms((String) cmbRoomID.getValue());
        Room room = new Room(roomDTO.getRoomID(), roomDTO.getRoomType(), roomDTO.getKeyMoney(), roomDTO.getRoomQty());
        double key_money = Double.parseDouble(txtKeyMoney.getText());
        String status = txtStatus.getText();
        int qty = Integer.parseInt(txtStudentQty.getText());


        ReservationDTO reservationDTO = new ReservationDTO(res_id, date, student, room, key_money, status, qty);
        if(purchaseReserveBO.purchaseReserveSave(reservationDTO)){
            updateRoomQty((String) cmbRoomID.getValue());
            loadAllReservation();
            ab();
            new Alert(Alert.AlertType.CONFIRMATION,"Saved.......").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again.......").show();
        }


    }

    public void btnUpdate(ActionEvent actionEvent) throws Exception {
        String res_id = lblReserveID.getText();
        LocalDate date = DashboardFormController.date;
        StudentsDTO studentDTO = purchaseReserveBO.searchStudent((String) cmbStudentID.getValue());
        Student student = new Student(studentDTO.getStudentID(), studentDTO.getStudentName(), studentDTO.getAddress(), studentDTO.getContactNo(), studentDTO.getDob(), studentDTO.getGender());
        RoomDTO roomDTO = purchaseReserveBO.searchRooms((String) cmbRoomID.getValue());
        Room room = new Room(roomDTO.getRoomID(), roomDTO.getRoomType(), roomDTO.getKeyMoney(), roomDTO.getRoomQty());
        double key_money = Double.parseDouble(txtKeyMoney.getText());
        String status = txtStatus.getText();
        int qty = Integer.parseInt(txtStudentQty.getText());


        ReservationDTO reservationDTO = new ReservationDTO(res_id, date, student, room, key_money, status, qty);

        if(purchaseReserveBO.UpdateReservation(reservationDTO)){
            loadAllReservation();


            int b=Qty-Integer.parseInt(txtStudentQty.getText());



            RoomDTO roomDTO1=new RoomDTO(room.getRoom_type_id(),room.getType(),room.getKey_money(),b);

            roomBO.updateRoom(roomDTO1);


            new Alert(Alert.AlertType.CONFIRMATION,"Updated.......").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again.......").show();
        }

    }

    public void btnAddToRemain(ActionEvent actionEvent) throws IOException {
        setUI("Dashboardform");

    }
}
