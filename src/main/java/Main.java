import hemming.Hemming;
import util.Reader;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String path = "HammingNeuralNetwork\\src\\main\\resources\\times new roman\\";
        double[][] train = Reader.readImages(path + "regular");
        double[] test = Reader.readImage(path + "noize\\1.jpg");
        Hemming hemming = new Hemming(train, test);
        hemming.calculate();
    }
}
