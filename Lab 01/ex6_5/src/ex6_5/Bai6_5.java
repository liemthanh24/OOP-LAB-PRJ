package ex6_5;

import java.util.Arrays;
import java.util.Scanner;

public class Bai6_5{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("So phan tu mang: ");
        int size = input.nextInt();
        int[] numbers = new int[size];
        
        System.out.println("Nhap phan tu cua mang:");
        for (int i = 0; i < size; i++) {
            numbers[i] = input.nextInt();
        }
        Arrays.sort(numbers);
        int sum = calculateSum(numbers);
        double average = (double) sum / numbers.length;
        System.out.println("Sort: " + Arrays.toString(numbers));
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
        input.close();
    }
    private static int calculateSum(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }
}
