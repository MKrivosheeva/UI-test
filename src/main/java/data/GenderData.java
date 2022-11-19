package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderData {

    MALE("Мужской", "m"),
    FEMALE("Женский", "f");

    private String name;
    private String option;

}
