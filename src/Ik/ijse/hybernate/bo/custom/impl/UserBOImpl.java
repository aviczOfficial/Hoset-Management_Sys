package Ik.ijse.hybernate.bo.custom.impl;

import Ik.ijse.hybernate.bo.custom.UserBO;
import Ik.ijse.hybernate.dao.DAOFactory;
import Ik.ijse.hybernate.dao.DAOType;
import Ik.ijse.hybernate.dao.custom.UserDAO;
import Ik.ijse.hybernate.dto.UserLOginDTO;
import Ik.ijse.hybernate.entity.UserLogin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);

    @Override
    public List<UserLOginDTO> getAllUser() throws IOException {
        List<UserLogin> all = userDAO.getAll();
        ArrayList<UserLOginDTO> allUser = new ArrayList<>();

        for(UserLogin u: all){
            allUser.add(new UserLOginDTO(
                    u.getUser_id(),
                    u.getUser_name(),
                    u.getPassword()
            ));
        }

        return allUser;
    }

    @Override
    public boolean saveUser(UserLOginDTO dto) throws IOException {
        return userDAO.save(new UserLogin(
                dto.getUserID(),
                dto.getUserName(),
                dto.getPassword()


        ));
    }

    @Override
    public boolean updateUser(UserLOginDTO dto) throws IOException {
        return userDAO.update(new UserLogin(
                dto.getUserID(),
                dto.getUserName(),
                dto.getPassword()


        ));
    }

    @Override
    public boolean deleteUser(String id) throws IOException {
        return userDAO.delete(id);
    }

    @Override
    public UserLogin searchUser(String id) throws IOException, SQLException, ClassNotFoundException {
        return userDAO.search(id);
    }
}
