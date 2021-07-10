//public interface InterfaceConflict {

    interface Left
    {
        int x=777;
    }

    interface Right
    {
        int x=888;
    }
    class InterfaceConflict implements Left,Right
    {
        public static void main(String[]args)
        {
            System.out.println(Left.x);
            System.out.println(Right.x);
        }
    }
