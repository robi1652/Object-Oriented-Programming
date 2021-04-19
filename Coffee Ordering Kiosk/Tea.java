
/**
 * Tea is a Beverage that the user can order through the Coffee Kiosk
 * 
 * @author Blake
 *
 */
public class Tea extends Beverage{
    
    /**
     * Constructor for the Tea class 
     */
    public Tea() {
        
    }
    
    /** 
     * Overrides the default toString method
     * 
     * Prints the Tea object out in the order of:
     * Size, then "Tea", then Any additions, then (calories) 
     * 
     */
    @Override
    public String toString() {
        String listOfAdditions = "";
        
        if (addOns.size() == 0) {
            listOfAdditions = "";
        } else {
            for (int i = 0; i < addOns.size(); i++) {
                listOfAdditions = listOfAdditions + " + " + addOns.get(i);
            }
        }
        if (size == Beverage.SMALL) {
            return "Small Tea" + listOfAdditions + " (" + getCalories() + " calories)";
        } else if (size == Beverage.MEDIUM) {
            return "Medium Tea" + listOfAdditions + " (" + getCalories() + " calories)";
        } else {
            return "Large Tea" + listOfAdditions + " (" + getCalories() + " calories)";
        }
        
    }
    
    /** 
     * Returns the cost of the Tea object, including the added cost
     * of any Additions
     *
     */
    public Money getCost() {
        Money costToReturn = new Money(150);
        
        for (int j = 0; j < addOns.size(); j++) {
            costToReturn = costToReturn.add(addOns.get(j).getCost());
        }
        
        return costToReturn;
    }
    
    /** 
     * Returns the calories of the Tea object
     * along with the calories of any additons added 
     * 
     * By default, Tea is always 5 calories if nothing is added
     * 
     */
    public int getCalories() {
        int caloriesToReturn = 5;
        
        for (int k = 0; k < addOns.size(); k++) {
            caloriesToReturn = caloriesToReturn + addOns.get(k).getCalories();
        }
        
        return caloriesToReturn;
    }

}
