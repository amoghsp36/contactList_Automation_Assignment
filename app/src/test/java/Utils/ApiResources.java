package Utils;

public enum ApiResources {
    AddUserAPI("users"),
    GetUserAPI("users/me"),
    UserLoginAPI("users/login"),
    UpdateUserAPI("users/me"),
    DeleteUserAPI("users/me");

    public String resource;

    ApiResources(String resource){
        this.resource = resource;
    }

    public String getResource(){
        return resource;
    }
}
