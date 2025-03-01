import javax.swing.JOptionPane;

public class Calculator {
    public static void main(String[] args) {

        String strNum1, strNum2;
        double num1, num2;
        
        strNum1 = JOptionPane.showInputDialog(null, "Please input the first number: ", "Input the first number", JOptionPane.INFORMATION_MESSAGE);
        strNum2 = JOptionPane.showInputDialog(null, "Please input the second number: ", "Input the second number", JOptionPane.INFORMATION_MESSAGE);
        
        num1 = Double.parseDouble(strNum1);
        num2 = Double.parseDouble(strNum2);
        
        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;
        String quotient = (num2 != 0) ? String.format("%.2f", num1 / num2) : "undefined (cannot divide by zero)";
        
        String result = "Results:\n";
        result += "Sum: " + sum + "\n";
        result += "Difference: " + difference + "\n";
        result += "Product: " + product + "\n";
        result += "Quotient: " + quotient;
        
        JOptionPane.showMessageDialog(null, result, "Calculator Results", JOptionPane.INFORMATION_MESSAGE);
        
        System.exit(0);
    }
}