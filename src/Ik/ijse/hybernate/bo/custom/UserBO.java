package Ik.ijse.hybernate.bo.custom;

import Ik.ijse.hybernate.bo.SuperBO;

import Ik.ijse.hybernate.dto.UserLOginDTO;
import Ik.ijse.hybernate.entity.UserLogin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO  extends SuperBO {
    List<UserLOginDTO> getAllUser() throws IOException;

    boolean saveUser(UserLOginDTO dto) throws IOException;

    boolean updateUser(UserLOginDTO dto) throws IOException;

    boolean deleteUser(String id) throws IOException;

    UserLogin searchUser(String id) throws IOException, SQLException, ClassNotFoundException;
}
