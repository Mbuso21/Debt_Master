package za.co.debtmaster.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sqlite.SQLiteException;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBaseHandler {

    private static Connection connection;
    private static String jsonString;

    public DataBaseHandler() throws SQLException {
        String url = "jdbc:sqlite:DebtMaster.db";
        this.connection = connectionToDB(url);
        createDataBase();
    }


    public static void main(String[] args) throws SQLException {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
//        dataBaseHander.disconnect();
//        System.out.println(connection.isClosed());
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
//        System.out.println(personJsonString);

//        dataBaseHandler.deleteAll();
//        getAllData();
        try {
//            dataBaseHandler.addPerson(personJsonString);
//            dataBaseHandler.addPerson(personJsonString2);
        }catch (Error e) {
            e.printStackTrace();
            return;
        }
        System.out.println(dataBaseHandler.getDataByEmail("mbuso456@test.com"));
//        dataBaseHandler.updateUserNameByEmail("mbuso@test.com", "nombuso");
//        System.out.println(dataBaseHandler.getDataByEmail("mbuso456@test.com"));
        System.out.println(dataBaseHandler.checkEmailIsRegistered("mbuso@test.com"));
//        getAllData();

//        deleteRowUsingEmail("mbuso@test.com");
    }

    public Connection connectionToDB(String url) {
        Connection connection = null;
        try {
            // /home/mbuso/Debt_Master/DebtMasterServer/
            connection = DriverManager.getConnection(url);
            System.out.println("connected");
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }

        return connection;
    }

    private void createDataBase() throws SQLException {
        String sql =    "CREATE TABLE \"person\" (" +
                            "\"id\" INTEGER," +
                            "\"name\" TEXT," +
                            "\"email\" TEXT," +
                            "\"budget\" BLOB," +
                            "PRIMARY KEY(\"id\" AUTOINCREMENT)" +
                        ");";

        try {
            PreparedStatement prepState = connection.prepareStatement(sql);
            prepState.executeUpdate();
        } catch (SQLiteException e) {
            System.out.println("person table already exists");
        }
    }

    /**
     * gets all the info in the database
     * @return sqlite object or Nothing
     */
    private String getAllData() {
        Statement statement = null;
        String query = "select * from person";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();
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

    /**
     * Retrieves data from DebtMaster.db row based on email
     * @param email String
     * @return json String of the row elements else throws an Error data not found
     */
    public String getDataByEmail(String email) {
        String jsonResult = "";
        Statement statement = null;
        String query = "select * from person where email=\"" + email + "\"";
        String name;
        Object budgetObject;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            name = resultSet.getString("name");
            budgetObject = resultSet.getString("budget");
        } catch (Exception e) {
            new Error("data not found");
            return "Data not found";
        }
        jsonResult = "{\"name\":\"" + name + "\"," +
                        "\"email\":\"" + email + "\"," +
                        "\"budget\":" + budgetObject + "\"";


        return jsonResult;
    }

    /**
     * Update the data base with a new name.
     * @param email
     * @param name
     */
    public void updateUserNameByEmail(String email, String name) throws SQLException {
        if(checkEmailIsRegistered(email)) {
            throw new Error("Email does not exist");
        }
        String query = "UPDATE person set name = \"" + name + "\" WHERE email = \"" + email + "\";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();

    }

    public boolean checkEmailIsRegistered(String email) {
        System.out.println(email);
        Statement statement = null;
        String query = "SELECT * from person WHERE email = \"" + email +"\";";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.getString("id");
            return true;
        }catch (SQLException e) {
            return false;
        }
    }

    /**
     * This will add person data to the DebtMaster.db
     * @param personJsonString
     * @throws SQLException
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
    public void deleteAll() throws SQLException {

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
    public void deleteRowUsingEmail(String email) throws SQLException {
        String sql = "delete from person where email=\""+ email +"\";";
        System.out.println(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
}
