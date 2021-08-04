package XgileItJavaAssignments;

import java.util.Scanner;

public class ShareMarket {

        public static void main(String[] args)
        {

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter length of list");
            int number = sc.nextInt();//Array Length

            int array[] = new int[number];
            System.out.println("Enter the " + number + " numbers");
            for (int j = 0; j <array.length; j++)  // Entering the values in the Array
            {
                array[j] = sc.nextInt();
            }

            int  maximumValue = array[0]; //Setting first element as maximum value of array
            int  minimumValue = array[0]; //Setting first element as minimum value of array

            for (int i = 1; i < array.length; i++)
            {
                if (array[i] > maximumValue)  //Checking Array Value and Setting maximum value
                    maximumValue = array[i];

                if (array[i] <minimumValue)   //Checking Array Value and Setting minimum value
                    minimumValue = array[i];
            }

            System.out.print("\nThe lowest value is " + minimumValue );
            System.out.print("\nThe peak value is " +maximumValue);
            System.out.println("\nDifference between peak and low value is "+(maximumValue-minimumValue));

        }
    }
