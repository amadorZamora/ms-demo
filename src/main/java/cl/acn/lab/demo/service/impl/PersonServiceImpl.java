package cl.acn.lab.demo.service.impl;

import cl.acn.lab.demo.dto.PersonDTO;
import cl.acn.lab.demo.entity.Person;
import cl.acn.lab.demo.repository.PersonRepository;
import cl.acn.lab.demo.service.PersonService;
import cl.acn.lab.demo.utils.MapperUtils;
import lombok.extern.log4j.Log4j;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ACN-amador.zamora.nunez
 * Date: 09-08-20
 */

@Service("personService")
@Log4j
public class PersonServiceImpl implements PersonService {


    @Autowired
    @Qualifier("personRepository")
    private PersonRepository personRepository;

    @Override
    public List<PersonDTO> getAll() {
        return (List<PersonDTO>) MapperUtils.mapAsList(personRepository.getAll(), new TypeToken<List<PersonDTO>>() {
        }.getType());
    }

    @Override
    public PersonDTO getById(Long id) {
        Optional<Person> data = this.personRepository.findById(id);
        if (data.isPresent()) {
            return (PersonDTO) MapperUtils.map(data, PersonDTO.class);
        } else {
            return null;
        }


    }

    @Override
    public boolean save(List<PersonDTO> daoList) {
        boolean result=Boolean.FALSE;
        try {
            List<Person> entityList = (List<Person>) MapperUtils.mapAsList(daoList, new TypeToken<List<Person>>() {
            }.getType());

            List<Person> saveResult = this.personRepository.saveAll(entityList);
            if (saveResult.size() == entityList.size()) {
                result= Boolean.TRUE;
            } else {
                result= Boolean.FALSE;
            }
        }catch(Exception e){
            log.error("ERROR message:  " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean update(List<PersonDTO> daoList) {
        boolean result=Boolean.FALSE;
        try {
            List<Person> entityList = (List<Person>) MapperUtils.mapAsList(daoList, new TypeToken<List<Person>>() {
            }.getType());

            List<Person> saveResult = this.personRepository.saveAll(entityList);
            if (saveResult.size() == entityList.size()) {
                result= Boolean.TRUE;
            } else {
                result= Boolean.FALSE;
            }
        }catch(Exception e){
            log.error("ERROR message:  " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean delete(Long id) {
        boolean ret = false;
        try {
            this.personRepository.deleteById(id);
            ret = true;
        } catch (Exception e) {
            log.error("ERROR message:  " + e.getMessage());
        }
        return ret;
    }
}


