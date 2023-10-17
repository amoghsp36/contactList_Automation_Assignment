package ResponsePojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResp {
    @JsonProperty("user")
    private User user;
    @JsonProperty("token")
    private String token;

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
