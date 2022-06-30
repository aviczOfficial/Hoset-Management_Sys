package Ik.ijse.hybernate.dao.custom;

import Ik.ijse.hybernate.dao.CrudDAO;
import Ik.ijse.hybernate.entity.Student;

import java.io.IOException;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student,String> {
    public List<String> getStudentIds() throws IOException;
}
