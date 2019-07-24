package Students.service.Impl;

import Students.model.School;

import Students.repository.RepositorySchool;
import Students.service.ServiceSchool;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceSchoolImpl implements ServiceSchool {

    @Autowired
    private RepositorySchool repositorySchool;

    @Override
    public Iterable<School> findAll() {
        return repositorySchool.findAll();
    }

    @Override
    public School findById(Long id) {
        return repositorySchool.findOne(id);
    }

    @Override
    public void save(School school) {
        repositorySchool.save(school);
    }

    @Override
    public void remove(Long id) {
        repositorySchool.delete(id);
    }
}
