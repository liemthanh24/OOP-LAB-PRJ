package ex6_6;

import java.util.Scanner;

public class Bai6_6{
    private static void inputMatrix(Scanner input, int[][] matrix, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = input.nextInt();
            }}}
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhap kich thuoc ma tran (m x n): ");
        int m = input.nextInt();
        int n = input.nextInt();
        int[][] matrix1 = new int[m][n];
        int[][] matrix2 = new int[m][n];
        int[][] sumMatrix = new int[m][n];

        System.out.println("Nhap ma tran thu nhat:");
        inputMatrix(input, matrix1, m, n);
        System.out.println("Nhap ma tran thu hai:");
        inputMatrix(input, matrix2, m, n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        System.out.println("Ma tran tong:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(sumMatrix[i][j] + " ");
            }
            System.out.println();
        }
        input.close();} }
