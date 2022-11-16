package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DevExperienceData {
    STARTER("Только начал", "1"),
    ONEYEAR("1 год", "2"),
    TWOYEARS("2 года", "3");

    private String name;
    private String option;
}


