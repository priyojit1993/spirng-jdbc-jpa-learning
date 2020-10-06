package com.springlearning.springjpahibernatelearning.spring_jdbc.mapper;

import com.springlearning.springjpahibernatelearning.spring_jdbc.model.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonRowMapper<T> implements RowMapper<T> {

    /*
     *
     *
     *
     * */


    @Override
    public T mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getString("id"));
        person.setLocation(resultSet.getString("location"));
        person.setName(resultSet.getString("name"));
        person.setBirth_date(resultSet.getTimestamp("birth_date"));
        return (T) person;
    }
}
