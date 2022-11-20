package data.textFieldsLocators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InputFieldData {

    FIRSTNAMERU("Имя", "fname"),
    LASTNAMERU("Фамилия", "lname"),
    FIRSTNAMEEN("Имя(латинницей)", "fname_latin"),
    LASTNAMEEN("Фамилия(латинницей)", "lname_latin"),
    BLOGNAME("Имя(в блоге)","blog_name"),
    COMPANY("Компания", "company"),
    POSITION("Должность", "work");

    private String fieldName;
    private String attributeValue;

}
