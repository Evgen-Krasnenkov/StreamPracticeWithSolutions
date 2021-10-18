import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class SimpleLetterRemovalTest {
        @Test
        public void basicTests() {
            doTest("abracadabra",  1, "bracadabra");
            doTest("abracadabra",  2, "brcadabra");
            doTest("abracadabra",  6, "rcdbr");
            doTest("abracadabra",  8, "rdr");
            doTest("abracadabra", 50, "");
        }
        private void doTest(String s, int k, String expected) {
            assertEquals(expected, SimpleLetterRemoval.solve(s, k));
        }
}
