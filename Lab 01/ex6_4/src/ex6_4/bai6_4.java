package ex6_4;
import java.util.Scanner;

public class bai6_4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String month;
        int year;
        
        while (true) {
            System.out.print("Month input: ");
            month = input.nextLine().trim();
            month = capitalizeFirstLetter(month);
            
            System.out.print("Year input: ");
            if (!input.hasNextInt()) {
                System.out.println("Year invalid!");
                input.next();
                continue;
            }
            year = input.nextInt();
            input.nextLine();
            
            if (year < 0) {
                System.out.println("Year invalid!");
                continue;
            }
            
            int monthNumber = parseMonth(month);
            if (monthNumber == -1) {
                System.out.println("Month invalid!");
                continue;
            }
            
            System.out.println("Number of days: " + calculateDays(monthNumber, year));
            break;
        }
        input.close();
    }
    
    private static int parseMonth(String month) {
        String[][] monthFormats = {
            {"January", "Jan.", "Jan", "1"},
            {"February", "Feb.", "Feb", "2"},
            {"March", "Mar.", "Mar", "3"},
            {"April", "Apr.", "Apr", "4"},
            {"May", "May", "5"},
            {"June", "Jun.", "Jun", "6"},
            {"July", "Jul.", "Jul", "7"},
            {"August", "Aug.", "Aug", "8"},
            {"September", "Sep.", "Sep", "9"},
            {"October", "Oct.", "Oct", "10"},
            {"November", "Nov.", "Nov", "11"},
            {"December", "Dec.", "Dec", "12"}
        };
        
        for (int i = 0; i < monthFormats.length; i++) {
            for (String format : monthFormats[i]) {
                if (month.equalsIgnoreCase(format)) {
                    return i + 1;
                }
            }
        }
        return -1;
    }
    
    private static int calculateDays(int month, int year) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 2 && isLeap(year)) {
            return 29;
        }
        return daysInMonth[month - 1];
    }
    
    private static boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    
    private static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() 
        + str.substring(1).toLowerCase();
    }
}
