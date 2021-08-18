package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

    public class ExcelDemo {
        public static void main(String[]args) throws Exception
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql:exceldsn","root","virender8@");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from [Sheet1$]");
            System.out.println("S.No.\tNAME\tDesignation");

            while (rs.next())
            {
                System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
            }
            con.close();
        }
    }
