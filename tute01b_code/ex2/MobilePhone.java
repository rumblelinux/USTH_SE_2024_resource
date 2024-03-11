package tute01b_code.ex2;
import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview An electronic device used for communication, entertainment
 * 
 * @attributes
 *  manName     String
 *  model       String
 *  color       Color
 *  year        Integer     int
 *  guaranteed  Boolean     boolean
 *  
 * @object
 *  A typical MobilePhone is <n, m, c, y> where manName(n), model(m), color(c), year(y)
 * 
 * @abstract_properties
 *  mutable(manName) = false /\ optional(manName) = false /\ length(manName) = 100 /\
 *  mutable(model) = false /\ optional(model) = false /\ length(manName) = 50 /\
 *  mutable(color) = false /\ optional(color) = false /\ length(color) = 1 /\
 *         color in {'R', 'O', 'Y', 'B', 'P'}
 *  mutable(year) = false /\ optional(year) = false /\ min(year) = 1970 /\
 *  mutable(guaranteed) = true /\ optional(guaranteed) = true   
 */
public class MobilePhone {
  @DomainConstraint(type="String", mutable=false, optional=false, length=100)
  private String manName;
  
  @DomainConstraint (type="String", mutable= false, optional= false, length=50)
  private String model;
  
  @DomainConstraint (type="Color", mutable= false, optional = false, length=1)
  private Color color;
  
  @DomainConstraint(type="Integer", mutable=false,optional=false,min=1970)
  private int year;
  
  @DomainConstraint(type="Boolean",mutable=true,optional=true)
  private boolean guaranteed;
  
  /**
   * @effects 
   *    if manName, model, color, year are valid
   *        initialise this as <manName, model, color, year>
   *    else
   *        throws NotPossibleException
   */
  public MobilePhone(@AttrRef("manName") String manName, 
      @AttrRef("model") String model, 
      @AttrRef("color") Color color, 
      @AttrRef("year") int year) throws NotPossibleException {
      if (!validateManName(manName)) {
        throw new NotPossibleException("MobilePhone.init: invalid manName: " + manName);
      }
      
      if (!validateModel(model)) {
        throw new NotPossibleException("MobilePhone.init: invalid model: " + model);
      }
          
      if (!validateColor(color)) {
        throw new NotPossibleException("MobilePhone.init: invalid color: " + color);
      }
          
      if (!validateYear(year)) {
        throw new NotPossibleException("MobilePhone.init: invalid year: " + year);
      }
                   
      this.manName = manName;
      this.model = model;
      this.color = color;
      this.year = year;
  }
    
  /**
   * @effects 
   *    return manName
   */
  @DOpt(type=OptType.Observer) @AttrRef("manName")   
  public String getManName() {
      return manName;
  }

  /**
   * @effects 
   *    return model
   */
  @DOpt(type=OptType.Observer) @AttrRef("model")   
  public String getModel() {
      return model;
  }

  /**
   * @effects 
   *    return color
   */
  @DOpt(type=OptType.Observer) @AttrRef("color")   
  public Color getColor() {
      return color;
  }

  /**
   * @effects 
   *    return year
   */
  @DOpt(type=OptType.Observer) @AttrRef("year")   
  public int getYear() {
      return year;
  }

  /**
   * @effects 
   *    return guaranteed
   */
  @DOpt(type=OptType.Observer) @AttrRef("guaranteed")   
  public boolean getGuaranteed() {
      return guaranteed;
  }

  /**
   * @effects
   *    if guaranteed is valid
   *        set this.guaranteed = guaranteed
   *        return true
   *    else
   *        return false
   */
  @DOpt(type=OptType.Mutator) @AttrRef("guaranteed")     
  public boolean setGuaranteed(boolean guaranteed) {
    // no validation needed for guaranteed
    this.guaranteed = guaranteed;
    return true;
  }
  
  @Override
  public String toString() {
      return "MobilePhone<"+manName+","+model+","+color+","+year+">";
  }
  
  @Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof MobilePhone)) {
      return false;
    } else {
      MobilePhone other = (MobilePhone) o;
      return manName.equals(other.getManName()) && 
          model.equals(other.getModel()) &&
          color == other.getColor() && 
          year == other.getYear();
    }
  }
  
  /**
   * @effects 
   *    if this is valid
   *        return true
   *    else
   *        return false
   */
  public boolean repOK() {
      return validateManName(manName) && validateModel(model) 
            && validateColor(color) && validateYear(year);
  }
  
  /**
   * @effects 
   *    if manName is valid
   *        return true
   *    else
   *        return false
   */
  private boolean validateManName(String manName) {
      return manName != null && manName.length() <= 100;
  }
  
  /**
   * @effects 
   *    if model is valid
   *        return true
   *    else
   *        return false
   */
  private boolean validateModel(String model) {
      return model != null && model.length() <= 50;
  }
  
  /**
   * @effects 
   *    if color is valid
   *        return true
   *    else
   *        return false
   */
  private boolean validateColor(Color color) {
      return color != null;
  }
  
  /**
   * @effects 
   *    if year is valid
   *        return true
   *    else
   *        return false
   */
  private boolean validateYear(int year) {
      return year >= 1970;
  }
    
}
