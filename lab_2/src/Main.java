import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Введіть шлях до файлу");
        Scanner s = new Scanner(System.in);
        String path = s.nextLine();
        double entropy = calcEntropy(calcProbability(readFile(path)));
        System.out.println("Ентропія файлу: " + entropy);
        try {
            Files.write(Paths.get(path), String.valueOf(entropy).getBytes());
            System.out.println("Дані успішно записані у файл");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static double calcEntropy(double[] list){

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


    private  static char[] readFile(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next()).append(" ");
        }
        return  sb.toString().toCharArray();
    }

    private static double[] calcProbability(char[] fileChars) {
        Map<Character, Double> counts = new HashMap<>();
        for (char c : fileChars) {
            if (!counts.containsKey(c)) {
                counts.put(c, 1.0);
            } else {
                counts.put(c, counts.get(c) + 1.0);
            }
        }
        double[] p = new double[counts.size()];
        int index = 0;
        for (char c : counts.keySet()) {
            p[index] = counts.get(c) / fileChars.length;
            index++;
        }
        return p;
    }
}
