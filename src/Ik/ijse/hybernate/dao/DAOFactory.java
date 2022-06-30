package Ik.ijse.hybernate.dao;

import Ik.ijse.hybernate.dao.custom.impl.ResevationDAOImpl;
import Ik.ijse.hybernate.dao.custom.impl.RoomDAOImpl;
import Ik.ijse.hybernate.dao.custom.impl.StudentDAOImpl;
import Ik.ijse.hybernate.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (null == daoFactory) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO>T getDAO(DAOType type){
        switch (type){
            case STUDENT:
                return (T) new StudentDAOImpl();
            case ROOM:
                return (T) new RoomDAOImpl();
            case RESERVATION:
                return (T) new ResevationDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            default:
                return null;
        }
    }
}
