package Ik.ijse.hybernate.bo.custom;

import Ik.ijse.hybernate.bo.SuperBO;
import Ik.ijse.hybernate.dto.RoomDTO;

import java.io.IOException;
import java.util.List;

public interface RoomBO extends SuperBO {
    List<RoomDTO> getAllRooms() throws IOException;

    boolean saveRoom(RoomDTO dto) throws IOException;

    boolean updateRoom(RoomDTO dto) throws IOException;

    boolean deleteRoom(String id) throws IOException;



}
