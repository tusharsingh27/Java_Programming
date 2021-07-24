package Collection;

import java.util.ArrayList;
import java.util.Iterator;
   public class ListIteratorWhile
    {
        public static void main(String[] args)
        {
            ArrayList<String> languages = new ArrayList<>();

            // Add elements in the array list
            languages.add("Java");
            languages.add("Python");
            languages.add("JavaScript");
            languages.add("Swift");

            // Create a variable of Iterator
            // store the iterator returned by iterator()
            Iterator<String> iterate = languages.iterator();
            System.out.println("Element: Index");

            // loop through ArrayList till it has all elements
            // Use methods of Iterator to access elements
            while(iterate.hasNext())
            {

                // access element
                String element = iterate.next();
                System.out.print(element + ": ");

                // access index of each element
                System.out.println(languages.indexOf(element));
            }
        }
    }

