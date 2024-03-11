package tute01b_code.ex6;

import java.util.Arrays;
import java.util.Vector;

import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;
import utils.collections.Collection;

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
public class StringIntMap implements Collection {
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
   *     return true
   *   else
   *    return false
   */
  @DOpt(type=OptType.MutatorAdd)
  public boolean add(String s, int i) {
    if (!isIn(s,i)) {
      entries.add(new Object[]{s,i});
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * @modifies this
   * @effects
   *   if <s,i> is in this
   *     remove <s,i> from this, i.e. this_post = this-{<s,i>}
   *     return true
   *   else
   *    return false
   *  
   */
  @DOpt(type=OptType.MutatorRemove)
  public boolean remove(String s, int i) {
    // alternative (but slower): use look up to check if entry exists
    // then use Vector.remove for each entry
    Object[] e;
    for (int j = 0; j < entries.size(); j++) {
      e = entries.get(j);
      if ((e[0] == null && s == null) || 
          e[0].equals(s)) {
        if (((Integer)e[1]) == i) {
          entries.setElementAt(entries.lastElement(), j);
          entries.remove(entries.size()-1);
          return true;
        }
      }
    }
    
    return false;
  }
  
  /**
   * @effects <pre>
   *            if there exist entries e = < s1,i > in this
   *            s.t. (s = null /\ s1 = null) || s1.equals(s) 
   *              returns an StringIntMap containing all such e
   *            else
   *              returns null</pre>
   */
  private StringIntMap lookup(String s, int i) {
    StringIntMap matches = new StringIntMap();
    Object[] e;
    for (int j = 0; j < entries.size(); j++) {
      e = entries.get(j);
      if ((e[0] == null && s == null) || 
          (e[0] != null && e[0].equals(s))) {
        if (((Integer)e[1]) == i) {
          matches.entries.add(e);
        }
      }
    }
    
    if (matches.size() == 0) 
      return null;
    else 
      return matches;
  }

  /**
   * @effects
   *   if there exists entries <s,i> in this for some integer i
   *     return a set of all such entries
   *   else
   *     return null   
   */ 
  public StringIntMap lookup(String s) {
    StringIntMap matches = new StringIntMap();
    Object[] e;
    for (int j = 0; j < entries.size(); j++) {
      e = entries.get(j);
      if ((e[0] == null && s == null) || 
          (e[0] != null && e[0].equals(s))) {
        matches.entries.add(e);
      }
    }
    
    if (matches.size() == 0) 
      return null;
    else 
      return matches;
  }
  
  /**
   * Reversed look up method.
   * 
   * @effects
   *   if there exists entries <s,i> in this for some string s
   *     return a set of all such entries
   *   else
   *     return null
   */  
  public StringIntMap lookup(int i) {
    StringIntMap matches = new StringIntMap();
    Object[] e;
    for (int j = 0; j < entries.size(); j++) {
      e = entries.get(j);
      if (((Integer)e[1]) == i) {
        matches.entries.add(e);
      }
    }
    
    if (matches.size() == 0) 
      return null;
    else 
      return matches;
    
  }

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
    return lookup(s,i) != null;
  }
  
  /**
   * @effects 
   *  return this.entries as Object[][]
   */
  @DOpt(type=OptType.Observer)
  public Object[][] getEntries() {
    if (size() == 0)
      return null;
    else
      return entries.toArray(new Object[size()][]);
  }
  
  /**
   * @effects returns the cardinality of this
   */
  @DOpt(type=OptType.ObserverSize)    
  public int size() {
    return entries.size();
  }

  @Override
  public String toString() {
    if (entries.size() == 0)
      return "StringIntMap:{ }";
    
    String s = "StringIntMap:{ ";
    Object[] e;
    int size = entries.size();
    for (int i = 0; i < size; i++) {
      e = entries.get(i);
      s += "<" + e[0] + "," + e[1] + ">";
      if (i < size-1)
        s += ",";
    }
    return s + " }";
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof StringIntMap)) {
      return false;
    } else {
      StringIntMap other = (StringIntMap) o;
      if (size() == other.size()) {
        // same size: check entries
        Object[][] otherEntries = other.getEntries();
        for (Object[] e : entries) {
          boolean found = false;
          for (Object[] oe : otherEntries) {
            if (Arrays.equals(e, oe)) {
              found = true; break;
            }
          }
          if (!found) {
            // e is not found: not equals
            return false;
          }
        }
        
        // equals
        return true;
      } else { // different sizes
        return false;
      }
    }
  }
  
  /**
   * @effects <pre>
   *            if this satisfies abstract properties
   *              returns true
   *            else
   *              returns false
   *          </pre>
   */
   public boolean repOK() {
    // optional=false
    if (entries == null)
      return false;

    // check that entries of the form <String,Integer>
    Object[] e;
    Object[] other;
    Object s;
    Object i;
    int size = entries.size();
    for (int j = 0; j < size; j++) {
      e = entries.get(j);
      s = e[0];
      i = e[1];

      if (e == null || e.length != 2) {
        // not a pair
        return false;
      }

      if (s != null && !(s instanceof String)) {
        // key is not string
        return false;
      }

      if (!(i instanceof Integer)) {
        // value is not integer
        return false;
      }
    }

    // check uniqueness
    Object otherS;
    Object otherI;
    for (int j = 0; j < size; j++) {
      e = entries.get(j);
      s = e[0];
      i = e[1];
      for (int k = j + 1; k < size; k++) {
        other = entries.get(k);
        otherS = other[0];
        otherI = other[1];
        if ((s == null && otherS == null) || 
            (s != null && s.equals(otherS))) {
          if (i.equals(otherI)) {
            // duplicate
            return false;
          }
        }
      }
    }

    return true;
  }
}
