package springboot.generic.api.service;

import org.springframework.cache.Cache;
import springboot.generic.api.dto.StudentDto;
import springboot.generic.api.model.StudentEntity;

import java.util.List;

public interface StudentServices {
    StudentDto createStudent(StudentDto studentDto);

    StudentDto getStudentById(Long id);

    List<StudentDto> getStudents();

    Cache getCacheByname(String cacheName);

    StudentDto updateStudent(Long id, StudentDto studentDto);

    boolean deleteStudent(Long id);

    boolean deleteMultipleStudent(List<StudentEntity> students);

}
