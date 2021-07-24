package Collection;

import java.util.NavigableMap;
import java.util.TreeMap;

    public class NavigableMap1
    {
        public static void main(String[] args)
        {
            // Creating NavigableMap using TreeMap
            NavigableMap<String, Integer> numbers = new TreeMap<>();

            // Insert elements to map
            numbers.put("Two", 2);
            numbers.put("One", 1);
            numbers.put("Three", 3);
            System.out.println("NavigableMap: " + numbers);

            // Access the first entry of the map
            System.out.println("First Entry: " + numbers.firstEntry());

            // Access the last entry of the map
            System.out.println("Last Entry: " + numbers.lastEntry());

            // Remove the first entry from the map
            System.out.println("Removed First Entry: " + numbers.pollFirstEntry());

            // Remove the last entry from the map
            System.out.println("Removed Last Entry: " + numbers.pollLastEntry());
        }
    }
