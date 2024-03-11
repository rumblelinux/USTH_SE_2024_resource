package tute01a_design.ex8;
import userlib.DomainConstraint;
import utils.NotPossibleException;

/**
 * @overview Rats are rational numbers of the form x/y, where x is the
 *   numerator and y is the denominator.
 * @attributes
 *   num     Integer
 *   denom   Integer
 * @object
 *   A typical Rat is n/d where num(n) and denom(d).
 * @abstract_properties
 *  mutable(num)=false /\ optional(num)=false /\
 *  mutable(denom)=false /\ optional(denom)=false /\ 
 *    denom != 0
 *    
 * @author dmle
 */
public class Rat {
  @DomainConstraint(type="Integer",optional=false)
  private int num;
  @DomainConstraint(type="Integer",optional=false)
  private int denom;

  // not formal attributes, added because of reduce()
  private Integer rNum; // reduced value of num
  private Integer rDenom; // reduced value of denom
  
  /**
   * @effects <pre>
   *   if d is valid 
   *     initialise this as n/d
   *   else
   *     throws NotPossibleException
   *  </pre>
   */
  public Rat(int n, int d) throws NotPossibleException

  /**
   * @modifies  this
   * @effects
   *  add r to this, i.e. this_post = (num*r.denom+r.num*denom)/denom*r.denom
   */
  public void add(Rat r)

  /**
   * @modifies this
   * @effects subtract r from this, i.e.
   *  this_post = (num*r.denom-r.num*denom)/denom*r.denom
   */
  public void sub(Rat r)

  /**
   * @modifies this
   * @effects
   *   if r neq null and the reduced form of this and r are equal, 
   *     i.e. num=r.num and denom=r.denom
   *     return true
   *   else
   *     return false  
   */
  public boolean equals(Rat r)

  /**
   * @effects 
   *    if num != 0 AND rNum, rDenom are null
   *        divide num, denom by the greatest common divisor of them, 
   *        i.e. rNum_post = (num/g), rDenom_post = (denom/g), where g = Gcd(num,denom)   
   */
  private void reduce()

  /**
   * @requires x > 0
   * @effects
   *   return the greatest common divisor of two natural numbers x, y (x > 0)
   */
  private int gcd(int x, int y)
  
  @Override
  public String toString()

  /**
   * @effects <pre>
   *            if this satisfies abstract properties
   *              returns true
   *            else
   *              returns false</pre>
   *  
   */
  public boolean repOK()
  
  /**
   * @effects
   *  if denom is valid
   *    return true
   *  else
   *    return false 
   */
  private boolean validateDenom(int denom);
}
