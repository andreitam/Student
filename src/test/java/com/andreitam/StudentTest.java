package com.andreitam;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    static final Logger logger = LogManager.getLogger(StudentTest.class.getName());

    @Test
    void studentTestConstructorValidations() {

        //validate firstName, test thrown, text and log exception
        Exception e1 = assertThrows(IllegalArgumentException.class, ()-> {
            new Student("","Law", "1972-12-29", "m", "45000");
        });
        assertEquals("First name should not be empty.", e1.getMessage());
        logger.error(e1);
        //validate lastName, test thrown, text and log exception
        Exception e2 = assertThrows(IllegalArgumentException.class, ()-> {
            new Student("Jude","", "1972-12-29", "m", "45000");
        });
        assertEquals("Last name should not be empty.", e2.getMessage());
        logger.error(e2);
        //validate dateOfBirth years>120, test thrown, text and log exception
        Exception e3 = assertThrows(IllegalArgumentException.class, ()-> {
            new Student("Jude","Law", "1872-12-29", "m", "45000");
        });
        assertEquals("Date of birth under 18 years or over 120 years.", e3.getMessage());
        logger.error(e3);
        //validate dateOfBirth years<18, test thrown, text and log exception
        Exception e4 = assertThrows(IllegalArgumentException.class, ()-> {
            new Student("Jude","Law", "2019-12-29", "m", "45000");
        });
        assertEquals("Date of birth under 18 years or over 120 years.", e4.getMessage());
        logger.error(e4);
        //validate dateOfBirth date format, test thrown, text and log exception
        Exception e5 = assertThrows(DateTimeParseException.class, ()-> {
            new Student("Jude","Law", "1972,12,29", "m", "45000");
        });
        assertEquals("Date format not correct. Use ISO format Year-Month-Day.", e5.getMessage());
        logger.error(e5);
        //validate gender, test thrown, text and log exception
        Exception e6 = assertThrows(IllegalArgumentException.class, ()-> {
            new Student("Jude","Law", "1972-12-29", "x", "45000");
        });
        assertEquals("Gender unknown.", e6.getMessage());
        logger.error(e6);
    }

}