package Collection;


    import java.util.LinkedHashSet;

    public class LinkedHashSet2 {
        public static void main(String[] args) {
            LinkedHashSet<Integer> numbers = new LinkedHashSet<>();
            numbers.add(1);
            numbers.add(2);
            numbers.add(3);
            numbers.add(4);
            System.out.println("LinkedHashSet1: " + numbers);

            LinkedHashSet<Integer> primeNumbers = new LinkedHashSet<>();
            primeNumbers.add(2);
            primeNumbers.add(3);
            System.out.println("LinkedHashSet2: " + primeNumbers);

            // Check if primeNumbers is a subset of numbers
            boolean result = numbers.containsAll(primeNumbers);
            System.out.println("Is LinkedHashSet2 is subset of LinkedHashSet1? " + result);
        }
    }

