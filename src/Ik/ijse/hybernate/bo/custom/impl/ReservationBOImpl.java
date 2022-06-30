package Ik.ijse.hybernate.bo.custom.impl;

import Ik.ijse.hybernate.bo.custom.ReservationBO;
import Ik.ijse.hybernate.dao.DAOFactory;
import Ik.ijse.hybernate.dao.DAOType;
import Ik.ijse.hybernate.dao.custom.ReservationDAO;
import Ik.ijse.hybernate.dto.ReservationDTO;
import Ik.ijse.hybernate.view.tdm.RemainKeyMoneyTM;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO= DAOFactory.getInstance().getDAO(DAOType.RESERVATION);

    @Override
    public ArrayList<ReservationDTO> getAllReserveDetails() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ReservationDTO> searchReserveDetails(String enteredText) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<RemainKeyMoneyTM> getRemainKeyMnyStudent() throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.getRemainKeyMoney();
    }
}
