package tute01b_code.ex8;

import utils.AttrRef;
import utils.DomainConstraint;
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
 *  denom != 0
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
  public Rat(@AttrRef("num") int n, @AttrRef("denom") int d) throws NotPossibleException {
    if (!validateDenom(d))
      throw new NotPossibleException("Rat.init: invalid denominator: " + d);

    this.num = n;
    this.denom = d;
  }


  /**
   * @modifies  this
   * @effects
   *  add r to this, i.e. this_post = (num*r.denom+r.num*denom)/denom*r.denom
   */
  public void add(Rat r) {
    num = num*r.denom+denom*r.num;
    denom = denom * r.denom;
  }

  /**
   * @modifies this
   * @effects subtract r from this, i.e.
   *  this_post = (num*r.denom-r.num*denom)/denom*r.denom
   */
  public void sub(Rat r) {
    num = num*r.denom-denom*r.num;
    denom = denom * r.denom;
  }

  /**
   * @modifies this
   * @effects
   *   if r neq null and the reduced form of this and r are equal, 
   *     i.e. num=r.num and denom=r.denom
   *     return true
   *   else
   *     return false  
   */
  public boolean equals(Rat r) {
    // special cases
    if (r == null)
      return false;
    if (num == 0)
      return r.num == 0;
    if (r.num == 0)
      return false;

    reduce();
    r.reduce();
    return (rNum == r.rNum && denom == r.rDenom);
  }

  /**
   * @effects 
   *    if num != 0 AND rNum, rDenom are null
   *        divide num, denom by the greatest common divisor of them, 
   *        i.e. rNum_post = (num/g), rDenom_post = (denom/g), where g = Gcd(num,denom)   
   */
  private void reduce() {      
    if (num!=0 && rNum == null && rDenom == null) {
      int temp = num;
      if (num < 0)
        temp = -num;

      int g = gcd(temp, denom);
      rNum = num / g;
      rDenom = denom / g;
    }
  }

  // return the greatest common divisor of two natural numbers x, y (x > 0)
  private int gcd(int x, int y) {
    /*
     * BASIS. for all natural numbers x > 0: 
     *       i) GCD(x,x)=x
     *      ii) GCD(x,0)=x
     * INDUCTION. for all natural numbers x, y (x > 0), there are two cases:
     *       i) GCD(x,y)=GCD(x-y,y) if x > y
     *       ii) GCD(x,y)=GCD(x,y-x) if x < y
     */
    if (x == y || y == 0) {
     return x;
   } else if (x > y) {
     return gcd(x-y,y);
   } else {
     return gcd(x,y-x);
   }
  }
  
  @Override
  public String toString() {
    if (num == 0 && denom != 0)
      return "0";
    else
      return num + "/" + denom;
  }

  /**
   * @effects <pre>
   *            if this satisfies abstract properties
   *              returns true
   *            else
   *              returns false</pre>
   *  
   */
  public boolean repOK() {
    return validateDenom(denom);
  }
  
  /**
   * @effects
   *  if denom is valid
   *    return true
   *  else
   *    return false 
   */
  private boolean validateDenom(int denom) {
    return denom != 0;
  }

}