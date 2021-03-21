package net.hackyle.service.impl;

import net.hackyle.mapper.PersonMapper;
import net.hackyle.pojo.Person;
import net.hackyle.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personServiceImpl")
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonMapper personMapper;

    /**
     * 方便Spring注入
     */
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public void addPerson(Person person) {
        this.personMapper.addPerson(person);
    }

    public void deletePerson(int id) {
        this.personMapper.deletePerson(id);
    }

    public void updatePerson(Person person) {
        this.personMapper.updatePerson(person);
    }

    public List<Person> readAllPerson() {
        return this.personMapper.readAllPerson();
    }
}
