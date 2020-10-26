package com.springlearning.springjpahibernatelearning.spring_jpa.model;

import javax.persistence.*;
import java.sql.Timestamp;

//@Entity annotation is used by hibernate to create a relation for this corresponding object
// if we dont mention name attribute to this  annotation then the table name = class name , if  we want to provide a
//name to the relation/table we have to specify that in name attribute of this annotation.
@Entity
@NamedQuery(name = "find_all_person", query = "SELECT P FROM Person p")
public class Person {

    /*
     * The @Id annotation maps that objects attribute  as a primary column for the relation
     * @GeneratedValue will  make sure , based on the database appropriate mechanism is used to auto generate the id
     * Hibernate creates a sequence in the database and use that sequence to populate the  value  for this @GeneratedValue column
     * */
    @Id
    @GeneratedValue
    public int id;
    public String name;
    public String location;
    //@Column annotaion is used if you want to add special property like different name for the  corresponding column in relation
    // if we dont use @Column annotation and specify name then the column name for table= variable name  of the class
    @Column(name = "birth_date")
    public Timestamp birthDate;


    /*
     * In case of defining an Entity we should define a no-argument constructor for our class.
     * */

    public Person() {

    }

    public Person(String name, String location, Timestamp birthDate) {
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
    }

    public Person(int id, String name, String location, Timestamp birthDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }
}
