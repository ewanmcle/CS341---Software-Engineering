package src;

public class Scrabbler {

    String letters;

    // constructor
    public Scrabbler(String l) {
        letters = l;
    }

    public String getLetters() {
        return letters;
    }

    public String scrabblinator() {

        letters = letters.toUpperCase();
        if (letters.length() > 7) return "too many letters.";

        char[] chars = new char[letters.length()];
        for (int i = 0; i < letters.length(); i++) {
            if (letters.charAt(i) < 'A' || letters.charAt(i) > 'Z') return "only enter alphabetical characters";
            chars[i] = letters.charAt(i);
        }

        StringBuilder result = new StringBuilder();
        boolean[] used = new boolean[chars.length];
        char[] chosen = new char[chars.length];

        permute(chars, chosen, used, 0, result);

        return result.toString();
    }

    private void permute(char[] letters, char[] chosen, boolean[] used, int position, StringBuilder result) {
        if (position == letters.length) {
            result.append(new String(chosen)).append("\n");
            return;
        }

        for (int i = 0; i < letters.length; i++) {
            if (!used[i]) {
                used[i] = true;
                chosen[position] = letters[i];
                permute(letters, chosen, used, position + 1, result);
                used[i] = false;
            }
        }
    }
}
