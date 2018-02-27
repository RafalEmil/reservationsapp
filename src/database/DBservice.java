package database;

import java.sql.*;

public class DBservice {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/database";
    static final String USER = "webuser";
    static final String PASS = "pass";

    public void addReservation(String fullName, String phoneNumber, String spotNumber, String dateStart, String dateEnd){

        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            sql = "INSERT INTO database.reservationsinfo" +
                    " (fullName," +
                    "phoneNumber," +
                    "spotNumber," +
                    "dateStart," +
                    "dateEnd) " +
                    "VALUES " +
                    "(\"" + fullName + "\"," +      //imię i nazwisko
                    "\"" + phoneNumber + "\"," +    //nr tel
                    "\"" + spotNumber + "\"," +     //nr miejsca parkingowego
                    "\"" + dateStart + "\"," +      //data początkowa
                    "\"" + dateEnd + "\");";        //data końcowa

            System.out.println(sql); // "sql" log

            statement.executeUpdate(sql);

            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkAvailability(String spotNumber, String dateStart, String dateEnd){

        boolean isAvailable = true;
        Connection connection = null;
        Statement statement = null;
        String sql = "";

        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            sql = "SELECT spotNumber, dateStart, dateEnd FROM database.reservationsinfo WHERE " +
                    "spotNumber = \"" + spotNumber + "\" AND " +
                    "((dateStart <= \"" + dateStart + "\" AND dateEnd >= \"" + dateEnd + "\") OR " +
                    "(dateStart <= \"" + dateEnd + "\" AND dateEnd >= \"" + dateEnd + "\") OR " +
                    "(dateStart <= \"" + dateStart + "\" AND dateEnd >= \"" + dateStart + "\") OR " +
                    "(dateStart >= \"" + dateStart + "\" AND dateEnd <= \"" + dateEnd + "\"));";

            System.out.println(sql); // "sql" log

            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet.next()){
                isAvailable = false;
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAvailable;
    }

    public String getReservationsList(String spotNumber){

        Connection connection = null;
        Statement statement = null;
        String sql = "";
        String textResult = "";
        try{
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            if(spotNumber != "null") {
                sql = "SELECT fullName, phoneNumber, spotNumber, dateStart, dateEnd FROM database.reservationsinfo WHERE " +
                        "spotNumber = \"" + spotNumber + "\";";
            }
            else {
                sql = "SELECT fullName, phoneNumber, spotNumber, dateStart, dateEnd FROM database.reservationsinfo;";
            }

            System.out.println(sql); // "sql" log

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                String phoneNumber = resultSet.getString("phoneNumber");
                String spotNumberFromDB = resultSet.getString("spotNumber");
                String dateStart = resultSet.getString("dateStart");
                String dateEnd = resultSet.getString("dateEnd");
                textResult = ( textResult + "\n" + fullName + "\t" + phoneNumber + "\t" + spotNumberFromDB +
                                "\t" + dateStart + "\t" + dateEnd);
                System.out.println(textResult);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return textResult;
    }
}
