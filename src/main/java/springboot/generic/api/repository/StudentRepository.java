package springboot.generic.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.generic.api.model.StudentEntity;


public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
