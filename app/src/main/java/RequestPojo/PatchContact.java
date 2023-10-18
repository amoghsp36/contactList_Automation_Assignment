package RequestPojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PatchContact {
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("birthdate")
    private String birthdate;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
