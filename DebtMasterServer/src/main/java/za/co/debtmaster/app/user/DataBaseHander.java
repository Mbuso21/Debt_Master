package za.co.debtmaster.app.user;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBaseHander {

    private static Connection connection;



    public DataBaseHander() {
        String url = "jdbc:sqlite:DebtMaster.db";
        this.connection = connectionToDB(url);
    }

    public static void main(String[] args) throws SQLException {
        DataBaseHander dataBaseHander = new DataBaseHander();
//        dataBaseHander.disconnect();
        System.out.println(connection.isClosed());
        String personJsonString = "{\"name\":\"mbuso\"," +
                "\"email\":\"mbuso456@test.com\"," +
                "\"budget\":{\"income\":\"15000.00\"," +
                "\"expense\":{" +
                "\"rent\":\"3000.00\"," +
                "\"groceries\":\"2000.00\"," +
                "\"transport\":\"2000.00\"" +
                "}" +
                "}" +
                "}";

        String personJsonString2 = "{\"name\":\"nombuso\"," +
                "\"email\":\"mbuso@gmail.com\"," +
                "\"budget\":{\"income\":\"15000.00\"," +
                "\"expense\":{" +
                "\"rent\":\"3000.00\"," +
                "\"groceries\":\"2000.00\"," +
                "\"transport\":\"2000.00\"" +
                "}" +
                "}" +
                "}";
        System.out.println(personJsonString);

//        deleteAll();
//        getAllData();
        try {
//            dataBaseHander.addPerson(personJsonString);
//            dataBaseHander.addPerson(personJsonString2);
        }catch (Error e) {
            e.printStackTrace();
            return;
        }
//        getAllData();

        deleteRowUsingEmail("mbuso@test.com");
    }

    public static Connection getConnection() {
        return connection;
    }

    /**
     * gets all the info in the database
     * @return sqlite object or Nothing
     */
    private static String getAllData() {
        Statement statement = null;
        String query = "select * from person";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();
            System.out.println(columnsNumber);
            while (resultSet.next()) {
                for(int i = 1; i <= columnsNumber; i++) {
                    System.out.println(resultSet.getString(i) + " ");
                }
                System.out.println();
            }
            return resultSet.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Nothing");
        return "Nothing";
    }

    public static String getDataByEmail(String email) {
        String jsonResult = "";
        Statement statement = null;
        String query = "select * from person where email=\"" + email +"\"";
        

        try {

        }catch (Exception e) {

        }

        return jsonResult;
    }


    public static Connection connectionToDB(String url) {
        Connection connection = null;
        try {
            // /home/mbuso/Debt_Master/DebtMasterServer/DebtMaster.db
            connection = DriverManager.getConnection(url);
            System.out.println("connected");
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
        return connection;
    }

    /**
     * This will add person data to the DebtMaster.db
     * @param personJsonString
     */
    private void addPerson(String personJsonString) throws SQLException {
        // We need to get the info from the
        HashMap<String, String> convertedJson = convertStringToJason(personJsonString);
        System.out.println(convertedJson);

        // Duplicate email cannot register
        System.out.println(duplicateEmailCheck(convertedJson.get("email")));
        if(duplicateEmailCheck(convertedJson.get("email"))) {
            System.out.println("email:" + convertedJson.get("email") + " already exists");
            throw new Error("Email already taken");
        }

        // now we add the info to the database
        String sql = "insert into person (name, email, budget) values (?, ?, ?);";
        PreparedStatement prepState  = connection.prepareStatement(sql);
        prepState.setString(1, convertedJson.get("name"));
        prepState.setString(2, convertedJson.get("email"));
        prepState.setObject(3, convertedJson.get("budget"));

        prepState.executeUpdate();

    }

    /**
     * Checks if the email is already in the database
     * @param email
     * @return true if email already exists else false
     */
    private boolean duplicateEmailCheck(String email) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from person");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("email"));
                if(email.equals(resultSet.getString("email"))){
                    return true;
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
            return true;
        }
        return false;
    }


    /**
     * converts jason string to map
     * @param personJsonString
     * @return a map of the json string
     */
    private HashMap<String, String> convertStringToJason(String personJsonString) {
        HashMap<String, String> personMap = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            personMap = (HashMap<String, String>) objectMapper.readValue(personJsonString, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personMap;
    }

    private void disconnect() throws SQLException {
        connection.close();
        System.out.println(connection.isClosed());
    }

    /**
     * Deletes everything in the database
     * @throws SQLException
     */
    public static void deleteAll() throws SQLException {

        String sql = "delete from person;";
        String sql2 = "delete from sqlite_sequence;";


        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
        preparedStatement1.executeUpdate();
    }

    /**
     * Deletes the row that has the email param
     * @param email
     * @throws SQLException
     */
    public static void deleteRowUsingEmail(String email) throws SQLException {
        String sql = "delete from person where email=\""+ email +"\";";
        System.out.println(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
}
