package Ik.ijse.hybernate.bo.custom.impl;

import Ik.ijse.hybernate.bo.custom.RoomBO;
import Ik.ijse.hybernate.dao.DAOFactory;
import Ik.ijse.hybernate.dao.DAOType;
import Ik.ijse.hybernate.dao.custom.RoomDAO;
import Ik.ijse.hybernate.dto.RoomDTO;
import Ik.ijse.hybernate.entity.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO = DAOFactory.getInstance().getDAO(DAOType.ROOM);

    @Override
    public List<RoomDTO> getAllRooms() throws IOException {
        List<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allRooms = new ArrayList<>();

        for(Room r: all){
            allRooms.add(new RoomDTO(
                    r.getRoom_type_id(),
                    r.getType(),
                    r.getKey_money(),
                    r.getQty()
            ));
        }

        return allRooms;
    }

    @Override
    public boolean saveRoom(RoomDTO dto) throws IOException {
        return roomDAO.save(new Room(
                dto.getRoomID(),
                dto.getRoomType(),
                dto.getKeyMoney(),
                dto.getRoomQty()

        ));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws IOException {
        return roomDAO.update(new Room(
                dto.getRoomID(),
                dto.getRoomType(),
                dto.getKeyMoney(),
                dto.getRoomQty()

        ));
    }

    @Override
    public boolean deleteRoom(String id) throws IOException {
        return roomDAO.delete(id);
    }
}
