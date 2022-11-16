package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReadyToRelocateData {
    TRUE(true),
    FALSE(false);
    private Boolean value;

}
