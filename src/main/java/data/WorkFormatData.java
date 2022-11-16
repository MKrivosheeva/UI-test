package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WorkFormatData {
    FULLTIME("Полный день"),
    FLEXIBLE("Гибкий график"),
    REMOTE("Удаленно");

    private String name;

}
