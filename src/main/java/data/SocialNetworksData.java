package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialNetworksData {
   FACEBOOK("Facebook"),
   HABR("Habr"),
   TELEGRAM("Telegram");
   private String name;
}
