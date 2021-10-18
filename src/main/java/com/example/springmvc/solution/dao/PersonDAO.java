package com.example.springmvc.solution.dao;

import com.example.springmvc.solution.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int PEOPLE_COUNT;

    //### блок инициаллизации
    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Gabe"));
        people.add(new Person(++PEOPLE_COUNT, "Tom"));
        people.add(new Person(++PEOPLE_COUNT, "Sam"));
        people.add(new Person(++PEOPLE_COUNT, "Scott"));
    }
    //###

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public  void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatePerson){
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatePerson.getName());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }

}
