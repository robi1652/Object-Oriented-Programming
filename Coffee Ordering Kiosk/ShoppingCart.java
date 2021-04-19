import java.util.ArrayList;

/**
 * ShoppingCart is a class that represents the user's cart, full of all the items they have told 
 * the CoffeeKiosk they want to buy
 * 
 * @author Blake
 *
 */
public class ShoppingCart {
    
    /**
     * theItems is an ArrayList that represents the user's shopping cart
     * Any buyable object they want to buy is held in theItems
     * 
     * 
     */
    private ArrayList<Buyable> theItems = new ArrayList<>();
    
    /**
     * Constructor for ShoppingCart object
     */
    public ShoppingCart() {
        
    }
    
    /** 
     * Overrides the default toString method 
     * If the shopping cart is empty and the user presses 1 to view the cart,
     * it returns "Cart is empty"
     * 
     * When printing a beverage, it prints in this order:
     * Size + type of beverage + Any additions + (calories) + Cost (without tax)
     * 
     * When printing a mug, it just prints "Classy Mug: $5.00"
     * 
     * Lastly, it prints "Total:" plus the total cost with added sales tax
     */
    @Override
    public String toString() {
        String stringToReturn = "";
        String costAsString;
        
        if (theItems.size() == 0) {
            return "Cart is empty";
        }
        
        for (int i = 0; i < theItems.size(); i++) {
            
            costAsString = "" + theItems.get(i).getCost();
            
            stringToReturn = stringToReturn + (i + 1) + ".   ) " + theItems.get(i) + ": " + theItems.get(i).getCost() +"\n";
        }
        stringToReturn = stringToReturn + "Total: " + getTotal();
        
        return stringToReturn;
    }
    
    
    /**
     * Adds a buyable object (beverage or mug) to theItems
     * 
     * @param itemToAdd is the mug or beverage the user wishes to buy
     */
    public void addItem(Buyable itemToAdd) {
        theItems.add(itemToAdd);
    }
    
    /**
     * Returns the total cost of everything in theItems that the user wishes to purchase
     * Sales tax is included
     * 
     * @return
     */
    public Money getTotal() {
        Money totalCost = new Money(0);
        for (int i = 0; i < theItems.size(); i++) {
            totalCost = totalCost.add(theItems.get(i).getCost());
        }
        
        return totalCost.add(Money.computeMNSalesTax(totalCost));
    }

}
