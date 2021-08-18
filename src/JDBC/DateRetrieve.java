package JDBC;

import java.sql.*;
public class DateRetrieve {
    public static void main(String[]args)throws Exception
    {
        String driver="com.mysql.cj.jdbc.Driver";
        String jdbc_url="jdbc:mysql://localhost:2709/agedemo";
        String user="root";
        String pwd="virender8@";
        Class.forName(driver);
        Connection con= DriverManager.getConnection(jdbc_url,user,pwd);
        PreparedStatement ps=con.prepareStatement("select * from user");
        ResultSet rs=ps.executeQuery();
        while (rs.next())
        {
            String name=rs.getString(1);
            java.sql.Date sdate=rs.getDate(2);
            System.out.println(name+"...."+sdate);
        }
        con.close();
    }
}
