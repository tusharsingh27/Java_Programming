package XgileItJavaAssignments;

import java.util.Scanner;
public class ReverseString {
        public static void main(String[] args)
        {
            String s;
            //reading String from User
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter a String:");
            s = sc.nextLine();
            System.out.println("After Reverse String is:");
            //determining length of String
            int i = s.length();
            while (i > 0)
            {
                //Printing character at Index -1
                System.out.println(s.charAt(i - 1));
                //Decreasing length of String
                i--;
            }
        }
    }

