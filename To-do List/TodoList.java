import java.util.List;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * This is the TodoList class 
 * The TodoList class creates an object that represents a Todo List, and is filled with TodoItems
 * The TodoItems are stored in an ArrayList called theTasks
 * Each TodoList is built using a username inputted by the user. If the user has already created a TodoList with their username and that TodoList (stored in a .txt file) is stored in the 
 * project folder, it will open that .txt file and build a TodoList from that
 * 
 * @author Blake
 *
 */
public class TodoList {
    /**
     * This is the ArrayList that embodies the TodoList. This ArrayList stores TodoItem objects
     */
    private ArrayList<TodoItem> theTasks;
    /**
     * This is the string containing the username inputted by the user. It is used to search for existing Files containing previously saved TodoLists, and it is used to build
     * new TodoLists, and those TodoLists are saved to files titled username+".txt"
     */
    private String username;
    
    /**
     * This is the constructor for the TodoList
     * It creates a new ArrayList to represent the user's TodoList
     * 
     * @param username is the user's inputted name to build the TodoList from. It is what is searched for to see if the File for that
     * TodoList already exists
     */
    public TodoList(String username) {
        theTasks = new ArrayList<TodoItem>();    
        this.username = username;
    }
    
    /**
     * This is the buildFromUsername method
     * This is a static factory method that takes the username inputted by the user and creates (returns) a new 
     * TodoList object.
     * 
     * It attempts to read in an existing file with the title username+".txt", and if it can't it throws a IllegalArgumentException,
     * prompting the user to create a new TodoList under their username.
     * 
     * @param username is the username by with the new TodoList will be built
     * @return is the newly built TodoList
     */
    public static TodoList buildFromUsername(String username) {
        Scanner fileScan;
        List<String> theLines;
        TodoList userTodoList = new TodoList(username);       
        
        try {
            theLines = Files.readAllLines(Paths.get(username + ".txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException();
         
        }    
        
        if (theLines.size() > 0) {
            for (int i = 0; i < theLines.size(); i++) {     
                userTodoList.addTask(TodoItem.buildFromCSV(theLines.get(i)));
            }
            return userTodoList;
        } else {
            return userTodoList;
        }
        
        
    }
    
    /**
     * This is the addTask method
     * This method simply adds a TodoItem (task) to the end of the theTasks ArrayList 
     *
     * @param itemToAdd is the TodoItem to add to theTasks
     */
    public void addTask(TodoItem itemToAdd) {
        theTasks.add(itemToAdd);
    }
    
    /**
     * This is the getAsDateSortedString method
     * This method returns a string that contains the entire theTasks ArrayList, with all the elements (TodoItem objects/"tasks") sorted
     * by the TodoItem's date, with the chronologically first dates being sorted to the top/front 
     * 
     * @return the string that contains the TodoItems in theTasks sorted by date
     */
    public String getAsDateSortedString() {
        String stringToReturn = "";
        
        if (theTasks.size() == 0) {
            return "\nThe Todo List is empty\n";
        }
        
        Collections.sort(theTasks, new DateComparator());
        
        for (int i = 0; i < theTasks.size(); i++) {
            TodoItem theCurrentItem = theTasks.get(i);
            Date currentDate = theTasks.get(i).getDate();
            Importance currentImportance = theTasks.get(i).getImportanceLevel();
            String currentDesc = theTasks.get(i).getDescription();
            
            stringToReturn = stringToReturn + "*\n   Date: " + currentDate + "\n   Importance: " + currentImportance + "\n   " + currentDesc + "\n\n\n";
        }
        
        return stringToReturn;
        
    }
    
    /**
     * This is the getAsImportanceSortedString method
     * This method returns a string that contains the entire theTasks ArrayList, with all the elemnts (TodoItem objects/"tasks") sorted 
     * by the TodoItem's Importance, with the more important items being sorted to the top/front
     * 
     * @return the string that contains the TodoItems in theTasks sorted by importance
     */
    public String getAsImportanceSortedString() {
        String stringToReturn = "";
        
        if (theTasks.size() == 0) {
            return "\nThe Todo List is empty\n";
        }
        
        Collections.sort(theTasks, new ImportanceComparator());
        
        
        
        for (int j = 0; j < theTasks.size(); j++) {
            TodoItem theCurrentItem = theTasks.get(j);
            Date currentDate = theTasks.get(j).getDate();
            Importance currentImportance = theTasks.get(j).getImportanceLevel();
            String currentDesc = theTasks.get(j).getDescription();
            
            stringToReturn = stringToReturn + "*\n   Date: " + currentDate + "\n   Importance: " + currentImportance + "\n   " + currentDesc + "\n\n\n";
        }
        
        return stringToReturn;
        
    }
    
    /** 
     * This is the finalize method
     * This method takes theTasks in its current state and turns each element into a CSV, and concatenates that CSV 
     * to stringToFinalize. Then, stringToFinalize is written to a new File, which is titled username+".txt"
     * 
     * --If the TodoList file under the inputted username already exists, then this method adds any new methods to the TodoList
     * created by buildFromUsername, and after putting together the string previously described, it will overwrite the file
     * 
     */
    public void finalize() {
        String stringToFinalize = "";
        for (int i = 0; i < theTasks.size(); i++) {
            stringToFinalize = stringToFinalize + theTasks.get(i).getAsCSV() + "\n";
        }
        
        try {
            Files.write(Paths.get(username + ".txt"), stringToFinalize.getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        
    }
    
    

}
