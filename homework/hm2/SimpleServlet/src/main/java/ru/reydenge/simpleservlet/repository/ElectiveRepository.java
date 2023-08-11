package ru.reydenge.simpleservlet.repository;

import ru.reydenge.simpleservlet.dbutils.DataBaseUtil;
import ru.reydenge.simpleservlet.entity.Elective;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ElectiveRepository {

    private static final String CREATE = "INSERT INTO electives(elective_title) VALUES (?)";
    private static final String UPDATE = "UPDATE electives SET elective_title=? WHERE elective_id=?";
    private static final String GET_BY_ID = "SELECT * FROM electives WHERE elective_id=?";
    private static final String GET_ALL = "SELECT * FROM electives";
    private static final String DELETE = "DELETE FROM electives WHERE elective_id=?";

    public static int createElective(Elective elective) {
        int status = 0;
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE);) {

            preparedStatement.setString(1, elective.getTitle());

            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Enable to create new elective");
            e.printStackTrace();
        }
        return status;
    }

    public static int updateElective(Elective elective) {
        int status = 0;
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {

            preparedStatement.setString(1, elective.getTitle());
            preparedStatement.setInt(2, elective.getId());

            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Enable to update selected elective");
            e.printStackTrace();
        }
        return status;
    }

    public static Elective getElectiveById(int id) {
        Elective elective = new Elective();
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                elective.setId(resultSet.getInt(1));
                elective.setTitle(resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Enable to get elective by id");
            e.printStackTrace();
        }
        return elective;
    }

    public static List<Elective> getAllElectives() {
        List<Elective> electiveList = new ArrayList<>();
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Elective elective = new Elective();
                elective.setId(resultSet.getInt(1));
                elective.setTitle(resultSet.getString(2));
                electiveList.add(elective);
            }
        } catch (SQLException e) {
            System.out.println("Enable to get all electives");
            e.printStackTrace();
        }
        return electiveList;
    }

    public static int deleteElectiveById(int id) {
        int status = 0;
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {

            preparedStatement.setInt(1, id);

            status = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Enable to delete the elective by id");
            e.printStackTrace();
        }
        return status;
    }
}
