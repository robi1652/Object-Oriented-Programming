import java.util.Scanner;

/**
 * @author Scott Yilek - provided to CISC 230 class for use on Lab 4
 *
 */
public class CoffeeKiosk {

    public static void main(String[] args) {
        
        
        ShoppingCart theCart = new ShoppingCart();
        Scanner keyboard = new Scanner(System.in);
        String userChoice;
        
        System.out.println("Welcome to the Coffee Shop");
        while (true) {
            System.out.println("Please make a selection.");
            System.out.println("Options: ");
            System.out.println("(1) Display Shopping Cart");
            System.out.println("(2) Add Beverage");
            System.out.println("(3) Purchase Mug");
            System.out.println("(4) Checkout");
            System.out.print("Your choice: ");
            userChoice = keyboard.nextLine();
            if (userChoice.equals("1")) {
                System.out.println("+++++++++++++");
                System.out.println(theCart);
                System.out.println("+++++++++++++");
            } else if (userChoice.equals("2")) {
                Beverage theirBev;
                System.out.println("   Choose a beverage.");
                System.out.println("   (1) Coffee");
                System.out.println("   (2) Decaf Coffee");
                System.out.println("   (3) Tea");
                System.out.print("Your choice: ");
                userChoice = keyboard.nextLine();
                if (userChoice.equals("1")) {
                    theirBev = new Coffee(false);
                } else if (userChoice.equals("2")) {
                    theirBev = new Coffee(true);
                } else if (userChoice.equals("3")) {
                    theirBev = new Tea();
                } else {
                    break;
                }
                
                System.out.print("   Choose a size (S,M,L): ");
                
                userChoice = keyboard.nextLine();
                if (userChoice.equals("S")) {
                    theirBev.setSize(Beverage.SMALL);
                } else if (userChoice.equals("M")) {
                    theirBev.setSize(Beverage.MEDIUM);
                } else if (userChoice.equals("L")) {
                    theirBev.setSize(Beverage.LARGE);
                } else {
                    break;
                }
                
                System.out.println("   Choose a Add-ons.");
                System.out.println("   (1) Soy milk");
                System.out.println("   (2) Sugar Syrup");
                System.out.println("   (3) Both");
                System.out.println("   (4) Neither");
                
                System.out.print("Your choice: ");
                userChoice = keyboard.nextLine();
                if (userChoice.equals("1")) {
                    theirBev.addToDrink(new SoyMilk());
                } else if (userChoice.equals("2")) {
                    theirBev.addToDrink(new SugarSyrup());
                } else if (userChoice.equals("3")) {
                    theirBev.addToDrink(new SoyMilk());
                    theirBev.addToDrink(new SugarSyrup());
                } else if (userChoice.equals("4")) {
                    //nothing
                } else {
                    break;
                }
                
                
                System.out.println("Adding "+theirBev.toString()+" to the cart...");
                theCart.addItem(theirBev);
            } else if (userChoice.equals("3")) { 
                System.out.println("Adding mug to cart...");
                theCart.addItem(new Mug());
            
            } else if (userChoice.equals("4")) {
                System.out.println("+++++++++");
                System.out.println("Your total is "+theCart.getTotal());
                System.out.println("Please see cashier...");
                break;
            } else {
                break;
            }
            
            
            
        }
        
        
        
    }

}
