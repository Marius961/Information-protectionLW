import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введіть шлях до файлу який бажаєте зашифрувати");
        Scanner s = new Scanner(System.in);
        String path = s.nextLine();
        File file = new File(path);
        System.out.println("Оберіть дію:\n 1: Зашифрувати файл\n 2: Розшифрувати файл");
        String n = s.nextLine();
        try {
            int number = Integer.parseInt(n);
            if (number == 1) {
                encryptPhrase(file, 3);
            } if (number == 2) {
                decryptPhrase(file, 3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Невірний вибір");
        }
    }


    private static void decryptPhrase(File targetFile, int level) throws IOException {
        int currentLevel = level;
        String s = readFile(targetFile.getPath()).trim();
        int linePoiner = level - 1;
        char[] text = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (linePoiner < s.length()) {
                text[linePoiner] = s.charAt(i);
                linePoiner = linePoiner + level;
            } else {
                --currentLevel;
                linePoiner = currentLevel - 1;
                text[linePoiner] = s.charAt(i);
                linePoiner = linePoiner + level;
            }
        }
        StringBuilder out = new StringBuilder();
        for (char aText : text) {
            out.append(aText);
        }
        System.out.println("Розшифрований текст\n" + out);
    }

    private static void encryptPhrase(File targetFile,int level) throws IOException {
        String s = readFile(targetFile.getPath());
        StringBuilder sb = new StringBuilder();
        int currentLevel = level;
        int linePoiner = level - 1;
        for (int i = 0; i<s.length(); i++) {
            if (linePoiner < s.length()) {
                sb.append(s.charAt(linePoiner));
                linePoiner = linePoiner + level;
            } else {
                --currentLevel;
                linePoiner = currentLevel - 1;
                sb.append(s.charAt(linePoiner));
                linePoiner = linePoiner + level;
            }
        }
        try {
            Files.write(Paths.get(targetFile.getPath()), sb.toString().getBytes());
            System.out.println("Кінцевий файл згенерований у: " + Paths.get("").toRealPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Зашифрований текст\n" + sb + "\n");
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



