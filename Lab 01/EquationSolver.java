import javax.swing.JOptionPane;

public class EquationSolver {

    public static void main(String[] args) {
        String[] options = {"First-degree equation (linear equation)","System of first-degree equations","Second-degree equation (quadratic equation)"};
        int choice = JOptionPane.showOptionDialog(null,"Choose the equation to solve","Equation Solver",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null, options, options[0]);

        switch (choice) {
            case 0: solveLinearEquation();
                break;
            case 1: solveLinearSystem();
                break;
            case 2: solveQuadraticEquation();
                break;
            default:
                System.exit(0);
        }
    }

    public static void solveLinearEquation() {
        String strA = JOptionPane.showInputDialog(null, "Enter coefficient a for the equation ax + b = 0:", "First-degree Equation", JOptionPane.INFORMATION_MESSAGE);
        String strB = JOptionPane.showInputDialog(null, "Enter constant b for the equation ax + b = 0:", "First-degree Equation", JOptionPane.INFORMATION_MESSAGE);

        double a = Double.parseDouble(strA);
        double b = Double.parseDouble(strB);

        if (a == 0) {
            JOptionPane.showMessageDialog(null, "The value of a cannot be zero in a linear equation!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } else {
            double x = -b / a;
            JOptionPane.showMessageDialog(null, "The solution is x = " + x, "Solution", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void solveLinearSystem() {
        
        double a11 = Double.parseDouble(JOptionPane.showInputDialog("Enter coefficient a11 for the first equation:"));
        double a12 = Double.parseDouble(JOptionPane.showInputDialog("Enter coefficient a12 for the first equation:"));
        double b1 = Double.parseDouble(JOptionPane.showInputDialog("Enter constant b1 for the first equation:"));
        
        double a21 = Double.parseDouble(JOptionPane.showInputDialog("Enter coefficient a21 for the second equation:"));
        double a22 = Double.parseDouble(JOptionPane.showInputDialog("Enter coefficient a22 for the second equation:"));
        double b2 = Double.parseDouble(JOptionPane.showInputDialog("Enter constant b2 for the second equation:"));
        
        double D = a11 * a22 - a12 * a21;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;

        if (D == 0) {
            if (D1 == 0 && D2 == 0) {
                JOptionPane.showMessageDialog(null, "The system has infinitely many solutions.", "Solution", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "The system has no solution.", "Solution", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            double x1 = D1 / D;
            double x2 = D2 / D;
            JOptionPane.showMessageDialog(null, "The solution is:\n x1 = " + x1 + "\n x2 = " + x2, "Solution", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void solveQuadraticEquation() {
        String strA = JOptionPane.showInputDialog("Enter coefficient a for the quadratic equation (ax^2 + bx + c = 0):");
        String strB = JOptionPane.showInputDialog("Enter coefficient b for the quadratic equation (ax^2 + bx + c = 0):");
        String strC = JOptionPane.showInputDialog("Enter coefficient c for the quadratic equation (ax^2 + bx + c = 0):");

        double a = Double.parseDouble(strA);
        double b = Double.parseDouble(strB);
        double c = Double.parseDouble(strC);

        if (a == 0) {
            JOptionPane.showMessageDialog(null, "The value of a cannot be zero in a quadratic equation!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } else {
            double discriminant = b * b - 4 * a * c;

            if (discriminant > 0) {

                double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                JOptionPane.showMessageDialog(null, "The equation has two real roots:\n x1 = " + root1 + "\n x2 = " + root2, "Solution", JOptionPane.INFORMATION_MESSAGE);
            } else if (discriminant == 0) {

                double root = -b / (2 * a);
                JOptionPane.showMessageDialog(null, "The equation has one real root: x = " + root, "Solution", JOptionPane.INFORMATION_MESSAGE);
            } else {

                JOptionPane.showMessageDialog(null, "The equation has no real roots.", "Solution", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}