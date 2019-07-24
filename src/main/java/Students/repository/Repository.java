package Students.repository;


import Students.model.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface Repository extends PagingAndSortingRepository<Students, Long> {
    Page<Students> findAllByFirstnameContaining(String firstname, Pageable pageable);

}
