import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.lang.Math;

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
      
    ////// Lists
    // List<> allows for polymorphism
    List<String> people = Arrays.asList("Al", "Ankit", "Brent", "Sarika", "amanda", "Hans", "Shivika", "Sarah");
  }
}