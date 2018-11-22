import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Введіть шлях до файлу");
        Scanner s = new Scanner(System.in);
        String path = s.nextLine();


        double[] list = readNumbersFromFile(path);
        double result = 0.0;
        if (list != null) {
            result = calcEntropySimple(list);
        }
        try {
            Files.write(Paths.get(path), String.valueOf(result).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }


    private static double calcEntropySimple(double[] list){
        // Calculate entropy
        double entropy = 0;
        for (int i = 0; i < list.length; i++) {
            if(Double.isNaN(list[i])){
                list[i] = 0;
            }
            if (list[i] > 0) {
                entropy -= list[i] * log2(list[i]);
            }
        }
        return entropy;
    }

    private static double log2 (double x) {
        return (Math.log(x)/Math.log(2)+1e-10);
    }


    private  static double[] readNumbersFromFile(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(path));
        List<Double> list = new ArrayList<>();
        while(in.hasNext()) {
            list.add(Double.parseDouble(in.next()));
        }
        double[] resultArray = new double[list.size()];
        for (int i = 0; i<list.size(); i++) {
            resultArray[i] = list.get(i);
        }
        return resultArray;
    }
}
