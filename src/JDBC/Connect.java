package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    static Connection con;

    public static Connection createC() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String password = "virender8@";
            String url = "jdbc:mysql://localhost:2709/student_manage";

            con = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}