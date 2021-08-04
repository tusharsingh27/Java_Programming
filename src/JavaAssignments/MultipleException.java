package JavaAssignments;

public class MultipleException
    {
        public static void main(String[] args)
        {
            try
            {
                int num = 20 / 0;                 //Arithmetic Exception occurs
                int[] firstArray = {2, 3, 4, 5};
                int secondNum = firstArray[4];         //ArrayIndexOutOfBoundsExceptionException occurs
                int numParse = Integer.parseInt("A101");//NullFormatException occurs
            }
            catch (ArithmeticException ae)
            {
                System.out.println("Division by 0 ");
            }
            catch (ArrayIndexOutOfBoundsException aiob)
            {
                System.out.println("Wrong array index is refeered");
            }
            catch (NullPointerException npe)
            {
                System.out.println("Trying to perform operation on Null referrence");
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("Illegal Parsing being performed");
            }
            catch (Exception e)
            {
                System.out.println("Exception occured");
            }
        }
    }
