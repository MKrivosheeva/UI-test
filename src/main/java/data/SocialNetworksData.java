package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialNetworksData {
   FACEBOOK("facebook"),
   HABR("habr"),
   TELEGRAM("telegram");
   private String name;
}
