package ru.reydenge.simpleservlet.service;

import ru.reydenge.simpleservlet.entity.Elective;

import java.util.List;

public interface ElectiveService {
    int createElective(Elective elective);

    int updateElective(Elective elective);

    int deleteElectiveById(int electiveId);

    Elective getElectiveById(int electiveId);

    List<Elective> getAllElectives();
}
