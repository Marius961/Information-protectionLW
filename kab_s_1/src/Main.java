//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Введіть шлях до файлу");
        Scanner s = new Scanner(System.in);
        String path = s.nextLine();
        String fileContent = readFile(path).toLowerCase();
        StringBuilder result = new StringBuilder();
        ArrayList<Character> list = new ArrayList<Character>(Arrays.asList(
                'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж', 'з', 'и', 'і', 'ї',
                'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х',
                'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я'
        ));
        System.out.println("Вміст файлу: \n" +fileContent);

        for (char c : fileContent.toCharArray()) {
            if (list.contains(c)) {
                result.append(list.indexOf(c));
            } else {
                result.append(c);
            }
        }
        System.out.println("Перетворений вміст файлу: \n" + result);
    }

    private static String readFile(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(path));
        StringBuilder sb = new StringBuilder();

        while(in.hasNext()) {
            sb.append(in.nextLine()).append(" ");
        }

        return sb.toString();
    }

}
