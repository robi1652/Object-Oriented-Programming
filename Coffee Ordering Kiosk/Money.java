
/**
 * Money class creates the money objects that represent the currency that 
 * The purchasable items cost
 * 
 * The lone input is amountInCents which is an integer that represents the 
 * amount of money that the item is worth, in cents
 * 
 * Int was used over double for ease of use
 * 
 * @author Blake
 *
 */
public class Money {
    
    /**
     * These constants represent a few cost possibilities of items as Money Objects
     * 0 cents = ZERO
     * 100 cents = ONE_DOLLAR
     * 200 cents = TWO_DOLLARS
     * 
     */
    public static final Money ZERO = new Money(0);
    public static final Money ONE_DOLLAR = new Money(100);
    public static final Money TWO_DOLLARS = new Money(200); 
    
    /**
     * This is the integer representing the number of cents for the Money object
     */
    private int amountInCents;
    
    
    /**
     * Constructor for the Money class
     * Takes in an integer representing the amount of cents for that money object
     * and sets it to amountInCents
     * 
     * @param cents
     */
    public Money(int cents) {
        amountInCents = cents;
        
    }
      
    /** 
     * Overrides the default toString method
     * This prints "$", followed by the money in a standard dollar format
     * with the dollars, a decimal, and the cents.
     * 
     */
    @Override
    public String toString() {
        int numberBeforeDecimal;
        int numberAfterDecimal;
        
        String stringToReturn = "$";
        
        numberBeforeDecimal = amountInCents / 100;
        numberAfterDecimal = amountInCents % 100;
        
        if (numberAfterDecimal < 10) {
            stringToReturn = stringToReturn + numberBeforeDecimal + ".0" + numberAfterDecimal; 
        } else {
            stringToReturn = stringToReturn + numberBeforeDecimal + "." + numberAfterDecimal;
        }   
        
        return stringToReturn;
    }
    
    /**
     * Computes the cost of sales tax, based in MN
     * 
     * @param centsBeforeTax is the amount of money that is to be taxed
     * @return A new Money object of the cost of the sales tax
     */
    public static Money computeMNSalesTax(Money centsBeforeTax) {
        double taxCents; 
        int taxCentsInt;
        
        taxCents = centsBeforeTax.amountInCents * .06875;
        taxCents = Math.round(taxCents);
        
        return new Money((int) taxCents); 
    }
    
    /**
     * An addition function for the Money object
     * Adds the amountInCents of each Money object
     * 
     * @param secondMoney Money to be added to the first money object
     * @return sum of the two money objects
     */
    public Money add(Money secondMoney) {
        return new Money(amountInCents + secondMoney.amountInCents);
    }
    
    /**
     * A subtraction function for the Money object
     * Subtracts the amountInCents of secondMoney from the first money object
     * 
     * @param secondMoney is the money to be subtracted
     * @return is the total money remaining after the subtraction
     */
    public Money subtract(Money secondMoney) {
        return new Money(amountInCents - secondMoney.amountInCents);
    }
    
    
}
