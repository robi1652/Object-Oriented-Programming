
/**
 * SoyMilk is an Addition that the user can add to any beverage
 * @author Blake
 *
 */
public class SoyMilk extends Additions {
    
    /**
     * Constructor for the SoyMilk class
     */
    public SoyMilk() {
        
    }
    
    /** 
     * Overrides the default toString method
     * Returns the string "Soy"
     */
    @Override
    public String toString() {
        return "Soy";
    }
    
    /** 
     * Returns the cost of adding soy milk to a beverage
     * Soy milk always costs $1.00 to add, so it represented by a Money object of 100 cents
     */
    public Money getCost() {
        return Money.ONE_DOLLAR;
    }
    
    /** 
     * Returns the calories of adding soy milk to a beverage
     * Soy milk is always 80 calories, so the return is just an integer representing the calories
     */
    public int getCalories() {
        return 80;
    }

}
