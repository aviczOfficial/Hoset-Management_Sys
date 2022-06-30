package Ik.ijse.hybernate.dao.custom;

import Ik.ijse.hybernate.dao.CrudDAO;
import Ik.ijse.hybernate.entity.Reservation;
import Ik.ijse.hybernate.view.tdm.RemainKeyMnyTM;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationDAO extends CrudDAO<Reservation,String> {

    public ArrayList<Reservation> searchReservation(String enteredText) throws SQLException, ClassNotFoundException;

    public ObservableList<RemainKeyMnyTM> getRemainKeyMoney() throws SQLException, ClassNotFoundException, IOException;
}
