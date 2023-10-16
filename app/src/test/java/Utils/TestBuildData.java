package Utils;

import RequestPojo.AddContact;
import RequestPojo.AddUser;
import RequestPojo.UserLogin;

public class TestBuildData {
    public AddUser addUserData(String fname, String lname, String email, String password){
        AddUser addUser = new AddUser();
        addUser.setFirstName(fname);
        addUser.setLastName(lname);
        addUser.setEmail(email);
        addUser.setPassword(password);
        return addUser;
    }

    public UserLogin userLoginData(String email, String password){
        UserLogin userLogin = new UserLogin();
        userLogin.setEmail(email);
        userLogin.setPassword(password);
        return userLogin;
    }

    public AddContact addContactData(String fname, String lname, String birthDate, String email, String phone, String street1, String street2, String city, String state, int postalCode, String country){
        AddContact addContact = new AddContact();
        addContact.setFirstName(fname);
        addContact.setLastName(lname);
        addContact.setBirthdate(birthDate);



        return addContact;
    }
}
