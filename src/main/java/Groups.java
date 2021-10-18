import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class Groups{

  public static boolean groupCheck(String s){
    Stack<Character> stack = new Stack<>();
    char[] aChars = s.toCharArray();
    for (char aChar : aChars) {
      switch (aChar){
        case '(':
        case '{':
        case '[': stack.push(aChar); break;
        case ')':if (stack.pop() != '(') {
          return false;
        }
        case '}':if (stack.pop() != '{') {
          return false;
        }
        case ']': if (stack.pop() != '[') {
          return false;
        }

        default: break;
      }
    }
    return stack.isEmpty();
  }

  public static boolean groupCheck_DOnetWork(String s){
    int sLength;
    do {
      sLength = s.length();
      s.replace("{}", "");
      s.replace("[]", "");
      s.replace("()", "");
    } while (s.length() != sLength);

    /*
    s.replaceAll("{}", "");
    s.replaceAll("()", "");
    s.replaceAll("[]", "");

     */
    return  s.length() == 0;
  }
  public static boolean groupCheckMap_notOK(String s){
    Map<Character, Long> collect = s.chars().mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy((k -> k), Collectors.counting()));
    boolean b = collect.get('(') == collect.get(')');
    boolean b1 = collect.get('{') == collect.get('}');
    boolean b2 = collect.get('[') == collect.get(']');
    return b && b1 && b2;
  }
  static boolean groupCheckRegex(String s) {
    for (int i = s.length() / 2; i > 0; i--)
      s = s.replaceAll("\\(\\)|\\[]|\\{}", "");
    return s.isEmpty();
  }
}