package com.springlearning.springjpahibernatelearning.spring_jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springlearning.springjpahibernatelearning.spring_jdbc.repository.PersonDAOSpringJDBC;
import com.springlearning.springjpahibernatelearning.spring_jpa.model.Person;
import com.springlearning.springjpahibernatelearning.spring_jpa.repository.PersonJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringJpaHibernateLearningApplication implements CommandLineRunner {
    final Logger logger = LoggerFactory.getLogger(getClass().getName());


    @Autowired
    PersonJpaRepository personJpaRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaHibernateLearningApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println(new ObjectMapper().writeValueAsString(personJpaRepository.getById(1001)));
        // create a person
        Person newPerson = personJpaRepository.insertOrUpdate(new
                Person("Rishab", "Jharkhand", new Timestamp(System.currentTimeMillis())));
        System.out.println("Id of newly created person is " + newPerson.getId());

        //update a person
        Person updatedPerson = personJpaRepository.insertOrUpdate(new Person(newPerson.getId(),
                "Rishab Bhattacharya", newPerson.getLocation(), newPerson.getBirthDate()));
        System.out.println("The person is updated with new value is" + new ObjectMapper().writeValueAsString(updatedPerson));

        //delete by id
        personJpaRepository.deleteById(1001);

        //get list of person using jpql named query
        System.out.println(new ObjectMapper().writeValueAsString(personJpaRepository.getAllPerson()));




    }
}
