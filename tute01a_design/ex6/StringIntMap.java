package tute01a_design.ex6;

import java.util.Vector;

import utils.DomainConstraint;
import utils.DOpt;
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
 * @author dmle
 */
public class StringIntMap implements Collection {
  @DomainConstraint(type="Vector",optional=false)
  private Vector<Object[]> entries;
  
  /**
   * @effects
   *   initialise this to be empty
   */
  public StringIntMap()
  
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
  public boolean add(String s, int i)
  
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
  public boolean remove(String s, int i)
  
  /**
   * @effects <pre>
   *            if there exist entries e = < s1,i > in this
   *            s.t. (s = null /\ s1 = null) || s1.equals(s) 
   *              returns an StringIntMap containing all such e
   *            else
   *              returns null</pre>
   */
  public StringIntMap lookup(String s, int i)

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
  public boolean isIn(String s, int i)
  
  /**
   * @effects 
   *  return this.entries as Object[][]
   */
  @DOpt(type=OptType.Observer)
  public Object[][] getEntries()
  
  /**
   * @effects returns the cardinality of this
   */
  @DOpt(type=OptType.ObserverSize)  
  public int size()

  @Override
  public String toString()
  
  @Override
  public boolean equals(Object o)
  
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
