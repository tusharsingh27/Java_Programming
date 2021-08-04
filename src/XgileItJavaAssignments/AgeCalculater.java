package XgileItJavaAssignments;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;


    public class AgeCalculater
    {
        public static void main(String[] args)
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your Dateofbirth[dd-mm-yyyy]:");
            String Dateofbirth = sc.nextLine();
            String arr[] = Dateofbirth.split("-");
            int d = Integer.parseInt(arr[0]);            //Converting String to Integer
            int m = Integer.parseInt(arr[1]);
            int y = Integer.parseInt(arr[2]);
            var t1 = LocalDate.of(y, m, d);
            var t2 = LocalDate.now();
            var p = Period.between(t1, t2);
            System.out.println("Age is:" + p.getYears() + "Years" + p.getMonths() + "Months" + p.getDays() + "Days");
            //To get Age in Years,Months and Days
            var t3 = (p.getYears() * 365) + (p.getMonths() * 30) + p.getDays();
            //   System.out.println(t3);
            int leapcount = 0;
            for (int i = y; i <= LocalDate.now().getYear(); i++)
            {
                if (i % 4 == 0)
                {
                    leapcount++;
                }

            }
            var age=t3+leapcount;
            System.out.println("Age in days:"+age);
        }
    }
