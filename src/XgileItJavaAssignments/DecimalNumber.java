package XgileItJavaAssignments;

import java.util.Scanner;
import java.util.regex.Pattern;

    public class DecimalNumber
    {
        public static void main(String[] args)
        {
            //   double value;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter no. with Decimal Digits");
            //    value = sc.nextDouble();

            //    String num = Double.toString(value);//Converting Double value to String
            String num;
            num=sc.nextLine();
            String decnum[] = num.split(Pattern.quote("."));//Creating Array and Storing Number in String form

            int d1 = Integer.parseInt(decnum[0]);//Converting String to Integer
            System.out.println(d1);

            int d2 = Integer.parseInt(decnum[1]);
            System.out.println(d2);
            if (d1 == d2)               //Comparing two numbers
            {
                System.out.println("Identical");
            }
            else
            {
                System.out.println("Not Identical");
            }
        }

}
