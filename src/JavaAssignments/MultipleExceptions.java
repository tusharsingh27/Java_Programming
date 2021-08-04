package JavaAssignments;

import java.util.Scanner;
class MultipleCatchExceptions
{
    public static void main(String[] args) {
        int x, y;
        Scanner sc = new Scanner(System.in);
        try
        {
            System.out.println("Enter your First Number");
            x = Integer.parseInt(sc.nextLine());
            System.out.println("Enter your second number");
            y = Integer.parseInt(sc.nextLine());
            System.out.println("z=" + (x/y));
        }
        catch (ArithmeticException ae)
        {
            System.out.println("A number cannot be divided by 0,illegal operation in Java");
            System.out.println("Exception Thrown:" + ae);
            //ArithmeticException will be generated when division by zero
        }
        catch(NumberFormatException nfe)
        {
            System.out.println("Invalid datatypes are entered,number must be an integer.");
            System.out.println("Exception Thrown:" + nfe);
            //NumberFormatException will be generated when enter datatype like float,double
        }
        catch(RuntimeException re)
        {
            System.out.println("Exception Thrown:" + re);
        }
        System.out.println("Out of try catch block");
    }
}
