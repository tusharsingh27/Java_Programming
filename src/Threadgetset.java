
    class MyThread extends Thread {
        public void run() {
            System.out.println("run method executed by Thread:" + Thread.currentThread().getName());
        }
    }
    class Threadgetset
    {
        public static void main(String[]args)
        {
            MyThread t=new MyThread();
            t.start();
            System.out.println("main method executed by Thread:" + Thread.currentThread().getName());
        }
    }
