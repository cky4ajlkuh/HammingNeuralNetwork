package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Класс помощник для чтения данных из директории
 */
public class Reader {
    /**
     * Преобразует все изображения, находящиеся в текущей директории, в список бинарных массивов
     *
     * @param path путь до указанной директории
     * @return массив с изображениями, представленными в виде массива
     * @throws IOException может возникнуть исключение, если файлов в директории нет
     */
    public static double[][] readImages(String path) throws IOException {
        File directory = new File(path);
        double[][] images = new double[Objects.requireNonNull(directory.listFiles()).length][];
        for (int i = 0; i < images.length; i++) {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(directory.listFiles())[i]);
            images[i] = getArray(image);
        }
        return images;
    }

    /**
     * Преобразует входное изображение в массив бинарный массив
     *
     * @param path путь до указанного изображения
     * @return преобразованное в массив double изображение
     * @throws IOException может возникнуть исключение, если файлов в директории нет
     */
    public static double[] readImage(String path) throws IOException {
        File image = new File(path);
        return getArray(ImageIO.read(image));
    }

    /**
     * Преобразует входное изображение в массив double (представляя его в бинарном/черно-белом виде)
     *
     * @param image изображение, загруженное в приложение
     * @return массив, содержащий или -1 или 1, длиной в height * weight изображения
     */
    private static double[] getArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        double[] result = new double[width * height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                if (r + g + b > ((255 + 100) / 2) * 3) {
                    result[i * height + j] = -1;
                } else {
                    result[i * height + j] = 1;
                }
            }
        }
        return result;
    }
}


