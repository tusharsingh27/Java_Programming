package JavaAssignments;

public class BusVehicle
    {
        int mpg;
        int fuel_capacity;
        String type;

        // parent method start()
        public void start()
        {
            System.out.println("JavaAssignments.Vehicle starting...");
            // do steps to start a vehicle
        }

        //declaration of instance variable storing num of passengers
        private int passengers;

        // method that returns the number of passengers
        public int getPassengers() {
            return passengers;
        }

        // method the “sets” the number of passenger
        public void setPassengers(int numPassengers) {
            this.passengers = numPassengers;
        }
    }

    class Vehicle
    {
        public static void main(String[] args)
        {
            Bus1 myBus = new Bus1(); // Create a Car object
            int wheels = myBus.numberOfWheels();
            myBus.start();
            // create a new object of type JavaAssignments.Vehicle named "myVehicle"
            BusVehicle myVehicle = new BusVehicle();
            // then we can set the instance variables in the myVehicle object
            myVehicle.setPassengers(5);
            // then we can also access the methods within the myVehicle object
            System.out.println(myVehicle.getPassengers());
            DeluxeBus LuxuryBus= new DeluxeBus();
            //below we are using the "make" variable which is defined in Car
            LuxuryBus .make = "Volkswagon";
            //below we are using the "model" variable which is defined in Car
            LuxuryBus.model = "599 XX";
            //below we are using the "hasSpoiler" variable which is defined in SportsCar
            LuxuryBus.hasBed = true;
            //below we are using the "hasRaceTires" variable which is defined in SportsCar
            LuxuryBus.hasAc = true;
            //below we are using the "drive()" method which is defined in Car
            LuxuryBus.drive(20);
            //below we are using the "getZeroToSixtyTime" method which is defined in SportsCar
            double time = LuxuryBus.getZerotoSixtyTime();
            BusVehicle mini = new MiniBus();
            // now invoke the start() method on the Motorcycle
            // which overrides the start() method in the JavaAssignments.Vehicle class
            mini.start();
            // now invoke the start() method on the JavaAssignments.Vehicle object
            // (which will call the start() method in the JavaAssignments.Vehicle class)
            myVehicle.start();
            myBus.changeGear();
        }
    }

    class Bus4
    {
        String make;
        String model;

        public void drive(int distance)
        {
            // some action(s) to drive the bus
        }
    }
    class Scooter
    {
        String make;
        String model;

        public void drive(int distance)
        {
            // some action(s) to drive the scooter
        }
    }

    abstract class Vehicle1
    {
        // Abstract method - the method below does not have a “method body”
        public abstract int numberOfWheels();

        // Below is a standard method with a body
        public void changeGear()
        {
            System.out.println("Changing the gear");
        }
        public void start()
        {
            System.out.println("Starting vehicle.");
        }
    }

    // Subclass (inherits from JavaAssignments.Vehicle)
    class Bus1 extends Vehicle1
    {
        public void changeGear()
        {
            System.out.println("Gear has been changed");
        }
        public int numberOfWheels()
        {
            // The method body of numberOfWheels() is provided here
            System.out.println("Bus has 4 wheels");
            return 4;
        }
    }


    class Bus2
    {
        public String make;
        public String model;
        public void drive(int distance)
        {
            // some action(s) to drive the car
        }
    }


    class DeluxeBus extends Bus2 {
        boolean hasAc;
        boolean hasBed;

        public double getZerotoSixtyTime()
        {
            // do something to determine time from zero to sixty MPH
            return 0;
        }
    }

    // child class Motorcycle
    class MiniBus extends BusVehicle
    {
        MiniBus()
        {
            mpg = 30;
            fuel_capacity = 10;
            type = "minibus";
        }

        // the method below overrides the behavior of the start() method in the JavaAssignments.Vehicle class
        @Override
        public void start() {
            System.out.println("Minibus starting...");
            // do steps to start a minibus
        }

    }