package tute01b_code.ex2;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview A human being that is characterised by attributes such as id and name
 * @attributes
 *  id      Integer         int
 *  name    String      
 *  phone   MobilePhone2 
 *  
 * @object
 *  A typical Person is <i,n,p> where id(i), name(n), phone(p)
 * 
 * @abstract_properties
 *  mutable(id)=false /\ optional(id)=false /\ min(id)=1 /\
 *  mutable(name)=true /\ optional(name)=false /\ length(name)=30 /\
 *  mutable(phone)=true /\ optional(phone)=true
 *  
 * @author dmle
 * @version 
 */
public class Person {
    @DomainConstraint(type="Integer",mutable=false,optional=false,min=1)
    private int id;

    @DomainConstraint(type="String",mutable=true,optional=false,length=30)
    private String name;

    @DomainConstraint(type="MobilePhone2",mutable=true,optional=true)    
    private MobilePhone phone;
    
    /**
     * @effects 
     *  if id, name are valid
     *      initialise this as <id,name>
     *  else
     *      throw new NotPossibleException
     */
    public Person(@AttrRef("id") int id, @AttrRef("name") String name) throws NotPossibleException {
        if (!validateId(id)) {
            // id is invalid, initialise this as <> and print error
          throw new NotPossibleException("Person.init: invalid id: " + id);
        } 
        
        if (!validateName(name)) {
            // name is invalid, initialise this as <> and print error
          throw new NotPossibleException("Person.init: invalid name: " + name);
        }
        
        // id and name are both valid: initialise this as <id,name>
        this.id = id;
        this.name = name;
    }
    
    /**
     * @effects 
     *  return id
     */
    @DOpt(type=OptType.Observer) @AttrRef("id") 
    public int getId() {
        return id;
    }
    
    /**
     * @effects 
     *  return name
     */
    @DOpt(type=OptType.Observer) @AttrRef("name") 
    public String getName() {
        return name;
    }
    
    /**
     * @effects 
     *  return phone
     */
    @DOpt(type=OptType.Observer) @AttrRef("phone") 
    public MobilePhone getPhone() {
        return phone;
    }
    
    /**
     * @modifies this.name
     * @effects 
     *  if name is valid
     *      set this.name = name
     *      return true
     *  else
     *      return false
     */
    @DOpt(type=OptType.Mutator) @AttrRef("name") 
    public boolean setName(String name) {
        if (validateName(name)) {
            this.name = name;
            return true;
        } else {
          return false;
        }
    }

    /**
     * @modifies this.phone
     * @effects 
     *  if phone is valid
     *      set this.phone = phone
     *      return true
     *  else
     *      return false
     */
    @DOpt(type=OptType.Mutator) @AttrRef("phone") 
    public boolean setPhone(MobilePhone phone) {
        if (validatePhone(phone)) {
            this.phone = phone;
            return true;
        } else {
          return false;
        }
    }

    @Override
    public String toString() {
        String str;
        /*use String*/
        str = "<"+id+", "+name+", "+ phone +">";
        /*use StringBuffer*/
        /*
        StringBuffer sb = new StringBuffer();
        sb.append("<").
                append(id).append(", ").
                append(name).append(", ").
                append(phone).append(">");
        str = sb.toString();
        */
        
        return str;
    }
    
    @Override
    public boolean equals(Object o) {
      if (o == null || !(o instanceof Person)) {
        return false;
      } else {
        Person other = (Person) o;
        return id == other.getId();
      }
    }
    
    /**
     * @effects 
     *  if this is valid with regards to abstract properties
     *      return true
     *  else
     *      return false
     */
    public boolean repOK() {
        if (validateId(id) && validateName(name)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * @effects 
     *  if id is valid
     *      return true
     *  else
     *      return false
     */
    private boolean validateId(int id) {
        // because id is primitive type, there no need to validate the optional constraint
        // validate min constraint
        if (id < 1) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * @effects 
     *  if name is valid
     *      return true
     *  else
     *      return false
     */
    private boolean validateName(String name) {
        // check optional constraint
        if (name == null || name.length() == 0) {
            return false;
        }
        
        // check length constraint
        if (name.length() > 30) {
            return false;
        } 
        
        return true;
    }
    
    /**
     * @effects 
     *  if phone is valid
     *      return true
     *  else
     *      return false
     */
    private boolean validatePhone(MobilePhone phone) {
        // no need to validate optional constraint b/c phone is an object type
        return true;
    }
}
