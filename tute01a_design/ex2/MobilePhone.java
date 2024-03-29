package tute01a_design.ex2;
import utils.DomainConstraint;
import utils.NotPossibleException;
import utils.AttrRef;
import utils.DOpt;
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
 *  mutable(model) = false /\ optional(model) = false /\ length(model) = 50 /\
 *  mutable(color) = false /\ optional(color) = false /\ length(color) = 1 /\
 *          color in {'R', 'O', 'Y', 'B', 'P'} /\ 
 *  mutable(year) = false /\ optional(year) = false /\ min(year) = 1970 /\
 *  mutable(guaranteed) = true /\ optional(guaranteed) = true   
 */
public class MobilePhone2 {
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
   *        initialise this as <tt><manName, model, color, year></tt>
   *    else
   *        throws NotPossibleException
   */
  public MobilePhone(@AttrRef("manName") String manName, 
      @AttrRef("model") String model, 
      @AttrRef("color") Color color, 
      @AttrRef("year") int year) throws NotPossibleException
  
  /**
   * @effects 
   *    return manName
   */
  @DOpt(type=OptType.Observer) @AttrRef("manName")   
  public String getManName()

  /**
   * @effects 
   *    return model
   */
  @DOpt(type=OptType.Observer) @AttrRef("model")   
  public String getModel()

  /**
   * @effects 
   *    return color
   */
  @DOpt(type=OptType.Observer) @AttrRef("color")   
  public Color getColor()

  /**
   * @effects 
   *    return year
   */
  @DOpt(type=OptType.Observer) @AttrRef("year")   
  public int getYear()

  /**
   * @effects 
   *    return guaranteed
   */
  @DOpt(type=OptType.Observer) @AttrRef("guaranteed")   
  public boolean getGuaranteed()

  /**
   * @effects
   *    if guaranteed is valid
   *        set this.guaranteed = guaranteed
   *        return true
   *    else
   *        return false
   */
  @DOpt(type=OptType.Mutator) @AttrRef("guaranteed")   
  public boolean setGuaranteed(boolean guaranteed)
  
  @Override
  public String toString()

  @Override
  public boolean equals(Object o)

  /**
   * @effects 
   *    if this is valid
   *        return true
   *    else
   *        return false
   */
  public boolean repOK()
  
  /**
   * @effects 
   *    if manName is valid
   *        return true
   *    else
   *        return false
   */
  private boolean validateManName(String manName)
  
  /**
   * @effects 
   *    if model is valid
   *        return true
   *    else
   *        return false
   */
  private boolean validateModel(String model)
  
  /**
   * @effects 
   *    if color is valid
   *        return true
   *    else
   *        return false
   */
  private boolean validateColor(Color color)
  
  /**
   * @effects 
   *    if year is valid
   *        return true
   *    else
   *        return false
   */
  private boolean validateYear(int year)
    
}
