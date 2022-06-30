package Ik.ijse.hybernate.dao.custom;

import Ik.ijse.hybernate.dao.CrudDAO;
import Ik.ijse.hybernate.entity.Room;

import java.io.IOException;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String> {
    public List getRoomIds() throws IOException;
}
