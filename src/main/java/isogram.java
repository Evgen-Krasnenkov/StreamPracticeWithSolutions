public class isogram {
    public static boolean  isIsogram(String str) {
        return str.toLowerCase().chars().distinct().count() == (long) str.length()
                ? true : false;

    } 
}