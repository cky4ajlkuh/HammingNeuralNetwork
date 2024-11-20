package util;

import java.util.Arrays;

/**
 * Класс помощник, реализующий методы для работы с нейронной сетью
 */
public class Helper {

    /**
     * Метод, реализующий расчет матрицы весов
     *
     * @param x матрица исходных изображений
     * @return матрица весов
     */
    public static double[][] getWeightsMaxnet(double[][] x) {
        double[][] w = new double[x.length][x[0].length];
        int p = x.length;
        double coefficient = -Math.pow(p - 1, -1);
        for (int i = 0; i < w.length; i++) {
            for (int s = 0; s < w[0].length; s++) {
                if (i == s) {
                    w[i][s] = 1;
                } else {
                    w[i][s] = coefficient + rnd();
                }
            }
        }
        return w;
    }

    /**
     * Метод, возвращающий случайное число в заданном диапазоне
     *
     * @return случайное число
     */
    private static double rnd() {
        return (Math.random() * 0.09) + 0.01;
    }

    /**
     * Метод, применяющий функцию активации ко входному вектору
     *
     * @param x входной вектор
     * @return вектор, обработанный функцией активации
     */
    public static double[] functionOfActivation(double[] x) {
        double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            if (x[i] >= 0) {
                result[i] = x[i];
            } else {
                result[i] = 0;
            }
        }
        return result;
    }

    /**
     * Метод, проверяющий является ли данная итерация последней
     *
     * @param x вектор, полученный в ходе итерации
     * @return false, если не конец итерации, true - если необходимо определить класс победителя
     */
    public static boolean isLastIteration(double[] x) {
        System.out.println(Arrays.toString(x));
        double[] afterActivation = functionOfActivation(x);
        double counter = 0;
        for (double v : afterActivation) {
            if (v == 0 || Double.isNaN(v) || Double.isInfinite(v)) {
                counter++;
            }
        }
        if (counter < x.length - 1) {
            return true;
        } else {
            System.out.println(Arrays.toString(afterActivation));
            findWinner(x);
            return false;
        }
    }

    /**
     * Метод, реализующий расчет расстояния Хемминга
     *
     * @param x_t эталонный вектор
     * @param y   входной вектор
     * @return расстояние Хемминга между входным и эталонным вектором
     */
    public static double distanceOfHemming(double[] x_t, double[] y) {
        double distance = 0;
        if (x_t.length == 0 || y.length == 0) {
            return 0;
        }
        for (int i = 0; i < y.length; i++) {
            if (x_t[i] != y[i]) {
                distance++;
            }
        }
        return distance;
    }

    /**
     * Метод, реализующий поиск итогового класса
     *
     * @param x список весов
     */
    public static void findWinner(double[] x) {
        double minElement = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] < minElement) {
                minElement = x[i];
                index = i;
            }
        }
        Arrays.fill(x, 0);
        x[index] = 1;
        System.out.println("Итоговый класс:\n" + Arrays.toString(x));
    }
}
