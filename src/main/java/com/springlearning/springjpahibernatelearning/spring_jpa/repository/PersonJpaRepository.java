package com.springlearning.springjpahibernatelearning.spring_jpa.repository;

import com.springlearning.springjpahibernatelearning.spring_jpa.model.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PersonJpaRepository {
    /*
     * (all the operation we do in a particular session are all stored inside Persistence context)
     * Entity manager is the interface to the persistence context
     *
     * */
    @PersistenceContext
    // manages the entities
            EntityManager entityManager;


    public Person getById(int id) {
        /*
         * jpa-hibernate will create the query for us based on the entity(person in our case ) we passed in the find method
         * in order to see the query created by hibernate we have to set the spring property to true
         * spring.jpa.show-sql=true
         *
         * */
        Person person = entityManager.find(Person.class, id);
        return person;
    }

    public Person insertOrUpdate(Person person) {
        /*
        merge method is used to insert or update hibernate searches for the id of the
         entity and if present updates it else insert it
         persist() ie used to only insert

        */
        return entityManager.merge(person);
    }

    public void deleteById(int id) {
        Person person = getById(id);
        entityManager.remove(person);
    }


    /*
     *  using named query to get to create a jpql to retrieve all persons we write a jpql and give
     * */

    public List<Person> getAllPerson() {
        TypedQuery<Person> person = entityManager.createNamedQuery("find_all_person", Person.class);
        return person.getResultList();
    }


}
