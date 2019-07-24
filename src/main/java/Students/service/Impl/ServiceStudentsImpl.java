package Students.service.Impl;

import Students.model.Students;
import Students.repository.Repository;
import Students.service.ServiceStudents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ServiceStudentsImpl implements ServiceStudents {

    @Autowired
    private Repository repository;

//    @Override
//    public Iterable<Students> findAll() {
//        return repository.findAll();
//    }

    @Override
    public Page<Students> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Students findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void save(Students students) {
        repository.save(students);
    }

    @Override
    public void remove(Long id) {
        repository.delete(id);
    }

    @Override
    public Page<Students> findAllByFirstNameContaining(String firstname, Pageable pageable) {
        return repository.findAllByFirstnameContaining(firstname,pageable);
    }
}
