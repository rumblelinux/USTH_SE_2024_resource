package tute01b_code.ex5;

import java.util.Vector;

import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;
import utils.collections.Collection;

/**
 * @overview EvenIntSet are mutable, unbounded sets of even integers.
 * @attributes 
 *   elements   Set<Integer>  Vector<Integer>
 * @object A typical IntSet object is c={x1,...,xn}, where x1,...,xn are
 *   elements.
 * @abstract_properties
 *  optional(elements) = false /\ 
 *  for all x in elements. (x is integer /\ x mod 2 = 0) /\  
 *  for all x, y in elements. x neq y
 * @author dmle
 */
public class EvenIntSet implements Collection {
  @DomainConstraint(type = "Vector", optional = false)
  private Vector<Integer> elements; // use generic syntax 

  // constructor methods
  /**
   * @effects initialise <tt>this</tt> to be empty
   */
  public EvenIntSet() {
    //
    elements = new Vector<>();
  }

  /**
   * @modifies <tt>this</tt>
   * @effects 
   *    if x mod 2 = 1 or x is already in this 
   *      return false 
   *    else 
   *      add x to this, i.e., this_post = this + {x}
   *      return true
   */
  @DOpt(type=OptType.MutatorAdd)
  public boolean insert(int x) {
    if (x % 2 == 0 && getIndex(x) < 0) {
      elements.add(x); // auto-boxing
      return true;
    } else {
      return false;
    }
  }

  /**
   * @modifies <tt>this</tt>
   * @effects
   *   if x is not in this 
   *     return false
   *   else 
   *     remove x from this, i.e. this_post = this - {x}
   *     return true
   */
  @DOpt(type=OptType.MutatorRemove)
  public boolean remove(int x) {
    int i = getIndex(x);
    if (i < 0) {
      return false;
    }
    elements.set(i, elements.lastElement());
    elements.remove(elements.size() - 1);
    return true;
  }

  /**
   * @effects <pre>
   *  if x is in this 
   *    return true 
   *  else 
   *    return false</pre>
   */
  @DOpt(type=OptType.ObserverContains)
  public boolean isIn(int x) {
    return (getIndex(x) >= 0);
  }

  
  /**
   * @effects return the cardinality of <tt>this</tt>
   */
  @DOpt(type=OptType.ObserverSize)
  public int size() {
    return elements.size();
  }
  
  /**
   * @effects
   *  if this is not empty
   *    return array Integer[] of elements of this
   *  else 
   *    return null 
   */
  @DOpt(type=OptType.Observer)  
  public Integer[] getElements() {
    if (size() == 0)
      return null;
    else
      return elements.toArray(new Integer[size()]);
  }
  
  /**
   * @effects <pre>
   *  if x is in this 
   *    return the index where x appears 
   *  else 
   *    return -1</pre>
   */
  private int getIndex(int x) {
    for (int i = 0; i < elements.size(); i++) {
      if (x == elements.get(i))
        return i;
    }

    return -1;
  }
  
  @Override
  public String toString() {
    if (size() == 0)
      return "EvenIntSet:{ }";

    String s = "EvenIntSet:{" + elements.elementAt(0).toString();
    for (int i = 1; i < size(); i++) {
      s = s + " , " + elements.elementAt(i).toString();
    }

    return s + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof EvenIntSet))
      return false;

    // use Vector.equals to compare elements
    return elements.equals(((EvenIntSet)o).elements);
  }
  
  /**
   * @effects <pre>
   *   if this is valid
   *     return true 
   *   else
   *     return false</pre>
   */
  public boolean repOK() {
    if (elements == null)
      return false;

    for (int i = 0; i < elements.size(); i++) {
      Integer x = elements.get(i); 

      /* omitted due to the use of generic
      if (!(x instanceof Integer))
        return false;
      */
      // check x mod 2
      if (x % 2 == 1) return false;
      
      for (int j = i + 1; j < elements.size(); j++) {
        if (elements.get(j).equals(x))
          return false;
      }
    }
    return true;
  }
}
