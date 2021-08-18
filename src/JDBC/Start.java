package JDBC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start {
    public static void main(String[] args)throws IOException
    {
        System.out.println("Welcome to Student Management App");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            System.out.println("Press 1 to Add Student");
            System.out.println("Press 2 to Delete Student");
            System.out.println("Press 3 to Display Student");
            System.out.println("Press 4 to exit App");
            int c=Integer.parseInt(br.readLine());
            if(c==1)
            {
            System.out.println("Enter User Name:");
            String name= br.readLine();

                System.out.println("Enter User Phone:");
                String Phone= br.readLine();

                System.out.println("Enter User City:");
                String City= br.readLine();

                Student2 st=new Student2(name,Phone,City);
                boolean answer= StudentDao.insertStudent2ToDB(st);
                if(answer){
                    System.out.println("Student is added successfully");
                }
                else{
                    System.out.println("Something went wrong try again");
                }
                System.out.println(st);

            }else if(c==2){
                System.out.println("Enter StudentId to delete: ");
                int userId=Integer.parseInt(br.readLine());
                boolean f= StudentDao.deleteStudent(userId);
                if(f){
                    System.out.println("Deleted");
                }
                else
                {
                    System.out.println("Something went wrong");
                }
            }else if(c==3)
            {
                StudentDao.showAllStudent();

            }else if(c==4)
            {
                break;
            }else{

            }
        }
        System.out.println("Thankyou for using my application");
    }
}
