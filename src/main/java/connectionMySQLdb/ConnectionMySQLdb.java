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
            Class.forName("com.mysql.cj.jdbc.Driver");
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

    public static void GetPhoneSQL() throws SQLException {


//        ResultSet resultSet = statement.executeQuery("SELECT code\n" +
//                "    FROM sms\n" +
//                "    WHERE phone = '+77770551364'\n" +
//                "    ORDER BY sms.id DESC\n" +
//                "    LIMIT 1"
//        );

        ResultSet resultSet = statement.executeQuery("SELECT code FROM sms where phone = '+77770551364' ORDER BY id DESC LIMIT 1");

        resultSet.next();
        smsCode = resultSet.getString(1);
//        System.out.println("test");
//        while(resultSet.next())
//        {
//            System.out.println(resultSet.getString(1)); //or rs.getString("column name");
//        }
//        System.out.println("test" + resultSet.getString(0));

        connection.close();
    }

    public static String getSmsCode(){
        return smsCode;
    }
}
