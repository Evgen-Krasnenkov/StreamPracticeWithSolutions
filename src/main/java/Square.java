import static java.lang.Math.sqrt;

public class Square {
    public static boolean isSquare(int n) {
      int a = (int) Math.sqrt(n);
        return a * a == n ? true : false;
    }
}