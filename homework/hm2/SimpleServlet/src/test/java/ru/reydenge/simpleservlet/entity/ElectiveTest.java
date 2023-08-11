package ru.reydenge.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.reydenge.simpleservlet.entity.Elective;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElectiveTest {
    Elective elective;

    @BeforeEach
    public void createElective() {
        elective = new Elective("Japanese Language");
    }

    @Test
    void getElectiveTitle() {
        assertEquals("Japanese Language", elective.getTitle());
    }
}
