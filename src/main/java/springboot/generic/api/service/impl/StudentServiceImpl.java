package springboot.generic.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import springboot.generic.api.dto.StudentDto;
import springboot.generic.api.mappers.StudentMapper;
import springboot.generic.api.model.StudentEntity;
import springboot.generic.api.repository.StudentRepository;
import springboot.generic.api.service.StudentServices;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@CacheConfig(cacheNames = "student-cache")
public class StudentServiceImpl implements StudentServices {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CacheManager cacheManager;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper, CacheManager cacheManager) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.cacheManager = cacheManager;
    }

    @Override
    @CacheEvict(allEntries = true)
    public StudentDto createStudent(StudentDto studentDto) {
        StudentEntity student = studentMapper.mapStudentDtoToStudent(studentDto);
        StudentEntity newStudent = studentRepository.save(student);
        return studentMapper.mapStudentToStudentDto(newStudent);
    }

    @Override
    @Cacheable(key = "#id")
    public StudentDto getStudentById(Long id) {
        Optional<StudentEntity> student = studentRepository.findById(id);
        return student.map(studentMapper::mapStudentToStudentDto).orElse(null);
    }

    @Override
    public List<StudentDto> getStudents() {
        List<StudentEntity> allStudents = studentRepository.findAll();
        return studentMapper.mapStudentListToStudentDtoList(allStudents);
    }

    @Override
    @CachePut(key = "#id")
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Optional<StudentEntity> student = studentRepository.findById(id);
        if (student.isPresent()) {
            student.get().setName(studentDto.getName());
            student.get().setEmail(studentDto.getEmail());
            student.get().setPhone(studentDto.getPhone());
            student.get().setAge(studentDto.getAge());
            student.get().setBio(studentDto.getBio());
            return studentMapper.mapStudentToStudentDto(studentRepository.save(student.get()));
        } else {
            return null;
        }
    }

    @Override
    @CacheEvict(key = "#id")
    public boolean deleteStudent(Long id) {
        Optional<StudentEntity> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMultipleStudent(List<StudentEntity> students) {
        studentRepository.deleteAll(students);
        return true;
    }

    @Override
    public Cache getCacheByname(String cacheName) {
        return cacheManager.getCache(cacheName);
    }
}
