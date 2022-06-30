package Ik.ijse.hybernate.bo.custom.impl;

import Ik.ijse.hybernate.bo.custom.StudentBO;
import Ik.ijse.hybernate.dao.DAOFactory;
import Ik.ijse.hybernate.dao.DAOType;
import Ik.ijse.hybernate.dao.custom.StudentDAO;
import Ik.ijse.hybernate.dto.StudentsDTO;
import Ik.ijse.hybernate.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO StudentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);

    @Override
    public List<StudentsDTO> getAllStudent() throws IOException {
        List<Student> all = StudentDAO.getAll();
        ArrayList<StudentsDTO> allStudents = new ArrayList<>();

        for(Student s: all){
            allStudents.add(new StudentsDTO(
                    s.getStudent_id(),
                    s.getStudentName(),
                    s.getStudentAddress(),
                    s.getContac_no(),
                    s.getDob(),
                    s.getGender()));
        }

        return allStudents;
    }

    @Override
    public boolean saveStudent(StudentsDTO dto) throws IOException {
        return StudentDAO.save(new Student(
                dto.getStudentID(),
                dto.getStudentName(),
                dto.getAddress(),
                dto.getContactNo(),
                dto.getDob(),
                dto.getGender()

        ));
    }

    @Override
    public boolean updateStudent(StudentsDTO dto) throws IOException {
        return StudentDAO.update(new Student(
                dto.getStudentID(),
                dto.getStudentName(),
                dto.getAddress(),
                dto.getContactNo(),
                dto.getDob(),
                dto.getGender()

        ));
    }

    @Override
    public boolean deleteStudent(String id) throws IOException {
        return StudentDAO.delete(id);
    }
}
