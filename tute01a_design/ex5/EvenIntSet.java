package tute01a_design.ex5;

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
  public EvenIntSet()

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
  public boolean insert(int x)

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
  public boolean remove(int x)

  /**
   * @effects <pre>
   *  if x is in this 
   *    return true 
   *  else 
   *    return false</pre>
   */
  @DOpt(type=OptType.ObserverContains)
  public boolean isIn(int x)

  
  /**
   * @effects return the cardinality of <tt>this</tt>
   */
  @DOpt(type=OptType.ObserverSize)
  public int size()
  
  /**
   * @effects
   *  if this is not empty
   *    return array Integer[] of elements of this
   *  else 
   *    return null 
   */
  @DOpt(type=OptType.Observer)  
  public Integer[] getElements()
  
  /**
   * @effects <pre>
   *  if x is in this 
   *    return the index where x appears 
   *  else 
   *    return -1</pre>
   */
  private int getIndex(int x)
  
  @Override
  public String toString()

  @Override
  public boolean equals(Object o)
  
  /**
   * @effects <pre>
   *   if this is valid
   *     return true 
   *   else
   *     return false</pre>
   */
  public boolean repOK()
}
