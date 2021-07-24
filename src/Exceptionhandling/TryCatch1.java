package Exceptionhandling;

public class TryCatch1 {

        public static void main(String[] args)
        {
            int i=40;
            int j=0;
            int data;
            try
            {
                data=i/j; //may throw exception
            }
            // handling the exception
            catch(Exception e)
            {
                // resolving the exception in catch block
                System.out.println(i/(j+2));
            }
        }
    }
