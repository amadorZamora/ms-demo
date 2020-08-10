package cl.acn.lab.demo.service;

import cl.acn.lab.demo.dto.PersonDTO;
import java.util.List;

/**
 * @author ACN-amador.zamora.nunez
 * Date: 09-08-20
 */
public interface PersonService {

    public List<PersonDTO> getAll();

    public PersonDTO getById(Long id);

    public boolean save(List<PersonDTO> input);

    public boolean update(List<PersonDTO> input);

    public boolean delete(Long id);
}
