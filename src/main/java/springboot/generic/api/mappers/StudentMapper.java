package springboot.generic.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import springboot.generic.api.dto.StudentDto;
import springboot.generic.api.model.StudentEntity;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    StudentDto mapStudentToStudentDto(StudentEntity studentEntity);
    StudentEntity mapStudentDtoToStudent(StudentDto studentDto);

    List<StudentDto> mapStudentListToStudentDtoList(List<StudentEntity> studentEntity);

}
