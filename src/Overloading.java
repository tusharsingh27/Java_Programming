public class Overloading {

        public void m1()
        {
            System.out.println("no-arg");
        }
        public void m1(int i)
        {
            System.out.println("int-arg");
        }
        public void m1(double d)
        {
            System.out.println("double-arg");
        }
        public static void main(String[] args)
        {
            Overloading o=new Overloading();
            o.m1();
            o.m1(10);
            o.m1(10.5);
        }
    }
