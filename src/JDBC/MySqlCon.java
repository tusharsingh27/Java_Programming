package JDBC;

import java.sql.*;
    class MySqlCon
    {
        public static void main(String args[])
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:2709","root","virender8@");
                //here mysql is database name, root is username and password
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from new_schema.new_table;");
                while(rs.next())
                    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
                con.close();
            }
            catch(Exception e)
            { System.out.println(e);
            }
        }
    }
