package Ik.ijse.hybernate.bo.custom;

import Ik.ijse.hybernate.bo.SuperBO;
import Ik.ijse.hybernate.dto.ReservationDTO;
import Ik.ijse.hybernate.view.tdm.RemainKeyMoneyTM;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO extends SuperBO {
    ArrayList<ReservationDTO> getAllReserveDetails() throws SQLException, ClassNotFoundException;

    public ArrayList<ReservationDTO> searchReserveDetails(String enteredText) throws SQLException, ClassNotFoundException;

    ObservableList<RemainKeyMoneyTM> getRemainKeyMnyStudent() throws SQLException, ClassNotFoundException, IOException;

}
