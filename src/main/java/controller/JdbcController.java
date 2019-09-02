package controller;


import model.Model;
import util.ObjectToByteConverter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcController {

    private static Logger logger = Logger.getLogger(JdbcController.class.getName());

    private static Properties properties = new Properties();
    private static String url = "";
    private static String login = "";
    private static String pass = "";

    static {
        try (InputStream inputStream =
                     JdbcController.class
                             .getClassLoader()
                             .getResourceAsStream("db.properties")) {

            properties.load(inputStream);
            url = properties.getProperty("jdbc.Url");
            login = properties.getProperty("jdbc.Login");
            pass = properties.getProperty("jdbc.Pass");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, pass);
    }

    public static void saveModel(Model model) {
        try (Connection connection = getConnection()) {
            ResultSet tables = connection.getMetaData().getTables(connection.getCatalog(), null, "%", null);
            boolean isExist = false;
            while (tables.next()) {
                String tableName = tables.getString(3);
                if ("models".equals(tableName)) {
                    isExist = true;
                    logger.log(Level.INFO, "Table models already exist");
                }
            }
            if (!isExist) {
                connection.createStatement().execute(
                        "CREATE TABLE models(" +
                                "id integer PRIMARY KEY" +
                                ",name varchar(255)" +
                                ",model bytea )");
                logger.log(Level.INFO, "Create table models");

            }

            byte[] serialize = ObjectToByteConverter.serialize(model);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into models(id,name,model) values (?,?,?)");
            preparedStatement.setInt(1, model.getId());
            preparedStatement.setString(2, model.getName());
            preparedStatement.setBytes(3, serialize);
            preparedStatement.execute();
            logger.log(Level.INFO, "Model " + model.getName() + " save.");
        } catch (
                SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    public static Model getModel(String name) {
        Model model = null;
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from models where name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            byte[] bytes = null;
            while (resultSet.next()) {
                bytes = resultSet.getBytes(3);
            }
            model = (Model) ObjectToByteConverter.deserialize(bytes);
            logger.log(Level.INFO, "Model " + model.getName() + " get.");

        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return model;
    }
}
