package Ik.ijse.hybernate.bo.custom;

import Ik.ijse.hybernate.bo.SuperBO;
import Ik.ijse.hybernate.dto.ReservationDTO;
import Ik.ijse.hybernate.dto.RoomDTO;
import Ik.ijse.hybernate.dto.StudentsDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PurchaseReserveBO extends SuperBO {

    boolean purchaseReserveSave(ReservationDTO dto) throws SQLException, ClassNotFoundException, IOException;

    boolean UpdateReservation(ReservationDTO dto) throws SQLException, ClassNotFoundException, IOException;

    boolean deleteReservation(String  id) throws SQLException, ClassNotFoundException, IOException;

    RoomDTO searchRooms(String id) throws SQLException, ClassNotFoundException, IOException;

    StudentsDTO searchStudent(String id) throws SQLException, ClassNotFoundException, IOException;

    ReservationDTO searchReservation(String id) throws SQLException, ClassNotFoundException, IOException;

    boolean checkRoomIsAvailable(String id) throws SQLException, ClassNotFoundException, IOException;

    boolean checkStudentIsAvailable(String id) throws SQLException, ClassNotFoundException, IOException;

    String generateNewOrderID() throws SQLException, ClassNotFoundException, IOException;

    List<StudentsDTO> getAllStudents() throws Exception;

    List<RoomDTO> getAllRooms() throws Exception;

    List<ReservationDTO> getAllReservation() throws Exception;


    List getStudentIds() throws IOException;

    List getRoomIds() throws IOException;

}
