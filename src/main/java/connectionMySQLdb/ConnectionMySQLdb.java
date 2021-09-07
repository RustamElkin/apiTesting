package connectionMySQLdb;
import core.ConfProperties;
import java.sql.*;



public class ConnectionMySQLdb {

    private static String smsCode = "";

    public static final String USER_NAME = ConfProperties.getProperty("userNameDB");
    public static final String PASSWORD = ConfProperties.getProperty("passwordDB");
    public static final String URL = ConfProperties.getProperty("urlDB");
    public static Statement statement;
    public static Connection connection;

    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
           System.out.println("Connection established");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void SelectSQL() throws SQLException {


        ResultSet resultSet = statement.executeQuery(
                "SELECT sms.code FROM sms" +
                    " WHERE phone = '+7(777)055-13-63'" +
                    " ORDER BY sms.id DESC LIMIT 1;"
        );

        resultSet.next();
        smsCode = resultSet.getString("code");
        System.out.println(smsCode);

        connection.close();
    }

    public static String getSmsCode(){
        return smsCode;
    }
}
