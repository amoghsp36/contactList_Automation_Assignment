package ResponsePojo;

import lombok.Data;

import java.util.List;

@Data
public class AddUserResp {
    private List<User> userData;
    private String token;
}
