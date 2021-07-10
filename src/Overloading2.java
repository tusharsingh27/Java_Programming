
    class Animal
    {
    }
    class Monkey  extends Animal
    {
    }
    class Overloading2
    {
        public void m1(Animal a)
        {
            System.out.println("Animal Version");
        }
        public void m1(Monkey m)
        {
            System.out.println("Monkey Version");
        }
        public static void main(String[]args)
        {
            Overloading2 o=new Overloading2();
            Animal a=new Animal();
            o.m1(a);

            Monkey m=new Monkey();
            o.m1(m);

            Animal a1=new Monkey();
            o.m1(a1);
        }
    }


