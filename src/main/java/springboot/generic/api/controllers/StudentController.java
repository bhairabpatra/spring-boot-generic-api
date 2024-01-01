package springboot.generic.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.generic.api.dto.StudentDto;
import springboot.generic.api.response.GenericResponse;
import springboot.generic.api.service.StudentServices;

@RestController
@RequestMapping("v1/api/")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentServices studentServices;

    @PostMapping("create")
    public ResponseEntity<GenericResponse<StudentDto>> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto newStudent = studentServices.createStudent(studentDto);
        if (newStudent != null) {
            return ResponseEntity.status(HttpStatus.OK.value())
                    .header("custom-header", "test").
                    body(GenericResponse.success(newStudent, " Student created successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .header("custom-header", "test").
                    body(GenericResponse.error(null, " There is some issue while creating student."));
        }
    }


    @GetMapping("student/{id}")
    public ResponseEntity<GenericResponse<StudentDto>> getStudent(@PathVariable Long id) {
        StudentDto studentDto = studentServices.getStudentById(id);
        if (studentDto != null) {
            return ResponseEntity.status(HttpStatus.OK.value())
                    .header("custom-header", "test").
                    body(GenericResponse.success(studentDto, " Student found" + id));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .header("custom-header", "test").
                    body(GenericResponse.error(null, " Student not found " + id));
        }
    }
}