import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.lang.Math;
import java.time.format.DateTimeFormatter;

// your bible
// https://docs.oracle.com/javase/8/docs/api/

public class JavaCheatSheet {
  public static void main(String[] args) {
    ////// Strings
    // String Builder
    StringBuilder sb = new StringBuilder();
    sb.append("abc");
    sb.toString();

    // access string character by index
    String string = "hello";
    string.charAt(3);

    // gets the string version of the input (input can be many different types)
    String.valueOf('c');
  
    // converts the string into an integer stream
    System.out.println("somestring".chars());
    // converts an integer into a character
    System.out.println((char) 182);
    Integer.parseInt("124");
    // substring - // first is the index, second is the end index
    string.substring(10, 4);

    // string capitalization
    string.toUpperCase();
    string.toLowerCase();

    // regex to replace
    string.replaceAll("regexExpression", "replaceWith");

    // checks if a substring exists
    "abcde".contains("bcd");

    // IndexOf = Finds the index of the character, returns -1 if not found
    if ("aeiouAEIOU".indexOf("h") == -1) {}

    "somestrinG".toLowerCase().chars().distinct().count();

    // datetime format to string
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedLastModified = lastModified.format(formatter);

    ////// Characters
    // Checks if a char is alphabetic, numeric, special symbol etc.
    Character.isAlphabetic('c');
    Character.isLetterOrDigit('£');
    Character.isDigit(0);
    Character.toUpperCase('c');

    ////// Integers
    Integer i = Character.getNumericValue('9');
    int digit = Character.digit('9', 10);
    Integer.parseInt("28428");

    ////// Ternary operator
    int n = 10;
    // return n % 2 == 0 ? n * 8 : n * 9;

    ////// Streams
    // import java.util.stream.IntStream;
    IntStream intStream = IntStream.range(0, 10);
    IntStream intStream2 = IntStream.of(2,5,6,7);
    int[] convertedStream = intStream.toArray();

    String[] names = {"Al", "Ankit", "Kushal", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah"};
		Arrays.stream(names);	// same as Stream.of(names)
    Stream<String> namez = Stream.of("Ava", "Aneri", "Alberto");
    Arrays.stream(new int[] {2, 4, 6, 8, 10});
    int minTill = Arrays.stream(tills).min().getAsInt();

    "somestringHere".chars()
              // maps integers into characters
              .mapToObj(c -> (char) c)
              // filters the strings that are not part of the original string
              .filter(c -> !"aeiouAEIOU".contains(String.valueOf(c)))
              // maps unfiltered characters into strings
              .map(String::valueOf)
              // collects & concatonates them
              .collect(Collectors.joining());

    "somethingHere".chars()
              .map(c -> {
                Integer k = Character.getNumericValue(c);
                return k * k;
              })
              .mapToObj(Integer::toString)
              // Instead of collectors, you can also use
              //.reduce("", (acc, str) -> acc + str)
              //
              .collect(Collectors.joining());

    String[] splitString = "12 4 9 15".split(" ");
              IntSummaryStatistics stats = Arrays.stream(splitString)
              .map(c -> Integer.parseInt(c))
              .collect(Collectors.summarizingInt(Integer::intValue));

    String result = String.valueOf(125)
                .chars()
                .mapToObj(Character::getNumericValue) 
                .sorted((s1, s2) -> s2 - s1)
                .map(String::valueOf)
                .collect(Collectors.joining());

    List<Object> abc = new ArrayList<>();
    
    abc
    .stream()
    .filter(c -> c instanceof Integer)
    .collect(Collectors.toList());

    ////// Arrays
    int[] originalArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
    // Get the first 10 elements
    int[] firstTenElements = Arrays.copyOfRange(originalArray, 0, 10);

    ////// HashMaps
    // Check if a key is present
    map.containsKey(key) ? map.get(key) : 0;

    // Declaring a hash map on initation;
    Map<Character, Character> inverse = new HashMap<>(Map.of(
      'w', 'e',
      's', 'n',
      'e', 'w',
      'n', 's'
    ));

    // Or use the diamond operator (above java 9 only);
    HashMap<Character, Character> inverse = new HashMap<>();
    
    // Iterate over key value pairs
    for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
      String key = entry.getKey();
      Integer value = entry.getValue();
      System.out.println("Key: " + key + ", Value: " + value);
    }

    // Counting characters in a string with default values
    HashMap<Character, Integer> counter = new HashMap<>();
    for (Character c : str.toCharArray()) {
      if (counter.containsKey(c)) {
        counter.put(c, counter.get(c) + 1);
      } else {
        counter.put(c, 1);
      }
    }
    // Neater way
    for (Integer i : a) {
      counter.put(i, counter.getOrDefault(i, 0) + 1);
    }

    // Iterating over values of a hashmap
    // Remember, lambda functions cannot break or return from the function scope on their own, hence the temp variable
    boolean hasDuplicates = false;
    for (Integer value : counter.values()) {
        if (value > 1) {
            hasDuplicates = true;
            break;
        }
    }
    return !hasDuplicates;

    // suppose a is an object that has key value pairs
    a.values().iterator().next() // <-- to iterate over the values
    a.keySet().iterator().next() // <-- to iterate over keys

    ////// Lists
    // List<> allows for polymorphism
    List<String> people = Arrays.asList("Al", "Ankit", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah");
    List<Integer> list = Arrays.asList(7, 4, -3, 18);
    Set<Integer> set = new HashSet<Integer>( Arrays.asList(5, 6, 10) );   
    
    // When you remove an element from list, if you give an integer, it removes by index;
    list.remove(10);
    // If you provide an object, removes an object
    list.remove("AI");
  }
}
