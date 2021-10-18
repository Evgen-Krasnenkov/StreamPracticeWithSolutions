import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyGroupTester {
  
  @Test
  public void myTests() {
    Assertions.assertEquals(true,  Groups.groupCheck("()"));
    Assertions.assertEquals(false, Groups.groupCheck("({"));
  }
}
