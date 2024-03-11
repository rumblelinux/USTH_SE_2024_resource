package tute01b_code.ex7;

import java.util.Arrays;

/**
 * @overview 
 *  A program that demonstrates how to use {@link IntQueue}.
 *  
 * @author dmle
 *
 */
public class IntQueueApp {
  /**
   * The main method
   * @effects 
   *  <pre>
   *    initialise test data
   *    create an {@link IntQueue} queue from test data
   *    if queue is valid
   *      print it
   *    else
   *      print an error message 
   *  </pre>
   */
  public static void main(String[] args) {
    // initialise test data
    int maxSize = 3;
    int[] elements = {1,2,3
        ,4 // error
        };

    System.out.printf("maxSize: %d%n", maxSize);
    System.out.printf("elements: %s%n", Arrays.toString(elements));
    
    // create an IntQueue from test data
    IntQueue queue = new IntQueue(maxSize);
    for (int e : elements) {
      boolean added = queue.enq(e);
      if (!added) {
        System.err.printf("IntQueue.enq: cannot add %d because queue is full%n", e);
      }
    }
    
    // test validity
    boolean ok = queue.repOK();
    
    if (!ok) {
      // queue is not valid
      System.err.println("Queue is not valid");
    } else {
      // queue is valid
      System.out.printf("Queue: %s%n", queue);
    }
  }
}
