package Utils;

public enum ApiResources {
    //USER APIs
    AddUserAPI("users"),
    GetUserAPI("users/me"),
    UserLoginAPI("users/login"),
    UpdateUserAPI("users/me"),
    DeleteUserAPI("users/me"),
    UserLogout("users/logout"),

    //CONTACT APIs
    AddContactAPI("contacts"),
    GetContactAPI("contacts/");

    public String resource;

    ApiResources(String resource){
        this.resource = resource;
    }

    public String getResource(){
        return resource;
    }
}
