public class Overloading1 {


        public void m1(int i)
        {
            System.out.println("int-arg");
        }
        public void m1(float f)
        {
            System.out.println("float-arg");
        }

        public static void main(String[]args)
        {
            Overloading1 o=new Overloading1();
            {
                o.m1(10);
                o.m1(10.5f);
                o.m1('a');
                o.m1(10l);
              //  o.m1(10.5);
            }
        }

    }
