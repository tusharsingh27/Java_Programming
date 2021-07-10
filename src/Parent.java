
    public class Parent {

        int i=10;
        {
            m1();
            System.out.println("First Instance Block");
        }
        Parent()
        {
            System.out.println("Constructor");
        }
        public static void main(String[]args)
        {
            Parent p=new Parent();
            System.out.println("Main");
        }
        public void m1()
        {
            System.out.println(j);
        }
        {
            System.out.println("Second Instance Block");
        }
        int j=20;
    }


