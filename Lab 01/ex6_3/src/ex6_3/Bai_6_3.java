package ex6_3;
import java.util.Scanner;

public class Bai_6_3 {
    public static void main(String args[]) {
        Scanner keyboard = new Scanner(System.in);
        int n;
        System.out.print("Input n: ");
        n = keyboard.nextInt();
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n - i; j++){
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        keyboard.close();
    }
}
