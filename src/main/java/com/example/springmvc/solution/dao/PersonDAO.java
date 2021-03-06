package com.example.springmvc.solution.dao;

import com.example.springmvc.solution.model.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin12";

    private static Connection connection;

    //Loading a class with a jdbc driver
    static {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index(){
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("username"));

                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }

    public Person show(int id){

        Person person = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setId(resultSet.getInt("username"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    public  void save(Person person){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Person VALUE(1, ?)");

            preparedStatement.setString(1, person.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(int id, Person updatePerson){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET username=?, WHERE id=?");

            preparedStatement.setString(1, updatePerson.getName());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
