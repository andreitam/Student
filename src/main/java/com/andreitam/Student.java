package com.andreitam;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Objects;


/**
 * For each Student the following information needs to be collected:
 *- First Name -Last Name - Date of Birth - Gender - ID (CNP)
 * The following validations are expected:
 * - date of birth between 1900 and current year - 18
 * - first name and last name should not be empty
 * - gender should be male or female (or M and F), upper/lower case should both be accepted
 * For all the validations, the corresponding exception(s) should be thrown when
 * a Student is created with data which breaks the validation constraint.
 *
 * @author  Andrei Tamasanu
 * @version 1.0
 * @since   2020-10-16
 */
public class Student {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String ID;
//aici sa arunce exceptie constructorul sa fim obligati sa-l instantiem in try catch
    public Student(String firstName, String lastName, String dateOfBirth, String gender, String ID) {
        //validations firstName
        if ( firstName == null || firstName.equals("")) {
            throw new IllegalArgumentException("First name should not be empty.");
        }
        else this.firstName = firstName;
        //validations lastName
        if ( lastName == null || lastName.equals("")) {
            throw new IllegalArgumentException("Last name should not be empty.");
        }
        else this.lastName = lastName;
        //validation dateOfBirth
        try{
            this.dateOfBirth = LocalDate.parse(dateOfBirth);
            LocalDate today=LocalDate.now();
            if (Period.between(this.dateOfBirth,today).getYears()<18 || Period.between(this.dateOfBirth,today).getYears()>120) {
                throw new IllegalArgumentException("Date of birth under 18 years or over 120 years.");
            }
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Date format not correct. Use ISO format Year-Month-Day.",
                    e.getParsedString(),e.getErrorIndex());
        }
        //validation gender
        if (!gender.trim().toLowerCase().equals("m") && !gender.trim().toLowerCase().equals("f") ) {
            throw new IllegalArgumentException("Gender unknown.");
        }
        else this.gender = gender;
        this.ID = ID;
    }

    public String getFirstName() { return firstName; }

    public String getLastNAme() { return lastName; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public String getGender() { return gender; }

    public String getID() { return ID; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(dateOfBirth, student.dateOfBirth) &&
                Objects.equals(gender, student.gender) &&
                Objects.equals(ID, student.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, gender, ID);
    }

}
