package Ik.ijse.hybernate.controller;

import Ik.ijse.hybernate.bo.BOFactory;
import Ik.ijse.hybernate.bo.BOTypes;
import Ik.ijse.hybernate.bo.custom.ReservationBO;
import Ik.ijse.hybernate.view.tdm.RemainKeyMoneyTM;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;

public class RemainKeyMoneyFormController {
    public TableView <RemainKeyMoneyTM>tblRemainKeyMoney;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colStatus;

    ReservationBO reservationBO = BOFactory.getInstance().getBO(BOTypes.RESERVATION);

    public void initialize() throws SQLException, IOException, ClassNotFoundException {

        tblRemainKeyMoney.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tblRemainKeyMoney.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        tblRemainKeyMoney.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("status"));

        loadRemainKeyMoneyStudent();
    }
    private void loadRemainKeyMoneyStudent() throws SQLException, IOException, ClassNotFoundException {

        ObservableList<RemainKeyMoneyTM> remainKeyMnyStudent = reservationBO.getRemainKeyMnyStudent();
        tblRemainKeyMoney.setItems(remainKeyMnyStudent);

    }

}
