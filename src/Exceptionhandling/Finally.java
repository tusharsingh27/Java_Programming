package Exceptionhandling;

public class Finally
{
    public static void main(String args[])
    {
        try
        {
            int data = 48 / 8;
            System.out.println(data);
        }
        catch (NullPointerException e)
        {
            System.out.println(e);
        }
        finally
        {
            System.out.println("finally block is executed");
        }
    }
}
