package lab1.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Реализация блочной сортировки для чисел в произвольном диапазоне.
 * Содержит логирование для отслеживания характерных точек алгоритма.
 */
public class BucketSort {
    private final List<String> log = new ArrayList<>();

    /**
     * Возвращает копию лога последней сортировки.
     */
    public List<String> getLog() {
        return new ArrayList<>(log);
    }

    /**
     * Сортирует массив методом блочной сортировки.
     *
     * @param array входной массив (любые числа)
     * @return новый отсортированный массив
     */
    public double[] sort(double[] array) {
        log.clear(); // очищаем лог перед новым запуском
        log.add("A"); // Точка A: начало

        if (array == null || array.length == 0) {
            log.add("F"); // Точка F: конец (пустой массив)
            return array;
        }

        int n = array.length;

        // Находим минимальное, максимальное значения и максимальный модуль
        double min = array[0];
        double max = array[0];
        double maxAbs = Math.abs(array[0]);
        for (double num : array) {
            if (num < min) min = num;
            if (num > max) max = num;
            double absNum = Math.abs(num);
            if (absNum > maxAbs) maxAbs = absNum;
        }

        // Если все элементы равны, массив уже отсортирован
        if (min == max) {
            log.add("B: создано 1 корзин (все элементы равны)");
            log.add("C: все элементы -> корзина 0");
            log.add("D: добавлены элементы из корзины 0: " + arrayToString(array));
            log.add("E");
            return array.clone();
        }

        // Точка B: создание корзин
        List<List<Double>> buckets = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<>());
        }
        log.add("B: создано " + n + " корзин");

        // Точка C: распределение элементов по корзинам
        double divisor = maxAbs + 1; // максимальный модуль + 1
        if (divisor == 0) {
            divisor = 1; // избегаем деления на ноль
        }
        for (double num : array) {
            double absNum = Math.abs(num);
            int bucketIndex = (int) (absNum * n / divisor);
            // Обработка граничных случаев
            if (bucketIndex >= n) {
                bucketIndex = n - 1;
            } else if (bucketIndex < 0) {
                bucketIndex = 0;
            }
            List<Double> bucket = buckets.get(bucketIndex);
            int pos = Collections.binarySearch(bucket, num);
            if (pos < 0) {
                pos = -pos - 1;
            }
            bucket.add(pos, num);
            log.add(String.format("C: элемент %.2f -> корзина %d", num, bucketIndex));
        }

        // Точка D: сборка отсортированного массива
        List<Double> resultList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Double> bucket = buckets.get(i);
            resultList.addAll(bucket);
            if (!bucket.isEmpty()) {
                log.add(String.format("D: добавлены элементы из корзины %d: %s", i, bucket));
            }
        }

        double[] result = new double[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }

        log.add("E"); // Точка F: конец
        return result;
    }

    private String arrayToString(double[] array) {
        List<String> parts = new ArrayList<>();
        for (double d : array) {
            parts.add(String.format("%.2f", d));
        }
        return "[" + String.join(", ", parts) + "]";
    }
}