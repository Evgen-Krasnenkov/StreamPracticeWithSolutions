import java.util.List;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StringHandling {
  public static String longestAlpabeticalSubstring(String text) {
      Matcher matcher = Pattern
              .compile("a*b*c*d*e*f*g*h*i*j*k*l*m*n*o*p*q*r*s*t*u*v*w*x*y*z*")
              .matcher(text);/*
      Optional<MatchResult> reduce = collect.stream().reduce((a, b) -> a.group().length() >= b.group().length() ? a : b);

      MatchResult matchResult = reduce.get();
      String group = matchResult.group();
*/
      return "group";
  }
}