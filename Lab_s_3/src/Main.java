import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть шлях до каринки");
        String pathToPicture = scanner.nextLine();
        File file = new File(pathToPicture);


        System.out.println("1 - Зашифрувати \n 2: Розшифрувати");
        String n = scanner.nextLine();


        try {
            int selected = Integer.parseInt(n);
            if (selected == 1) {
                System.out.println("Введіть текст який потрібно зашифрувати");
                String text = scanner.nextLine();
                encrypt(file, text);
            } if (selected == 2) {
                decrypt(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void encrypt(File file, String text) throws IOException {
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        byte[] textBytes = text.getBytes();
        byte[] result;

        if (fileBytes.length < textBytes.length) {
            int position = 0;
            byte[] tArray = new byte[textBytes.length];
            for (int i = 0; i < tArray.length; i++) {
                if (position == fileBytes.length) {
                    position = 0;
                }
                tArray[i] = fileBytes[position];
                position++;
            }
            fileBytes = tArray;
        } else {
            text = text + "~";
            textBytes = text.getBytes();
        }
        result = new byte[textBytes.length + fileBytes.length];
        boolean t = false;
        int resultIndex = 0;
        int textIndex = 0;
        for (int i = 0; i < result.length; i++) {
            if (!t) {
                result[i] = fileBytes[resultIndex];
                resultIndex++;
                t = true;
            } else {
                if (textIndex < textBytes.length) {
                    result[i] = textBytes[textIndex];
                    textIndex++;
                    t = false;
                }
            }
        }
        try {
            Files.write(Paths.get(file.getPath()), result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void decrypt(File file) throws IOException {
        ArrayList<Byte> b = new ArrayList<>();
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        boolean t = false;
        for (byte readedByte : fileBytes) {
            if (!t) {
                t = true;
            } else {
                if (readedByte == '|') {
                    break;
                }
                b.add(readedByte);
                t = false;
            }
        }
        byte[] e = new byte[b.size()];
        for (int i = 0; i < e.length; i++) {
            e[i] = b.get(i);
        }
        String phrase = new String(e, StandardCharsets.UTF_8);
        System.out.println(phrase);
    }


}
