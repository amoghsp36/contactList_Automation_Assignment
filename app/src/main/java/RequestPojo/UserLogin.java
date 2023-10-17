package RequestPojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserLogin {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    public UserLogin(){

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
