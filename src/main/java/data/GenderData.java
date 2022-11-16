package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderData {

    Male("Мужской", "m"),
    Female("Женский", "f");

    private String name;
    private String option;

}
