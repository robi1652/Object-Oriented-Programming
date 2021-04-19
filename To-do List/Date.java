import java.util.Comparator;

/**
 * This is the Date class
 * This creates a Date object which is used when building a TodoItem
 * The 3 instance variables are Month, Day and Year
 * All 3 are represented by integers
 * 
 * @author Blake
 *
 */
public class Date implements Comparable<Date> {

    /**
     * This is an integer representing the Month of the date object
     */
    private int month;
    /**
     * This is an integer representing the Day of the date object
     */
    private int day;
    /**
     * This is an integer representing the Year of the date object
     */
    private int year;
    

    /**
     * This is the constructor for the Date class
     * 
     * 
     * @param year: is the integer representing the Year of the date object
     * @param month: is the integer representing the Month of the date object
     * @param day: is the integer representing the Day object
     */
    public Date(int year, int month, int day) {
        this.month = month;
        this.day = day;
        this.year = year;
        
    }
      
    /** 
     * This is the toString method 
     * This method is used when printing the date to the user when the user sorts their TodoList
     * It is shown as month/day/year
     * 
     * @return a string representing the Date in the format month/day/year
     */
    public String toString() {
        return "" + month + "/" + day + "/" + year;
    }
    
    /**
     * This is the fromYYYYMMDDString method
     * This is a static factory method that takes a string representing a date in the format of YYYYMMDD 
     * and creates a new Date object from that string
     * 
     * It is used when reading a previously saved file, as all dates in those files are saved in YYYYMMDD 
     * format in their CSV
     * 
     * This method tests for the following errors:
     * --- It makes sure the string representing the date contains only 8 Characters, which is required for a YYYYMMDD format date
     * --- It makes sure the string representing the date contains only integers
     * --- It makes sure the date is a real, valid date
     * 
     * @param theDateAsString is the YYYYMMDD string to be turned into a date object
     * @return The newly created Date object
     */
    public static Date fromYYYYMMDDString(String theDateAsString) {
        String[] parts = new String[3];
        int month;
        int day;
        int year;
        int[] dateAsArray = new int[3];
        
        if (theDateAsString.length() != 8) {
            throw new IllegalArgumentException();
            
        }
        

        
        parts[0] = "" + theDateAsString.charAt(0) + theDateAsString.charAt(1) + theDateAsString.charAt(2) + theDateAsString.charAt(3);
        parts[1] = "" + theDateAsString.charAt(4) + theDateAsString.charAt(5);
        parts[2] = "" + theDateAsString.charAt(6) + theDateAsString.charAt(7);
        
        try {
            month = Integer.parseInt(parts[1]);
            day = Integer.parseInt(parts[2]);
            year = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        
        dateAsArray[0] = year;
        dateAsArray[1] = month;
        dateAsArray[2] = day;
        

        
        if (!isValidDate(dateAsArray)) {
            throw new IllegalArgumentException();
        }
        
        
        return new Date(year, month, day);   
    }
   
    /**
     * This is the fromYYYYMMDDDashString
     * This is a static factory method that takes a string representing a date in the format of YYYY-MM-DD
     * and creates a new Date object from that string
     * 
     * It is used when the user wants to create new TodoItems 
     * The user types their date in the YYYY-MM-DD format, and this method 
     * turns that into a new date
     * 
     * This method tests for the following errors:
     * --- It makes sure the string representing the date contains only 10 characters, which is required for a YYYY-MM-DD format date
     * --- It makes sure the user has typed only 2 dashes
     * --- It makes sure the string representing the date contains only integers
     * --- It makes sure the date is a real, valid date
     * 
     * 
     * @param theDateAsString is the YYYY-MM-DD string to be turned into a date object
     * @return The newly created Date object
     */
    public static Date fromYYYYMMDDDashString(String theDateAsString) {
        String[] parts = new String[3];
        int month;
        int day;
        int year;
        int[] dateAsArray = new int[3];
        
        if (theDateAsString.length() != 10) {
            throw new IllegalArgumentException();
        }
        
        parts = theDateAsString.split("-");
        
        if (parts.length != 3) {
            throw new IllegalArgumentException();
        }
        
        if (parts[0].length() != 4 || parts[1].length() != 2 || parts[2].length() != 2) {
            throw new IllegalArgumentException();
        }
        
        try {
            month = Integer.parseInt(parts[1]);
            day = Integer.parseInt(parts[2]);
            year = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        
        dateAsArray[0] = year;
        dateAsArray[1] = month;
        dateAsArray[2] = day;
        
        
        if (!isValidDate(dateAsArray)) {
            throw new IllegalArgumentException();
        }
        
        return new Date(year, month, day);   
    }
    
    /**
     * This is the getAsYYYYMMDD method
     * This method is called on a date object and returns a string in the YYYYMMDD format representing that date
     * 
     * @return the YYYYMMDD string representing the date
     */
    public String getAsYYYYMMDD() {
        String returnString = ""+year;
        if (month < 10) {
            returnString = returnString+"0"+month;
        } else {
            // 2 digit month
            returnString = returnString+month;
        }
        
        if (day < 10) {
            returnString = returnString+"0"+day;
        } else {
            returnString = returnString+day;
        }
        
        return returnString;
        
    }
    
    /**
     * This is the equals method
     * This checks to see if two date objects are equal, or "if the dates are the same"
     * 
     * @param other is the other date to compare to the date on which the method was called. 
     * @return a boolean which states whether or not the dates are equal. True means they are equal, false means they are not equal
     */
    public boolean equals(Date other) {            
        if (this.year == other.year && this.month == other.month && this.day == other.day) {
            return true;
        } else {
            return false;
        }
    }
                  
    /**
     * This is the isValidDate method
     * This is a private static method that checks if a Date is a valid, real date
     * For example, 04/25/2019 is a real date, whereas 03/54/2019 is not a real date
     * The date is represented by an integer array
     * 
     * @param date is an integer array representing a date. date[0] is the year, date[1] is the month, and date[2] is the day
     * @return a boolean which states whether or not the date is real/legit. True means it is legit, and false means it's not legit
     */
    private static boolean isValidDate(int[] date) {

        boolean validDateVerdict = false;
        int year = date[0];
        int month = date[1];
        int day = date[2];
        
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)==true) {
            daysInMonth[1] = 29;
        } else {
            daysInMonth[1] = 28;
        }
        
        if (isLeapYear(year)==false) {
            if (month>0 && month<13 && day>0 && day< 32 && year>0) {
                if (day <= daysInMonth[month-1]) {
                    validDateVerdict = true;
                } else {
                    validDateVerdict = false;
                }
            } else {
                validDateVerdict = false;
            }
        } else if (isLeapYear(year)==true) {
            if (month>0 && month<13 && day>0 && day< 32 && year>0) {
                if (day <= daysInMonth[month-1]) {
                    validDateVerdict = true;
                } else {
                    validDateVerdict = false;
                }
            }  
        }
        
    return validDateVerdict;
        
    }
    
    /**
     * This is the isLeapYear method
     * This is a private static method that checks if the Date is within a leap year
     * This is used in isValidDate as the validity of February 29th changes depending on if the year is a leap year
     * 
     * @param year is an integer representing the year to be checked for being a leap year
     * @return a boolean which states whether or not the year is a leap year
     */
    private static boolean isLeapYear(int year) {
            
            boolean leapYearVerdict;
            
            if (year % 400 == 0) {
                leapYearVerdict = true;
            } else if ((year % 100 != 0) && (year % 4 == 0)) {
                leapYearVerdict = true;
            } else {
                leapYearVerdict = false;
            }
            
            return leapYearVerdict;
            
        }
   
    /** 
     * This is the compareTo method for the Date class
     * Because Date implements Comparable, this method overrides the default compareTo method
     * 
     * This method can be used to determine the ordering of 2 dates, which is useful to sort the dates
     * 
     * @param other is the other date object to compare to the current date object
     * @return an integer that states whether or not other is greater that (chronologically following) the current date
     * -1 means it IS later, 1 means it's sooner, and 0 means they're the same date
     */
    @Override
    public int compareTo(Date other) {
        int firstDateYYYYMMDD = Integer.parseInt("" + year + month + day);
        int otherDateYYYYMMDD = Integer.parseInt("" + other.year + other.month + other.day);
        
        if (firstDateYYYYMMDD < otherDateYYYYMMDD) {
            return -1;
        } else if (firstDateYYYYMMDD > otherDateYYYYMMDD) {
            return 1;
        } else {
            return 0;
        }
            
    }

    
    
    
    
    
    
    
}
