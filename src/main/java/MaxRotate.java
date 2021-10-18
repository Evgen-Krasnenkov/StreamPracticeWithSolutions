public class MaxRotate {
    
    public static long maxRot (long n) {
        // your code
        String inputStr = String.valueOf(n);
        int length = inputStr.length();
        Long max = n;
        for (int i = 0; i < length; i++) {
            char firstDigit = inputStr.charAt(i);
            String newNumberString = inputStr.substring(0, i)
            + inputStr.substring(i + 1) + firstDigit;
            Long newN = Long.valueOf(newNumberString);
            if (newN > max) {
                max = newN;
            }
            inputStr = newNumberString;
        }
        return max;
    }
}