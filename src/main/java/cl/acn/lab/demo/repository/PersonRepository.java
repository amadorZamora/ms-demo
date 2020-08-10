package cl.acn.lab.demo.repository;

import cl.acn.lab.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ACN-amador.zamora.nunez
 * Date: 09-08-20
 */
@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT * FROM PERSONA", nativeQuery = true)
    public List<Person> getAll();
}
