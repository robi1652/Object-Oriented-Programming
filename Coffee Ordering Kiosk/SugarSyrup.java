
/**
 * SugarSyrup is an Addition that the user can add to any beverage
 * 
 * @author Blake
 *
 */
public class SugarSyrup extends Additions {
    
    /**
     * Constructor for the SugarSyrup class
     */
    public SugarSyrup() {
        
    }
    
    /** 
     * Overrides the default toString method
     * Returns the string "Sugar Syrup"
     *  
     */
    @Override
    public String toString() {
        return "Sugar Syrup";
    }
    
    /**
     * Returns the cost of adding Sugar Syrup to a beverage
     * The cost is always 50 cents, so it returns a Money object with
     * the amountInCents of 50
     * 
     */
    public Money getCost() {
        return new Money(50);
    }
    
    /** 
     * Returns the calories of adding Sugar Syrup to your beverage
     * Sugar Syrup will always be 60 calories, so it returns an integer of 60 
     */
    public int getCalories() {
        return 60;
    }

}
