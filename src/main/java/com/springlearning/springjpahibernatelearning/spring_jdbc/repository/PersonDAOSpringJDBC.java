package com.springlearning.springjpahibernatelearning.spring_jdbc.repository;

import com.springlearning.springjpahibernatelearning.spring_jdbc.mapper.PersonRowMapper;
import com.springlearning.springjpahibernatelearning.spring_jdbc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * This class will use spring JDBC in order to communicate with the person table created and pre-populated
 * in h2 database
 *
 * @Repository is a child annotation of @Component and is used to mark those componet that
 * talks to the databases i.e, the DAO classes
 * */
@Repository
public class PersonDAOSpringJDBC {

    /*
     * If you are using spring and want to communicate to DB using jdbc , use spring JDBC template to execute queries
     * JDBC template is automatically injected when we use @Autowired
     * */

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PersonRowMapper<Person> personRowMapper;

    public List<Person> getAllPerson() {
        /*
         * the jdbc template's query function expects a query and a mapper that will map the result into the given POJO/model class
         * when the query is executed by spring jdbc template we get a resultset initially that result set is mapped
         * to the give bean/pojo/model by a mapper, we can write custom mapper of our own or use some pre defined mapper by spring JDBC
         * org.springframework.jdbc.core.BeanPropertyRowMapper is the default row mapper of spring jdbc there are many other row mappers also
         *
         * Spring JDBC has a few advantages over classical JDBC , in case of classical JDBC we have to :
         * 1.create a connection to the DB
         * 2. Create a preparedStatement and execute execute the query
         * 3. Iterate ver the resultSet and map the result into the corresponding output format
         * 4. Also we must maintain a connection pool mechanism and also check the connection gets properly closed in case of exception and
         *    many other things
         * But with spring JDBC all the above things are taken cared by Spring we just have to give the query and mention a mapper that
         * will map the result to the given model/bean class
         *
         *
         *
         * How does spring JdbcTemplate knows to create a connection to H2 database?
         *  Ans: Due to spring boot's auto-configuration property during the application start process , if spring boot's auto
         * configurer finds particular classes in the classpath then and other condition then it automatically configures those
         * features for us
         *
         *   eg:        DispatcherServletAutoConfiguration matched:
                        - @ConditionalOnClass found required class 'org.springframework.web.servlet.DispatcherServlet' (OnClassCondition)
                        - found 'session' scope (OnWebApplicationCondition)

         *  in the same way when spring boot auto configurer finds that we have h2 dependencies jar in our class path
         * it creates a connection to h2 database
         *        JdbcTemplateAutoConfiguration matched:
              - @ConditionalOnClass found required classes 'javax.sql.DataSource', 'org.springframework.jdbc.core.JdbcTemplate' (OnClassCondition)
             - @ConditionalOnSingleCandidate (types: javax.sql.DataSource; SearchStrategy: all) found a primary bean from beans 'dataSource' (OnBeanCondition)

                 JdbcTemplateConfiguration matched:
                    - @ConditionalOnMissingBean (types: org.springframework.jdbc.core.JdbcOperations; SearchStrategy: all) did not find any beans (OnBeanCondition)
         *
         * */
        return jdbcTemplate.query("SELECT * FROM PERSON", personRowMapper);
    }


    public Person getPersonById(String id) {
        /*
         * queryForObject methods used when we are querying for a specific object
         * queryForObject accept query arguments as an array of objects.
         * */
        return  jdbcTemplate.queryForObject("SELECT * FROM PERSON WHERE id=?",
                new Object[]{id}, personRowMapper);
    }


    public List<Person> getPersonByLocation(String location) {
        /*
         * queryForObject methods used when we are querying for a specific object
         * queryForObject accept a parameter which is an arry of object that will hold the value we are passing as where clause.
         * */
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE location=?",
                new Object[]{location}, personRowMapper);
    }


    /*
     * In order to perform DDL queries we use the update/updateBatch methods given by jdbcTemplate
     * */
    public int deleteById(String id) {
        return jdbcTemplate.update("DELETE FROM PERSON WHERE id=?", new Object[]{id});
    }


    public int insertPerson(Person person) {
        String sql = "insert into person (id,name,location,birth_date) values(?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[]{person.getId(), person.getName(), person.getLocation(), person.getBirth_date()});
    }


    public int updatePerson(Person person) {
        String query = "update person set name=?,location=?,birth_date=? where id=?";
        return jdbcTemplate.update(query, new Object[]{person.getName(), person.getLocation(), person.getBirth_date(), person.getId()});
    }

}
