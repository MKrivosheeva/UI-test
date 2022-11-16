package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnglishLevelData {

    BEGINNER("Начальный уровень (Beginner)"),
    ELEMENTARY("Элементарный уровень (Elementary)"),
    PREINTERMEDIATE("Ниже среднего (Pre-Intermediate)"),
    INTERMEDIATE("Средний (Intermediate)"),
    UPPERINTERMEDIATE("Выше среднего (Upper Intermediate)"),
    ADVANCED("Продвинутый (Advanced)"),
    MASTERY("Супер продвинутый (Mastery)");

    private String name;

}
