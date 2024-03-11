package tute01b_code.ex6.partial;

import java.util.Vector;

import utils.DomainConstraint;
import utils.DOpt;
import utils.OptType;

/**
 * @overview StringIntMaps are mutable sets of mappings between strings and
 *   integers. 
 * @attributes
 *   entries   Set<<String,Integer>>    Vector<Object[]>
 * @object 
 *   A typical StringIntMap is {<s1,i1>,...,<sn,in>}, where s1,...,sn are
 *   strings and i1,...,in are integers.
 * @abstract_properties
 *   mutable(entries)=true /\ optional(entries)=false /\ 
 *   for all e in entries. (e neq null /\ e.length=2 /\ e[0] is string /\ e[1] is integer) /\
 *   for all e, f in entries. (equals(e[0],f[0])=false \/ e[1] != f[1])
 *   
 * @author dmle
 */
public class StringIntMap {
  @DomainConstraint(type="Vector",optional=false)
  private Vector<Object[]> entries;
  
  /**
   * @effects
   *   initialise this to be empty
   */
  public StringIntMap() {
      entries = new Vector<>();
  }
  
  /**
   * @modifies this
   * @effects
   *   if <s,i> is not in this
   *     add <s,i> to this, i.e. this_post = this+{<s,i>}
   */
  @DOpt(type=OptType.MutatorAdd)  
  public void add(String s, int i) {
      if (!isIn(s, i)) {
          entries.add(new Object[] {s, i}); // <s,i> = 2-element Object array
      }
  }
  
  /**
   * @modifies this
   * @effects
   *   if <s,i> is in this
   *     remove <s,i> from this, i.e. this_post = this-{<s,i>}
   *  
   */
  @DOpt(type=OptType.MutatorRemove)    
  public void remove(String s, int i)
  
  /**
   * @effects <pre>
   *            if there exist entries e = < s1,i > in this
   *            s.t. (s = null /\ s1 = null) || s1.equals(s) 
   *              returns an StringIntMap containing all such e
   *            else
   *              returns null</pre>
   */
  public StringIntMap lookup(String s, int i) {
      StringIntMap matches = new StringIntMap();
      Object[] e;
      for (Object eo : entries) {
          e = (Object[]) eo;    // e represents a pair
          if ((e[0] == null && s == null) || 
               (e[0] != null && e[0].equals(s))) {
            if (((Integer) e[1]) == i) {
                matches.entries.add(e);
            }
          }
      }
      
      // return result
      if (matches.size() == 0) {
          return null;
      } else {
          return matches;
      }
  }

  /**
   * @effects
   *   if there exists entries <s,i> in this for some integer i
   *     return a set of all such entries
   *   else
   *     return null   
   */ 
  public StringIntMap lookup(String s)
  
  /**
   * Reversed look up method.
   * 
   * @effects
   *   if there exists entries <s,i> in this for some integer i
   *     return a set of all such entries
   *   else
   *     return null
   */  
  public StringIntMap lookup(int i)

  /**
   * @effects
   *   if <s,i> is in this, 
   *   i.e. exists <s',i'> in this s.t. equals(s,s') /\ i=i'
   *     return true
   *   else
   *     return false
   */
  @DOpt(type=OptType.ObserverContains)  
  public boolean isIn(String s, int i) {
      if (lookup(s, i) == null) {
          return false;
      } else {
          return true;
      }
  }
  
  /**
   * @effects returns the cardinality of this
   */
  @DOpt(type=OptType.ObserverSize)    
  public int size()

  @Override
  public String toString() {
      
    // similar to IntSet
          
    if (size() == 0) {
        // empty 
        return "StringIntMap:{}";
    }

    /*use StringBuffer b/c it involves creating many String fragments */
    StringBuffer sb = new StringBuffer("StringIntMap:{");
    Object[] e;
    int size = size();
    for (int i = 0; i < size; i++) {
        e = (Object[]) entries.get(i);
        sb.append("<").append(e[0]).append(",").append(e[1]).append(">");
        if (i < size-1) {
            sb.append(",");
        }
    }
    
    sb.append("}");
    
    return sb.toString();
  }
  
  /**
   * @effects <pre>
   *            if this satisfies abstract properties
   *              returns true
   *            else
   *              returns false
   *          </pre>
   */
   public boolean repOK()
}
