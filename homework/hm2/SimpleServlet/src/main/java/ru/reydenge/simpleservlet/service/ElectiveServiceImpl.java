package ru.reydenge.simpleservlet.service;

import ru.reydenge.simpleservlet.entity.Elective;
import ru.reydenge.simpleservlet.repository.ElectiveRepository;

import java.util.List;

public class ElectiveServiceImpl implements ElectiveService {

    private static ElectiveServiceImpl electiveService;

    private ElectiveServiceImpl() {

    }

    public static ElectiveServiceImpl getElectiveService() {
        if (electiveService == null) {
            electiveService = new ElectiveServiceImpl();
        }
        return electiveService;
    }
    @Override
    public int createElective(Elective elective) {
        return ElectiveRepository.createElective(elective);
    }

    @Override
    public int updateElective(Elective elective) {
        return ElectiveRepository.updateElective(elective);
    }

    @Override
    public int deleteElectiveById(int electiveId) {
        return ElectiveRepository.deleteElectiveById(electiveId);
    }

    @Override
    public Elective getElectiveById(int electiveId) {
        return ElectiveRepository.getElectiveById(electiveId);
    }

    @Override
    public List<Elective> getAllElectives() {
        return ElectiveRepository.getAllElectives();
    }
}
