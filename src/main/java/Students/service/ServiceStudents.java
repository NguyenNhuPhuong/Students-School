package Students.service;

import Students.model.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceStudents {

//    Iterable<Students> findAll();

    Page<Students> findAll(Pageable pageable);

    Students findById(Long id);

    void save(Students students);

    void remove(Long id);

    Page<Students> findAllByFirstNameContaining(String firstname,Pageable pageable);
}
