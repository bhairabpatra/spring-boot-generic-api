package springboot.generic.api.service;

import springboot.generic.api.dto.StudentDto;

public interface StudentServices {
    StudentDto createStudent(StudentDto studentDto);

    StudentDto getStudentById(Long id);
}
