
/**
 * This is the TodoItem class
 * This class creates a TodoItem, which represents a task. TodoItems are to be stored in the ArrayList created in the TodoList class
 * A TodoItem object has a Date, an Importance, and a Description
 * 
 * @author Blake
 *
 */
public class TodoItem {
    
    /**
     * This is the Date object, which is the date of the TodoItem 
     */
    private Date date;
    /**
     * This is a string containing the description of the TodoItem
     */
    private String description;
    /**
     * This is an importance enum representing the importance level of the TodoItem
     */
    private Importance importanceLevel;
    
    /**
     * This is the constructor for the TodoItem class
     * 
     * @param date is the date on which the TodoItem/"task" takes place
     * @param description is the description of the TodoItem/"task"
     * @param importanceLevel is the level of importance of the TodoItem/"task"
     */
    public TodoItem(Date date, String description, Importance importanceLevel) {
        this.date = date;
        this.description = description;
        this.importanceLevel = importanceLevel;
    }
    
    /**
     * This is the buildFromCSV method
     * This is a static factory method that takes a Comma Separated Value string in the format of 
     * Date(YYYYMMDD),Description,Importance. It splits on the commas and assigns each index of the array created by the split to its
     * rightful place in a new TodoItem
     * 
     * @param theTodoString the CSV string from which the new TodoItem is built
     * @return the newly built TodoItem
     */
    public static TodoItem buildFromCSV(String theTodoString) {
        String[] parts;     
        Date theDate;
        String theDescription;
        Importance theImportance;
        
        parts = theTodoString.split(",");   

        theDate = Date.fromYYYYMMDDString(parts[0]);

        theDescription = parts[1];
        
        if (parts[2].equals("HIGH")) {
            theImportance = Importance.HIGH;
        } else if (parts[2].equals("MEDIUM")) {
            theImportance = Importance.MEDIUM;
        } else if (parts[2].equals("LOW")) {
            theImportance = Importance.LOW;
        } else {
            throw new IllegalArgumentException();
        }
        
        
        return new TodoItem(theDate, theDescription, theImportance);    
    }
    
    /**
     * This is the getAsCSV method
     * This method is called on a TodoItem, and creates a Comma Separated Value string using the date's getAsYYYYMMDD method and 
     * concatenating the description and importance level
     * 
     * @return the newly created CSV string representing a TodoItem
     */
    public String getAsCSV() {
        String dateAsString = date.getAsYYYYMMDD();     
        return dateAsString + "," + description + "," + importanceLevel;        
    }
    
    /**
     * This is the getDate method
     * It is a simple getter method, returning the date of a TodoItem
     * 
     * @return the date object of the TodoItem
     */
    public Date getDate() {
        return date;    
    }
    
    /**
     * This is the getImportanceLevel method
     * This is a simple getter method, returning the Importance level of the TodoItem
     * 
     * @return the Importance enum of the TodoItem, either LOW, MEDIUM, or HIGH
     */
    public Importance getImportanceLevel() {
        return importanceLevel;
    }
    
    /**
     * This is the getDescription method
     * This is a simple getter method, returning a string of the Description of the TodoItem
     * 
     * @return the string of the Description for the TodoItem
     */
    public String getDescription() {
        return description;
    }

}
