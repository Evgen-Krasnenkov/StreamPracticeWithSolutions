public class SimpleLetterRemoval {
    public static String solve(String s, int k) {
        int j = 0;
        int i = 0;
            String aaa = String.valueOf((char) ('a' + i));
            while (j < k && s.length() != 0) {
                while (s.contains(aaa) && j < k && s.length() != 0) {
                    s = s.replaceFirst(aaa, "");
                    j++;
                }
                i++;
                aaa = String.valueOf((char) ('a' + i));
            }
        return s;
    }
}