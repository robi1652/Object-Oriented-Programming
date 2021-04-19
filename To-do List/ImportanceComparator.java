import java.util.Comparator;

/**
 * This is the Importance Comparator
 * This comparator is used in the TodoList class to sort a user's TodoList by the importance of each TodoItem
 * Items with HIGH importance come first, MEDIUMs second, and LOWs last after the sort
 * 
 * @author Blake
 *
 */
public class ImportanceComparator implements Comparator<TodoItem> {
    
    /** 
     * This is the overridden compare method
     * This compares the two TodoItems by their Importances, and it places the higher importance item first
     * It uses the getImportanceNumber method in the Importance class to help it determine which Importance/TodoItem is more important
     * 
     * @param firstTodoItem is the first TodoItem to be compared
     * @param secondTodoItem is the second TodoItem to be compared
     * @return is an integer that determines which TodoItem is more important (and is to be moved ahead of the other TodoItem in the sort)
     * returning 1 means the secondTodoItem is more important
     * returning -1 means the firstTodoItem is more important
     * returning 0 means both TodoItems have the same importance
     * 
     */
    @Override
    public int compare(TodoItem firstTodoItem, TodoItem secondTodoItem) {
        Importance firstImportance = firstTodoItem.getImportanceLevel();
        Importance secondImportance = secondTodoItem.getImportanceLevel();
        
        int firstImportanceValue = firstImportance.getImportanceNumber();
        int secondImportanceValue = secondImportance.getImportanceNumber();
        
        
        
        if (firstImportanceValue > secondImportanceValue) {
            return -1;
        } else if (firstImportanceValue < secondImportanceValue) {
            return 1;
        } else {
            return 0;
        }
        
    }

}
