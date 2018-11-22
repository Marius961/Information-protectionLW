public class Main {

    public static void main(String[] args) {
        int level = 3;
        String s = "Розглянемо ще дві прості криптосистеми.";
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
        System.out.println("Зашифрований текст\n" + sb + "\n");
        // розшифрування

        currentLevel = level;
        linePoiner = level - 1;
        char[] text = new char[sb.length()];
        for (int i = 0; i < sb.length(); i++) {
            if (linePoiner < s.length()) {
                text[linePoiner] = sb.charAt(i);
                linePoiner = linePoiner + level;
            } else {
                --currentLevel;
                linePoiner = currentLevel - 1;
                text[linePoiner] = sb.charAt(i);
                linePoiner = linePoiner + level;
            }
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < text.length; i++) {
            out.append(text[i]);
        }
        System.out.println("Розшифрований текст\n" + out);
    }
}



