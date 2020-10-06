package com.springlearning.springjpahibernatelearning;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springlearning.springjpahibernatelearning.spring_jdbc.model.Person;
import com.springlearning.springjpahibernatelearning.spring_jdbc.repository.PersonDAOSpringJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class SpringJpaHibernateLearningApplication implements CommandLineRunner {
    final Logger logger = LoggerFactory.getLogger(getClass().getName());
    final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    public PersonDAOSpringJDBC personDAOSpringJDBC;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaHibernateLearningApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Getting list of all customers...");
        final List<Person> allPerson = personDAOSpringJDBC.getAllPerson();
        allPerson.stream().forEach(person -> {
            try {
                logger.info(objectMapper.writeValueAsString(person));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        logger.info("Getting detail of customer by Id");
        logger.info(objectMapper.writeValueAsString(personDAOSpringJDBC.getPersonById("1001")));


        logger.info("Getting detail of customer by location");
        logger.info(objectMapper.writeValueAsString(personDAOSpringJDBC.getPersonByLocation("Kolkata")));


        logger.info("Deleting person record by id");
        logger.info("Number of records deleted is {}", objectMapper.writeValueAsString(personDAOSpringJDBC.deleteById("1001")));

        final Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setName("Ram Rahim");
        person.setLocation("Punjab");
        person.setBirth_date(new Timestamp(System.currentTimeMillis()));
        logger.info("Inserting new Person , no of rows inserted {}", personDAOSpringJDBC.insertPerson(person));

        person.setLocation("Chennai");
        logger.info("Updating new Person , no of rows inserted {}", personDAOSpringJDBC.updatePerson(person));


    }
}
