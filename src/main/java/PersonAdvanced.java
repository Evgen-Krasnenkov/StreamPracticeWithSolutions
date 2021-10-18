import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonAdvanced {
  private String name;
  private int age;
  private Sex sex;
  private List<Cat> catList;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonAdvanced that = (PersonAdvanced) o;
    return age == that.age && Objects.equals(name, that.name) && sex == that.sex && Objects.equals(catList, that.catList);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }

  public void setCatList(List<Cat> catList) {
    this.catList = catList;
  }

  public PersonAdvanced(String name, int age, Sex sex) {
    this.name = name;
    this.age = age;
    this.sex = sex;
    catList = new ArrayList<>();
  }

  public PersonAdvanced(String name, int age, Sex sex, List<Cat> catList) {
    this.name = name;
    this.age = age;
    this.sex = sex;
    this.catList = catList;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public Sex getSex() {
    return sex;
  }

  public List<Cat> getCatList() {
    return catList;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age, sex, catList);
  }

  @Override
  public String toString() {
    return "Person{"
      + "name='" + name + '\''
      + ", age=" + age
      + ", sex=" + sex
      + ", catList=" + catList
      + '}';
  }

  public enum Sex {
    MAN, WOMAN
  }
}
