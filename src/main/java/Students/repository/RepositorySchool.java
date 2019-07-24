package Students.repository;

import Students.model.School;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RepositorySchool extends PagingAndSortingRepository<School,Long> {
}
