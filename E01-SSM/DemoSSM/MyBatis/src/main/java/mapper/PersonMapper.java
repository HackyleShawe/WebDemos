package mapper;

import pojo.Person;

import java.util.List;

public interface PersonMapper {
    void createPerson(Person person);

    void deletePerson(int id);

    void updatePerson(Person person);

    List<Person> readAll();
    Person getPersonById(int id);
    List<Person> getPersonByAddress(String address);
}
