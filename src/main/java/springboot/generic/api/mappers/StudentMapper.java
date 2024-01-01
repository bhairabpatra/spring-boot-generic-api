package springboot.generic.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import springboot.generic.api.dto.StudentDto;
import springboot.generic.api.model.StudentEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    StudentDto mapStudentToStudentDto(StudentEntity studentEntity);
    StudentEntity mapStudentDtoToStudent(StudentDto studentDto);
}
