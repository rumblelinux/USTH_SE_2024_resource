package tute01b_code.ex5;

import java.util.Arrays;

/**
 * @overview 
 *  A program that demonstrates how to use {@link EvenIntSet}.
 *  
 * @author dmle
 *
 */
public class EvenIntSetApp {
  /**
   * The main method
   * @effects 
   *  <pre>
   *    initialise test data
   *    create an {@link EvenIntSet} from test data
   *    if set is valid
   *      print it
   *    else
   *      print an error message 
   *  </pre>
   */
  public static void main(String[] args) {
    // initialise test data
    int[] elements = {-2,0,4,6,
        2,4,  // duplicate
        1,3,5 // invalid
        };

    System.out.printf("test elements array: %s%n", Arrays.toString(elements));
    
    // create an EvenIntSet from test data
    EvenIntSet eset = new EvenIntSet();
    for (int e : elements) {
      eset.insert(e);
    }
    
    // test validity
    boolean ok = eset.repOK();
    
    if (!ok) {
      // set is not valid
      System.err.println("Set is not valid");
    } else {
      // queue is valid
      System.out.printf("Set: %s%n", eset);
    }
  }
}
