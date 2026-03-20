package lab1.task1;

import lab1.task1.utils.Trigonometric;

public class App {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Usage: java App <x>");
      return;
    }

    try {
      double x = Double.parseDouble(args[0]);
      double result = Trigonometric.sec(x);
      if (Double.isNaN(result)) {
        System.out.println("Error: x must be in valid domain for sec(x).");
        return;
      }

      System.out.printf("sec(%.2f) = %.6f%n", x, result);
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please enter a valid number for x and an integer for n.");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}