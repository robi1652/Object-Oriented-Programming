import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is the TodoDriver class
 * It is the driver for the TodoList Lab, and it runs the basic menu that the user interacts with
 * It allows the user to:
 * 1) Sort their TodoList by date of the tasks
 * 2) Sort their TodoList by importance of the tasks
 * 3) Add a new task
 * 4) Save the TodoList to a new final and exit
 * 
 * @author Blake
 *
 */
public class TodoDriver {
    
    public static void main(String[] args) {
        String username;
        String userChoice;
        String newTodoItemAsCSV = "";
        TodoList userTodoList = null;
        Scanner keyboard = new Scanner(System.in);
        Scanner fileScan;
        
        
        System.out.println("Welcome to the TodoList application");
        System.out.print("Enter your username: ");
        username = keyboard.nextLine();
        
        try {
            userTodoList = TodoList.buildFromUsername(username);
        } catch (IllegalArgumentException e) {
            System.out.println("An error occurred trying to read the file for your username.");
            System.out.print("Create new Todo List? (Y/N) ");
            if (keyboard.nextLine().equals("Y")) {
                userTodoList = new TodoList(username);
            } else {
                return;
            }
        }
        
        
        
        while (true) {
            userChoice = "";
            System.out.println("1) Show tasks sorted by date");
            System.out.println("2) Show tasks sorted by importance");
            System.out.println("3) Add new task");
            System.out.println("4) Save and exit");
            System.out.print("Your choice: ");
            userChoice = keyboard.nextLine();
            newTodoItemAsCSV = "";
            String userImportanceChoice = "";
            String userDate = "";
            Date userDateTester;
            String userDescription = "";
            
            if (userChoice.equals("1")) {
                
                System.out.println(userTodoList.getAsDateSortedString());
                
            } else if (userChoice.equals("2")) {
                System.out.println(userTodoList.getAsImportanceSortedString());
                
            } else if (userChoice.equals("3")) {
                System.out.print("Enter a date (YYYY-MM-DD): ");
                userDate = keyboard.nextLine();               
                
                try {
                    userDateTester = Date.fromYYYYMMDDDashString(userDate);
                } catch (IllegalArgumentException e) {
                    System.out.println("Bad Date. Please enter a valid date");
                    continue;
                }               
                if (userDate.contains(",")) {
                    System.out.println("No commas allowed");
                    continue;
                }
                
                userDate = userDateTester.getAsYYYYMMDD();
                
                System.out.print("Enter an importance (HIGH, MEDIUM, LOW): ");
                userImportanceChoice = keyboard.nextLine();
                if (userImportanceChoice.equals("HIGH") || userImportanceChoice.equals("MEDIUM") || userImportanceChoice.equals("LOW")) {                    
                } else {                   
                    System.out.println("Bad importance choice");
                    continue;
                }        
                                
                System.out.print("Enter a short description (no commas): ");
                userDescription = keyboard.nextLine();
                if (userDescription.contains(",")) {
                    System.out.println("No commas allowed");
                    continue;
                }
                              
                newTodoItemAsCSV = userDate + "," + userDescription + "," + userImportanceChoice;         
                userTodoList.addTask(TodoItem.buildFromCSV(newTodoItemAsCSV));
                
            } else if (userChoice.equals("4")) {
                userTodoList.finalize();
                break;               
            } else {
                System.out.println("Please choose a valid option");
            }
        }
        
        
        
        
            
        
        
    }

}
