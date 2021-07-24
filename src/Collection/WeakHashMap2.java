package Collection;

import java.util.WeakHashMap;

public class WeakHashMap2
{

        public static void main(String[] args)
        {
            // Creating WeakHashMap of even numbers
            WeakHashMap<String, Integer> numbers = new WeakHashMap<>();

            String one = new String("One");
            Integer oneValue = 1;
            numbers.put(one, oneValue);

            String two = new String("Two");
            Integer twoValue = 2;
            numbers.put(two, twoValue);

            System.out.println("WeakHashMap: " + numbers);

            // Using remove() with single parameter
            int value = numbers.remove("Two");
            System.out.println("Removed value: " + value);

            // Using remove() with 2 parameters
            boolean result = numbers.remove("One", 3);
            System.out.println("Is the entry {One=3} removed? " + result);

            System.out.println("Updated WeakHashMap: " + numbers);
        }
    }
