package tute01b_code.ex1;

import utils.NotPossibleException;

/**
 * @overview
 *  A program that demonstrates how to use {@link Person} and {@link MobilePhone}.
 *  
 * @author dmle
 *  
 */
public class GreetingConversationApp {
  /**
   * The main method
   * @effects <pre>
   *  create a {@link MobilePhone} m 
   *  create a {@link Person} object p with phone m
   *  display an error message if failed
   * </pre>
   */
  public static void main(String[] args) {
      
    // create a new MobilePhone
    MobilePhone m1 = null;
    try {
      m1 = new MobilePhone("Samsung", "Galaxy S3", 'X', 2014);
      // display phone
      System.out.println("Created: " + m1);
    } catch (NotPossibleException e) {
      System.err.println("-> Failed to create mobile phone");
      e.printStackTrace();
    }

    try {
      // create a new Person with that phone
      Person p1 = new Person(1, "Duc Le");
      // display phone
      System.out.println("Created: " + p1);

      // assign phone to person
      p1.setPhone(m1);
      // display phone
      System.out.println("  has phone: " + p1.getPhone());
    } catch (NotPossibleException e) {
      // error creating person
      System.err.println("-> Failed to create person");
      e.printStackTrace();
    }
  }
}
