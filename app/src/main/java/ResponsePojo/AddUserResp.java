package ResponsePojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AddUserResp {
    @JsonProperty("user")
    private User user;
    private String token;

    public User getUserData() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
