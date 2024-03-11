package tute01a_design.ex7;
import java.util.Vector;

import userlib.DomainConstraint;
import utils.DOpt;
import utils.OptType;

/**
 * @overview <tt>IntQueue</tt> is a sequence of integers that has an upper bound on the number 
 *    of integers that can be stored. 
 * @attributes
 *  maxSize   Integer       
 *  elements  Sequence<Integer>  Vector<Integer>
 * @object
 *  A typical IntQueue is <n,els>, where maxSize(n) and elements(els).
 * @abstract_properties
 *  mutable(maxSize)=false /\ optional(maxSize)=false /\ min(maxSize)=1 /\
 *  for all x in elements: x is Integer /\
 *  mutable(elements)=true /\ optional(elements)=false /\ 
 *  size(elements) <= maxSize /\
 *  for all x, y in elements. (x is added before y -> x is removed before y).
 * @author dmle
 */
public class IntQueue {
  @DomainConstraint(type="Vector",optional=false)
  private Vector<Integer> elements;
  @DomainConstraint(type="Integer",mutable=false,optional=false,min=1)
  private int maxSize;

  /**
   * @effects
   *   if n is valid
   *     initialise this as <n,[]>
   *   else
   *     throws NotPossibleException
   */
  public IntQueue(int n) throws NotPossibleException
  
  /**
   * @modifies this
   * @effects
   *   if size(elements) < maxSize
   *     add x to the first position of this, i.e. elements_post = [x,elements]
   *     return true
   *   else
   *     return false
   */
  @DOpt(type=OptType.MutatorAdd)
  public boolean enq(int x)
  
  /**
   * @modifies this
   * @effects
   *   if size(elements) > 0
   *     remove the last element of this and return it, i.e.
   *       elements = [head,n] -> result = n /\ elements_post = [head]
   *   else
   *     print error "empty queue"
   *     return null
   */
  @DOpt(type=OptType.MutatorRemove)
  public Integer deq()
  
  /**
   * @effects return size(elements)
   */
  @DOpt(type=OptType.ObserverSize)
  public int size()
  
  /**
   * @effects
   *  if size(elements) = 0
   *     return true
   *   else 
   *     return false
   */
  public boolean isEmpty()

  /**
   * @effects
   *   if size(elements) = maxSize
   *     return true
   *   else 
   *     return false
   */
  public boolean isFull()
      

  /**
   * @modifies this
   * @effects
   *   remove all items in elements, i.e. elements_post=[]
   */
  public void clear()
  
  @Override
  public String toString()
  
  /**
   * @effects   <pre>
   *              if this satisfies the abstract properties
   *                return true
   *              else 
   *                return false</pre>
   */
  public boolean repOK()
}
