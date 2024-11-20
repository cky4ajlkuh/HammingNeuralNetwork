package hemming;

import java.util.Arrays;

import static util.Helper.*;

/**
 * Класс, реализующий вычисление Hamming neural network
 */
public class Hemming {

    private final double[][] x;

    private final double[] y;

    /**
     * Конструктор класса
     *
     * @param x тренировочная выборка изображений (изображения в бинарном виде)
     * @param y тестовое изображение
     */
    public Hemming(double[][] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Метод, реализующий вычисления нейронной сети
     */
    public void calculate() {
        double[][] w_maxnet = getWeightsMaxnet(x);
        double[] y_i = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y_i[i] = 1 - distanceOfHemming(x[i], y) / y.length;
        }
        System.out.println("Distance of Hemming:\n" + Arrays.toString(y_i));
        System.out.println("Iteration process:");
        double[] array = y_i;
        while (isLastIteration(array)) {
            double[] element = new double[array.length];
            for (int i = 0; i < w_maxnet.length; i++) {
                double w = 0;
                for (int s = 0; s < w_maxnet[0].length; s++) {
                        w += w_maxnet[i][s] ;
                }
                element[i] = array[i] * w;
            }
            array = element;
        }
    }
}
