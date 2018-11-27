import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введіть шлях до файлу в якому зашифроване повідомдення або в якому хочете зашифрувати повідомлення");
        Scanner s = new Scanner(System.in);
        String path = s.nextLine();
        File file = new File(path);
        System.out.println("Оберіть дію:\n 1: Зашифрувати фразу у файл\n 2: Розшифрувати фразу з файлу");
        String n = s.nextLine();
        try {
            int number = Integer.parseInt(n);
            if (number == 1) {
                System.out.println("Вкажіть шлях до файлу з текстом який потрібно зашифрувати");
                String textFilePath = s.nextLine();
                File textFile = new File(textFilePath);
                encryptPhrase(file, textFile);
            } if (number == 2) {
                decryptPhrase(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Невірний вибір");
        }
    }

    private static void encryptPhrase(File targetFile, File messageFile) throws IOException {
        byte[] outBytes = Files.readAllBytes(targetFile.toPath());
        byte[] textBytes = Files.readAllBytes(messageFile.toPath());
        String text = readFileToString(messageFile.getPath());
        byte[] endBytes;

        if (outBytes.length < textBytes.length) {
            int cursorPosition = 0;
            byte[] tempArray = new byte[textBytes.length];
            for (int i = 0; i < tempArray.length; i++) {
                if (cursorPosition == outBytes.length) {
                    cursorPosition = 0;
                }
                tempArray[i] = outBytes[cursorPosition];
                cursorPosition++;
            }
            outBytes = tempArray;
        } else {
            text = text + "|";
            textBytes = text.getBytes();
        }
        endBytes = new byte[textBytes.length + outBytes.length];
        boolean trigger = false;
        int outBytesIndex = 0;
        int textBytesIndex = 0;
        for (int i = 0; i < endBytes.length; i++) {
            if (!trigger) {
                endBytes[i] = outBytes[outBytesIndex];
                outBytesIndex++;
                trigger = true;
            } else {
                if (textBytesIndex < textBytes.length) {
                    endBytes[i] = textBytes[textBytesIndex];
                    textBytesIndex++;
                    trigger = false;
                }
            }
        }
        try {
            StringBuilder path = new StringBuilder(targetFile.getPath());
            String format = path.substring(path.lastIndexOf(".") + 1);
            Files.write(Paths.get("result." + format), endBytes);
            System.out.println("Кінцевий файл згенерований у: " + Paths.get("").toRealPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void decryptPhrase(File file) throws IOException {
        ArrayList<Byte> b = new ArrayList<>();
        byte[] readedBytes = Files.readAllBytes(file.toPath());
        boolean trigger = false;
        for (byte readedByte : readedBytes) {
            if (!trigger) {
                trigger = true;
            } else {
                if (readedByte == '|') {
                    break;
                }
                b.add(readedByte);
                trigger = false;
            }
        }
        byte[] e = new byte[b.size()];
        for (int i = 0; i < e.length; i++) {
            e[i] = b.get(i);
        }
        String phrase = new String(e, StandardCharsets.UTF_8);
        System.out.println(phrase);
    }


    private static String readFileToString(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(path));
        StringBuilder sb = new StringBuilder();

        while(in.hasNext()) {
            sb.append(in.nextLine()).append(" ");
        }

        return sb.toString();
    }


}
