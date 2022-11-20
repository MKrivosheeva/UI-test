package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DevLanguagesData {
  JAVA ("Java", "3"),
  PYTHON ("Python", "10"),
  RUBY ("Ruby", "9");

  private String name;
  private String option;
}
