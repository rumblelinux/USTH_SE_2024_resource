package tute01b_code.ex8;

import utils.NotPossibleException;

/**
 * @overview 
 *  A program that demonstrates how to use {@link Rat}.
 *  
 * @author dmle
 *
 */
public class RatApp {
  /**
   * The main method
   * @effects
   * <pre>
   *  initialise test data
   *  for each test value
   *    create a {@link Rat} r using it
   *    if r is valid
   *      print it
   *    else
   *      print an error message
   *    
   *  create some {@link Rat} objects and invoke some operations to test
   * </pre>
   */
  public static void main(String[] args) {
    // initialise test data
    int[] num = {0,1,2};
    int[] denom = {0,2,3};
    
    // create Rat objects from test data and check for object validity
    Rat r = null;
    for (int i = 0; i < num.length; i++) {
      for (int k = 0; k < denom.length; k++) {
        try {
          r = new Rat(num[i],denom[k]);
          // check for validity
          System.out.println(r);
        } catch (NotPossibleException e) {
          System.err.println("Invalid rational number: " + r);
          e.printStackTrace();
        }
      }
    }
  }
  
  /**
   * A method to demonstrate how to test equality between {@link Rat}s
   * @effects <pre>
   *  initialise some {@link Rat} objects
   *  check their equality
   *  </pre>  
   */
  public static void testEquality() {
    // check equality
    Rat r1 = new Rat(1,2);
    Rat r2 = new Rat(4,8);
    Rat r3 = new Rat(4,6);
    boolean eq = r1.equals(r2);
    System.out.printf("%s equals %s: %b%n", r1, r2, eq);
    eq = r1.equals(r3);
    System.out.printf("%s equals %s: %b%n", r1, r3, eq);    
  }
}
