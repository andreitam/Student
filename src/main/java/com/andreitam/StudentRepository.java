package com.andreitam;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * StudentRepository class contains methods to manipulate Student objects
 * These methods must validate inputs and throw exceptions if necessary.
 *
 * @author  Andrei Tamasanu
 * @version 1.0
 * @since   2020-10-16
 */
public class StudentRepository {
    private Set<Student> studentRepository = new HashSet<>();

    public void addStudent(Student student) {
        studentRepository.add(student);
    }

    public void deleteStudent(String ID) {
        boolean existsFlag = false;
        Student tempStudent = null;
        for (Student s: studentRepository) {
            if(s.getID().equals(ID)) {
                existsFlag = true;
                tempStudent = s;
            }
        }
        studentRepository.remove(tempStudent);
        if(!existsFlag) {
            throw new IllegalArgumentException("Student not found by ID.Student does not exists or ID is empty.");
        }
    }

    public List<Student> retriveStudentsByAge(String age) {
        List<Student> studentsByAge = new ArrayList<>();
        LocalDate today = LocalDate.now();
       try {
           int intAge=Integer.parseInt(age);
           if (intAge <= 0) {
               throw new IllegalArgumentException("Age is 0 or negative.");
           }
           for (Student s: studentRepository) {
               if (intAge == Period.between(s.getDateOfBirth(),today).getYears()) {
                   studentsByAge.add(s);
               }
           }
       } catch (NumberFormatException e) {
           throw new NumberFormatException("Age is not a number.");
       }
        return studentsByAge;
    }

    public List<Student> listStudents(Comparator<Student> sorter) {
        List<Student> listStudents = new ArrayList<>(studentRepository);
        if (sorter instanceof SortLastName) {
            Collections.sort(listStudents,new SortLastName());
        }
        else if (sorter instanceof SortDateBirth) {
            Collections.sort(listStudents,new SortDateBirth());
        }
        else throw new IllegalArgumentException("Comparator class unknown.");
        return listStudents;
    }

    public Integer getRepositorySize() {
        return studentRepository.size();
    }

}

class SortLastName implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getLastNAme().compareTo(o2.getLastNAme());
    }
}

class SortDateBirth implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
/*        return o1.getDateOfBirth().get(Calendar.YEAR)
                - o2.getDateOfBirth().get(Calendar.YEAR);*/
        return Period.between(o1.getDateOfBirth(),o2.getDateOfBirth()).getYears();
    }
}
