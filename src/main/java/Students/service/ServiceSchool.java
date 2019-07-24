package Students.service;

import Students.model.School;

public interface ServiceSchool {

    Iterable<School> findAll();

    School findById(Long id);

    void save(School school);

    void remove(Long id);
}
