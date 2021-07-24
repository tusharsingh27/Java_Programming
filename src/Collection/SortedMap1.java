package Collection;

import java.util.SortedMap;
import java.util.TreeMap;

    public class SortedMap1
    {

        public static void main(String[] args)
        {
            // Creating SortedMap using TreeMap
            SortedMap<String, Integer> numbers = new TreeMap<>();

            // Insert elements to map
            numbers.put("Two", 2);
            numbers.put("One", 1);
            System.out.println("SortedMap: " + numbers);


            // Access the first key of the map
            System.out.println("First Key: " + numbers.firstKey());

            // Access the last key of the map
            System.out.println("Last Key: " + numbers.lastKey());

            // Remove elements from the map
            int value = numbers.remove("One");
            System.out.println("Removed Value: " + value);
        }
    }

