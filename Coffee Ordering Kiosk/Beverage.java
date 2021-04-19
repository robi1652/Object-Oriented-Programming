import java.util.ArrayList;

/**
 * Beverage is an abstract class that covers Coffee and Tea
 * It represents the beverages that the user can purchase
 * 
 * Additions can be added and stored in the addOns ArrayList
 * The cost and calories of the additions are added to the final counts of each
 * for the beverage
 * 
 * 
 * 
 * @author Blake
 *
 */
public abstract class Beverage implements Buyable{
    
    /**
     * These constants represent the possible beverage sizes as integers
     * 0 = Small
     * 1 = Medium
     * 2 = Large
     * 
     */
    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;
    
    /**
     * The ArrayList of Additions that the user can add in the Kiosk file
     * Can be Soy Milk, Sugar Syrup, both, or neither (empty)
     */
    protected ArrayList<Additions> addOns = new ArrayList<>();
    /**
     * Represents the size of the beverage, Small = 0, Medium = 1, Large = 2
     * It is assigned by the constants above
     */
    protected int size;
    
    /**
     * Constructor for the beverage class
     */
    public Beverage() {
        
    }
    
    
    /** 
     * Returns the cost of whatever beverage the user has ordered
     * in the form of a Money object
     */
    public abstract Money getCost();
         
    /**
     * Returns the total calories of whatever beverage the user has ordered
     * as an integer representing calories
     * 
     * @return integer representing calories
     */
    public abstract int getCalories();
    
    /**
     * Sets the size of the beverage using an integer as input
     * 0 = small
     * 1 = medium 
     * 2 = large
     * 
     * @param size the integer determining the size 
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * "Adds" on either Soy Milk, Sugar Syrup, or both to a Beverage
     * by adding those objects to an ArrayList
     * 
     * @param extraToAdd the Additions object to add to the ArrayList of addOns
     */
    public void addToDrink(Additions extraToAdd) {
        addOns.add(extraToAdd);
    }

}
