//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Введіть шлях до файлу");
        Scanner s = new Scanner(System.in);
        String path = s.nextLine();
        List<String> words = readFile(path);
        double pairWords = 0;
        for (String word : words) {
            if ((word.length() % 2) == 0) {
                pairWords++;
            }
        }
        double result = pairWords / words.size();
        try {
            Files.write(Paths.get(path), String.valueOf(result).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Ймовірність появи слів з парною кількістю символів:  " + result);
    }

    private static List<String> readFile(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(path));
        List<String> words = new ArrayList<>();

        while(in.hasNext()) {
            words.add(in.next());
        }

        return words;
    }
}

