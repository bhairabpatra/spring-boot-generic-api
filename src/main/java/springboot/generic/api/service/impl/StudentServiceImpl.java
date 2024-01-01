package springboot.generic.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.generic.api.dto.StudentDto;
import springboot.generic.api.mappers.StudentMapper;
import springboot.generic.api.model.StudentEntity;
import springboot.generic.api.repository.StudentRepository;
import springboot.generic.api.service.StudentServices;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentServices {


    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        StudentEntity student = studentMapper.mapStudentDtoToStudent(studentDto);
        StudentEntity newStudent = studentRepository.save(student);
        return studentMapper.mapStudentToStudentDto(newStudent);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Optional<StudentEntity> student  = studentRepository.findById(id);
        if(student.isPresent()){
            return  studentMapper.mapStudentToStudentDto(student.get());
        }else {
            return null;
        }

    }
}
