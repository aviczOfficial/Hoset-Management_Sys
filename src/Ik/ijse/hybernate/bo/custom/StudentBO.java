package Ik.ijse.hybernate.bo.custom;

import Ik.ijse.hybernate.bo.SuperBO;
import Ik.ijse.hybernate.dto.StudentsDTO;

import java.io.IOException;
import java.util.List;

public interface StudentBO extends SuperBO {

    List<StudentsDTO> getAllStudent() throws IOException;

    boolean saveStudent(StudentsDTO dto) throws IOException;

    boolean updateStudent(StudentsDTO dto) throws IOException;

    boolean deleteStudent(String id) throws IOException;
}
