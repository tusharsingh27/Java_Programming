
public class Scanner1
{
    public static void main(String[] args)
    {

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("Hello World ");
        String userInputString = scanner.nextLine();
        System.out.println("java is my "+userInputString.toLowerCase());
        System.out.println("WORLD "+ userInputString.toUpperCase());

    }
}