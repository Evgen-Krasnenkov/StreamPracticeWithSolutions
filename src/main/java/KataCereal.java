import java.util.ArrayDeque;
import java.util.Deque;

public class KataCereal {

	public static long[] wheatFromChaff(long[] values) {
		long[] clone = values;
		int j = clone.length;
		for (int i = 0; i < clone.length; i++) {
			if (clone[i] > 0) {
				while (j > i) {
					long temp = clone[i];
					if (clone[j - 1] < 0) {
						clone[i] = clone[j - 1];
						clone[j - 1] = temp;
						break;
					}
					j--;
				}
			}
		}
		return clone;
	}
}