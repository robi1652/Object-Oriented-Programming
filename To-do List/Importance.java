
/**
 * This is the Importance enumeration
 * Importances are used in a TodoItem, to determine which level of importance the TodoItem has for the user
 * 
 * The 3 importances are LOW, MEDIUM, and HIGH, and the weight of each is represented by an integer
 * The higher the integer, the more important the TodoItem/Importance
 * 
 * The 2 variables are the String that is the Importance, and the integer representing the weight of the importance/TodoItem.
 * 
 * @author Blake
 *
 */
public enum Importance {
    /**
     * This is the LOW enum. It is the lowest valued Importance
     */
    LOW("LOW", 0),
    /**
     * This is the MEDIUM enum. It is the middle valued Importance
     */
    MEDIUM("MEDIUM", 1),
    /**
     * This is the HIGH enum. It is the highest valued Importance
     */
    HIGH("HIGH", 2);
    
    /**
     * This is the String representing the text of each enum 
     */
    private String importanceAsString;
    /**
     * This is an int representing the weight of each Importance enum. The higher the int, the more important the enum
     */
    private int importanceAsInt;
    
    /**
     * This is the constructor for Importance
     * It is private, because the only 3 Importance enums needed are LOW, MEDIUM, and High
     * 
     * @param importanceWord is the string of the Importance, either LOW, MEDIUM, or HIGH
     * @param importanceAsInt is the integer representing the weight of the Importance. The higher the int, the more importance the Importance/TodoItem
     */
    private Importance(String importanceWord, int importanceAsInt) {
        importanceAsString = importanceWord;
        this.importanceAsInt = importanceAsInt;
    }
    
    /**
     * This is the getImportanceNumber method
     * This method returns the integer that represents the weight of each importance
     * It is used in ImportanceComparator to assist in sorting the TodoList by Importance
     * 
     * @return the integer representing the weight of the importance
     */
    public int getImportanceNumber() {
        return importanceAsInt;
    }
}
