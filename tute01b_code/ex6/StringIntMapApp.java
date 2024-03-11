package tute01b_code.ex6;

import java.util.Arrays;

/**
 * 
 * @overview 
 *  A program that demonstrates how to use {@link StringIntMap}.
 *  
 * @author dmle
 */
public class StringIntMapApp {
  /**
   * The main method
   * @effects <pre> 
   *  initialise two arrays: keys as {@link String}[] and vals as {@link Integer}[]
   *  print keys and vals
   *  
   *  create a {@link StringIntMap} map
   *  for each pair (k,v) s.t. k=keys[i], v=vals[i] (i = 1...keys.length-1)
   *    map.add(k,v)
   *  
   *  print map
   *  
   *  if map is valid
   *    print "map ok"
   *  else
   *    print an error message
   *  </pre>
   */
  public static void main(String[] args) {
    //initialise two arrays
    String[] keys = { // 
        null, 
        "I", //null,
        "love", 
        "Java"
        };
    int[] vals = { //
        1, 
        2, //1, 
        3, 
        4};
    
    // print keys and vals
    System.out.printf("strings: %s%n", Arrays.toString(keys));
    System.out.printf("integers: %s%n", Arrays.toString(vals));
    
    // add keys, vals to a StringIntMap
    StringIntMap map = createMap(keys, vals);
    
    // print map
    System.out.println("map: " + map);
    
    // use map
    if (map.repOK()) {
      System.out.println("map ok");
    } else {
      System.err.println("Invalid map object");
    }
    
    // test equality
    StringIntMap map2 = createMap(keys, vals);
    System.out.println("map2: " + map2);
    boolean equal = map.equals(map2);
    if (equal) {
      System.out.println("map.equals(map2) = " + equal);
    } else {
      System.err.println("Error: not map.equals(map2)");
    }
    
    String[] keys2 = { // 
        "null", 
        "I", //null,
        "love", 
        "Java"
        };
    StringIntMap map3 = createMap(keys2, vals);
    System.out.println("map3: " + map3);
    equal = map.equals(map3);
    if (!equal) {
      System.out.println("map.equals(map3) = " + equal);
    } else {
      System.err.println("Error: map.equals(map3)");
    }
    
  }

  /**
   * @requires 
   *  <tt>keys, vals != null /\ 
   *  keys.length = vals.length
   *  </tt> 
   * @effects 
   *  create and return a {@link StringIntMap} that contains entries 
   *  specified in <tt>keys, vals</tt>
   */
  private static StringIntMap createMap(String[] keys, int[] vals) {
    int num = keys.length;
    StringIntMap map = new StringIntMap();
    for (int i = 0; i < num; i++) {
      map.add(keys[i], vals[i]);
    }
    
    return map;
  }
}
