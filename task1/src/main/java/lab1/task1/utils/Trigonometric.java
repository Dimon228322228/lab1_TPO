package lab1.task1.utils;

public class Trigonometric {

  /**
   * Реализация разложения sec(x) в степенной ряд.
   *
   * @param x аргумент функции 
   * @return значение sec(x)
   */
  public static double sec(double x) {
    // Проверка специальных значений
    if (Double.isInfinite(x)) {
      return Double.NaN;
    }
    if (Double.isNaN(x)) {
      return Double.NaN;
    }
    // Приведение аргумента к основному периоду [-π/2, π/2]
    // sec(x) имеет период 2π, поэтому можно сдвигать на 2π
    while (Math.abs(x) > Math.PI / 2) {
      if (x > 0) {
        x -= Math.PI;
      } else {
        x += Math.PI;
      }
    }
    
    // Secant function is undefined when cos(x) = 0, i.e. when x = ±π/2 + kπ
    // We check for these points and return NaN
    if (Math.abs(Math.cos(x)) < 1.0e-10) {
      return Double.NaN;
    }

    // Если аргумент равен нулю, то результатом будет 1
    if (x == 0d) {
      return 1.0;
    }

    // Вычисляем sec(x) с помощью редукции аргумента и ряда
    return secReduction(x);
  }

  private static double secReduction(double x) {
    // Рекурсивно уменьшаем аргумент до малого значения
    double threshold = 0.1;
    if (Math.abs(x) <= threshold) {
      return secSeriesSmall(x);
    }
    // Делим аргумент пополам
    double half = x / 2;
    double secHalf = secReduction(half);
    // Формула удвоения: sec(x) = secHalf^2 / (2 - secHalf^2)
    double secHalfSq = secHalf * secHalf;
    return secHalfSq / (2 - secHalfSq);
  }

  private static double secSeriesSmall(double x) {
    // Ряд секанса для малого x (до 0.1) с использованием чисел Эйлера
    // Коэффициенты Эйлера E_{2k} для k = 0..10
    double[] euler = {
      1,                     // E0
      1,                     // E2
      5,                     // E4
      61,                    // E6
      1385,                  // E8
      50521,                 // E10
      2702765,               // E12
      199360981,             // E14
      19391512145.0,         // E16
      2404879675441.0,       // E18
      370371188237525.0      // E20
    };
    // Факториалы (2k)! для k = 0..10
    double[] fact = new double[euler.length];
    fact[0] = 1.0;
    for (int k = 1; k < fact.length; k++) {
      fact[k] = fact[k - 1] * (2 * k - 1) * (2 * k);
    }

    double x2 = x * x;
    double term = 1.0; // x^(2k) для k=0
    double sum = euler[0] * term / fact[0]; // k=0

    for (int k = 1; k < euler.length; k++) {
      term *= x2;
      double contribution = euler[k] * term / fact[k];
      sum += contribution;
      // Если вклад стал пренебрежимо мал, прерываемся
      if (Math.abs(contribution) < 1.0e-15) {
        break;
      }
    }

    return sum;
  }
}