
/**
 * Mug is a Buyable that the user can purchase
 * A mug costs $5.00
 * 
 * @author Blake
 *
 */
public class Mug implements Buyable {
    
    /**
     * Constructor for the mug class
     */
    public Mug() {
        
    }
    
    /** 
     * Overrides the default toString method
     * Just returns the string "Classy Mug"
     *
     */
    @Override
    public String toString() {
        return "Classy Mug";
    }
    
    /** 
     * Returns the cost of a mug
     * Mugs always cost $5.00, so a Money object representing 500 cents is returned
     */
    public Money getCost() {
        return new Money(500);
    }
    
    

}
