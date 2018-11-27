import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введіть шлях до файлу");
        Scanner s = new Scanner(System.in);
        String path = s.nextLine();
        File file = new File(path);
        System.out.println("Вкажіть шлях до файлу з текстом який потрібно зашифрувати");
        String bookFilePath = s.nextLine();
        File bookFile = new File(bookFilePath);
        System.out.println("Що потрібно зробити з файлом? \n 1 зашифрувати \n 2 Розшифрувати");
        String n = s.nextLine();
        try {
            int number = Integer.parseInt(n);
            if (number == 1) {
                encryptPhrase(bookFile, file);
            } if (number == 2) {
                decryptPhrase(file, bookFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Невірний вибір");
        }
    }

    private static void encryptPhrase(File bookFile, File messageFile) throws IOException {
        Map<Integer, String> book = readBookFile(bookFile.getPath());
        String message = readFileToString(messageFile.getPath());
        int max = book.size();
        boolean charSearch = false;
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        int r;
        for (char c : message.toCharArray()) {
            while (!charSearch) {
                r = random.nextInt(max);
                if (book.get(r).contains(String.valueOf(c))) {
                    for (int i = 0; i< book.get(r).length(); i++) {
                        char[] line =  book.get(r).toCharArray();
                        if (c == line[i]) {
                            System.out.println("Рядок: " + r + " індекс: " + (i+1) + " буква: " + line[i]);
                            result.append(" ").append(r).append(" ").append(i);
                            break;
                        }
                    }
                    charSearch = true;
                }
            }
            charSearch = false;
        }
        try {
            Files.write(messageFile.toPath(), result.toString().getBytes());
            System.out.println("Кінцевий файл згенерований у: " + Paths.get("").toRealPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void decryptPhrase(File numbersMessage, File book) throws IOException {
        Scanner message = new Scanner(new FileReader(numbersMessage.getPath()));
        Map<Integer, String> bookFile = readBookFile(book.getPath());
        StringBuilder result = new StringBuilder();
        while(message.hasNext()) {
            int line = message.nextInt();
            int charIndex = message.nextInt();
            result.append(bookFile.get(line).charAt(charIndex));
        }
        System.out.println(result);
    }


    private static String readFileToString(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(path));
        StringBuilder sb = new StringBuilder();

        while(in.hasNext()) {
            sb.append(in.nextLine()).append(" ");
        }
        return sb.toString().trim();
    }

    private static Map<Integer, String> readBookFile(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(path));
        Map<Integer, String> book = new HashMap<>();
        int line = 0;
        while(in.hasNext()) {
            book.put(line, in.nextLine().trim());
            line++;
        }
        return book;
    }
}
