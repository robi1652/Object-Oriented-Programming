import java.util.Comparator;

/**
 * This is the Date Comparator
 * This comparator is used in the TodoList class to sort a user's Todo List by the date of each Todo Item
 * The earlier the date, the higher up on the TodoList it will be sorted to 
 * 
 * @author Blake
 *
 */
public class DateComparator implements Comparator<TodoItem> {

    /** 
     * This is the overridden compare method
     * This compares the two TodoItems by their dates. Whichever date came first will be placed ahead of the other date in the sort.
     * In terms of the TodoList, it will typically means which future date will come up first
     * 
     * @param todoItemOne is the first TodoItem to be compared 
     * @param todoItemTwo is the second TodoItem to be compared
     * @return is an integer that determines which TodoItem comes first, chronologically by date
     * 1 means todoItemOne's date comes first
     * -1 means todoItemTwo's date comes first
     * 0 means both TodoItems share the same date
     * 
     */
    @Override
    public int compare(TodoItem todoItemOne, TodoItem todoItemTwo)  {
        Date theFirstDate = todoItemOne.getDate();
        Date theSecondDate = todoItemTwo.getDate();
        int dateOneAsInt = Integer.parseInt(theFirstDate.getAsYYYYMMDD());
        int dateTwoAsInt = Integer.parseInt(theSecondDate.getAsYYYYMMDD());
        
        if (dateOneAsInt < dateTwoAsInt) {
            return -1;
        } else if (dateOneAsInt > dateTwoAsInt) {
            return 1;
        } else {
            return 0;
        }
    }
}
