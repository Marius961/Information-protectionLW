//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Введіть шлях до файлу");
        Scanner s = new Scanner(System.in);
        String path = s.nextLine();
        count(readFile(path));
    }

    private static String readFile(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(path));
        StringBuilder sb = new StringBuilder();

        while(in.hasNext()) {
            sb.append(in.nextLine()).append(" ");
        }

        return sb.toString();
    }

    private static void count(String fileText) {
        int words = 0;
        int space = 0;
        int charCounter = 0;

        for(int i = 0; i < fileText.length(); ++i) {
            if (fileText.charAt(i) == ' ') {
                ++space;
                if (charCounter != 0) {
                    ++words;
                    charCounter = 0;
                }
            } else {
                ++charCounter;
                if (i == fileText.length() - 1) {
                    ++words;
                }
            }
        }

        System.out.println("Слів:" + words);
        System.out.println("Пробілів: " + space);
    }
}
