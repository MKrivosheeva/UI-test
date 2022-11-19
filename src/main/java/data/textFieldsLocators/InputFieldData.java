package data.textFieldsLocators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

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

   // private By firstNameRu = By.cssSelector("#id_fname");
//    private By firstNameEn = By.cssSelector("#id_fname_latin");
//    private By lastNameRu = By.cssSelector("#id_lname");
//    private By lastNameEn = By.cssSelector("#id_lname_latin");
//    private By blogName = By.cssSelector("#id_blog_name");
//   private By companySelector = By.cssSelector("input[name='company']");
//    private By positionSelector = By.cssSelector("input[name='work']");
    private String fieldName;
    private String attributeValue;
//    public setDataToInputField(InputFieldData field, String value) {
//        String selector = String.format(«[name=‘%s’]», field.getAttributeValue());
//    }
}
