package tute01b_code.ex7;

import java.util.Vector;

import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;
import utils.collections.Collection;

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
 *  
 * @author dmle
 */
public class IntQueue implements Collection {
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
  public IntQueue(int n) throws NotPossibleException {
    if (n <= 0)
      throw new NotPossibleException("IntQueue.init: invalid queue bound value");
    
    maxSize = n;
    elements = new Vector<>(maxSize);
  }

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
  public boolean enq(int x) {
    int size = size();
    if (size == maxSize) {
//      System.err.printf("IntQueue.enq: cannot add %d because queue is full%n", x);
      return false;
    }

    if (size == 0) { // first element of the queue
      elements.add(x);
    } else { // subsequent element
      elements.add(0,x);      
    }
    
    return true;
  }

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
  public Integer deq() {
    if (isEmpty()) {
      System.err.println("IntQueue.deq: empty queue");
      return null;
    } else {
      return (elements.remove(elements.size()-1));
    }
  }
  
  /**
   * @effects return size(elements)
   */
  @DOpt(type=OptType.ObserverSize)  
  public int size() {
    return elements.size();
  }
  
  /**
   * @effects
   *  if size(elements) = 0
   *     return true
   *   else 
   *     return false
   */
  @DOpt(type=OptType.Observer)  
  public boolean isEmpty() {
    return elements.size()==0;
  }

  /**
   * @effects
   *   if size(elements) = maxSize
   *     return true
   *   else 
   *     return false
   */
  public boolean isFull() {
    return (elements.size() == maxSize);
  }
      

  /**
   * @modifies this
   * @effects
   *   remove all items in elements, i.e. elements_post=[]
   */
  public void clear() {
    elements.clear();
  }
  
  @Override
  public String toString() {
    if (elements.size() == 0)
      return "IntQueue:[]";
    
    String s = "IntQueue: [" + elements.elementAt(0).toString();
    for (int i = 1; i < size(); i++) {
      s = s + " , " + elements.elementAt(i).toString();      
    }
    
    return s + "]";      
  }
  
  /**
   * @effects   <pre>
   *              if this satisfies the abstract properties
   *                return true
   *              else 
   *                return false</pre>
   */
  public boolean repOK() {
    // optional(elements)=false
    if (elements == null)
      return false;
    
    // for all x in elements: x is Integer
    /* not needed
    for (Object x : elements) {
      if (!(x instanceof Integer))
        return false;
    }
    */
    
    // min(maxSize)=1 /\ size(elements) <= maxSize
    int sz = size();
    if  (maxSize <= 0 || sz > maxSize) 
      return false;
        
    // for all x, y in elements. (x is added before y -> x is removed before y).
    // IMPORTANT: check on a copy of this because deq() removes them
    int x;
    IntQueue copy = new IntQueue(maxSize);
    for (int i = 0; i < sz; i++) {
      x = elements.get(i); // we know elements are integers
      // enq() must result in correct deq()
      copy.enq(x);
      if (x != copy.deq())
        return false;
    }
    
    // if we get here then ok
    return true;    
  }
}