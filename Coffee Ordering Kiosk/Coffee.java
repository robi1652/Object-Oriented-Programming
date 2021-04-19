
/**
 * Coffee object class
 * 
 * It is a beverage that the user can order through the coffee kiosk
 * 
 * @author Blake
 *
 */
public class Coffee extends Beverage{
    
    /**
     * Boolean that determines whether or not the coffee object is a "decaf coffee"
     */
    private boolean isDecaf;
    
    /**
     * Constructor for Coffee
     * 
     * Determines if it is a decaf coffee 
     * with a boolean 
     * 
     * @param isDecaf is the boolean to determine whether or not the coffee object is a decaf drink
     */
    public Coffee(boolean isDecaf) {
        this.isDecaf = isDecaf;
    }
    
    /** 
     * Prints the coffee object out in the order of:
     * 
     * "Size, then (Decaf, if chosen) Coffee, then any add ons, then (number of calories)"
     */
    public String toString() {
        String typeOfCoffee;
        String listOfAdditions = "";
        
        if (isDecaf == true) {
            typeOfCoffee = "Decaf Coffee";
        } else {
            typeOfCoffee = "Coffee";
        }
        
        if (addOns.size() == 0) {
            listOfAdditions = "";
        } else {
            for (int i = 0; i < addOns.size(); i++) {
                listOfAdditions = listOfAdditions + " + " + addOns.get(i);
            }
        }
       if (size == Beverage.SMALL) {
            return "Small " + typeOfCoffee + listOfAdditions + " (" + getCalories() + " calories)";
        } else if (size == Beverage.MEDIUM) {
            return "Medium " + typeOfCoffee + listOfAdditions + " (" + getCalories() + " calories)";
        } else {
            return "Large " + typeOfCoffee + listOfAdditions + " (" + getCalories() + " calories)";
        }
        
    }
    
    /** 
     * Returns the cost of the coffee object
     * as a Money object
     * 
     */
    public Money getCost() {
        Money costToReturn;
        
        if (size == Beverage.SMALL) {
            costToReturn = new Money(150);
        } else if (size == Beverage.MEDIUM) {
            costToReturn = Money.TWO_DOLLARS;
        } else {
            costToReturn =  new Money(250);
        }
        
        for (int j = 0; j < addOns.size(); j++) {
            costToReturn = costToReturn.add(addOns.get(j).getCost());
        }
        
        return costToReturn;
        
    }
    
    
    /** 
     * Returns the total calories of the coffee object 
     * as an integer representing calories
     * 
     */
    public int getCalories() {
        int caloriesToReturn; 
        
        if (size == Beverage.SMALL) {
            caloriesToReturn = 5;
        } else if (size == Beverage.MEDIUM) {
            caloriesToReturn = 10;
        } else {
            caloriesToReturn = 15;
        }
        
        for (int k = 0; k < addOns.size(); k++) {
            caloriesToReturn = caloriesToReturn + addOns.get(k).getCalories();
        }
        
        return caloriesToReturn;
                
    }

}
