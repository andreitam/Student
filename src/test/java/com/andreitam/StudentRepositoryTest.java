package com.andreitam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {
//create Student instances
    private Student s1 = new Student("Al", "Pacino","1940-04-24","m","10001");
    private Student s2 = new Student("Robert", "DeNiro","1943-10-17","m","10002");
    private Student s3 = new Student("Diane", "Keaton","1946-01-05","f","10003");
    private Student s4 = new Student("Meryl", "Streep","1949-06-22","f","10004");
    private Student s5 = new Student("John", "Travolta","1954-02-18","m","10005");
//create StudentRepository instance
    private StudentRepository repo = new StudentRepository();
//create LogManager
    private static final Logger logger = LogManager.getLogger(StudentTest.class.getName());

    @Test
    void addStudent() {
        repo.addStudent(s1);
        repo.addStudent(s2);
        repo.addStudent(s3);
        repo.addStudent(s4);
        repo.addStudent(s5);//fill repository

        repo.addStudent(s5);//should not be added in Set
        assertEquals(repo.getRepositorySize(),5);
    }

    @Test
    void deleteStudent() {
        repo.addStudent(s1);
        repo.addStudent(s2);
        repo.addStudent(s3);
        repo.addStudent(s4);
        repo.addStudent(s5);//fill repository

        repo.deleteStudent("10001");
        assertEquals(repo.getRepositorySize(),4);
        //test ID empty, exception thrown and exception message and log
        Exception e1 = assertThrows(IllegalArgumentException.class, ()-> { repo.deleteStudent(""); });
        assertEquals("Student not found by ID.Student does not exists or ID is empty.", e1.getMessage());
        logger.error(e1);
        //test unknown ID, exception thrown and exception message and log
        Exception e2 = assertThrows(IllegalArgumentException.class, ()-> { repo.deleteStudent("12345"); });
        assertEquals("Student not found by ID.Student does not exists or ID is empty.", e2.getMessage());
        logger.error(e2);
    }

    @Test
    void retriveStudentsByAge() {
        repo.addStudent(s1);
        repo.addStudent(s2);
        repo.addStudent(s3);
        repo.addStudent(s4);
        repo.addStudent(s5);//fill repository

        // only Al Pacino is in the list
        assertEquals(repo.retriveStudentsByAge("80").size(), 1);
        // test age is negative, exception thrown and exception message and log
        Exception e1 = assertThrows(IllegalArgumentException.class, ()-> { repo.retriveStudentsByAge("-8"); });
        assertEquals("Age is 0 or negative.", e1.getMessage());
        logger.error(e1);
        //test age is not a number, , exception thrown and exception message and log
        Exception e2 = assertThrows(NumberFormatException.class, ()-> { repo.retriveStudentsByAge("-"); });
        assertEquals("Age is not a number.", e2.getMessage());
        logger.error(e2);
    }

    @Test
    void listStudents() {
        repo.addStudent(s1);
        repo.addStudent(s2);
        repo.addStudent(s3);
        repo.addStudent(s4);
        repo.addStudent(s5);//fill repository

        //test sorting by lastName
        List<Student> studentsSorted = repo.listStudents(new SortLastName());
        //before assertion print list, DeNiro pos (0) in the list
        System.out.println("");
        for (Student s: studentsSorted) { System.out.println(s.getLastNAme()); }
        assertEquals(studentsSorted.get(0).getLastNAme(),"DeNiro");

        //test sorting by dateOfBirth
        studentsSorted = repo.listStudents(new SortDateBirth());
        //before assertion print list, Travolta pos (0) in the list
        System.out.println("");
        for (Student s: studentsSorted) { System.out.println(s.getLastNAme()); }
        assertEquals(studentsSorted.get(0).getLastNAme(),"Travolta");
    }
}