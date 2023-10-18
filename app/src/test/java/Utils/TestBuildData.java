package Utils;

import RequestPojo.AddContact;
import RequestPojo.AddUser;
import RequestPojo.PatchContact;
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

    public AddContact addContactData(String fname, String lname, String birthDate, String email, String phone, String street1, String street2, String city, String state, String postalCode, String country){
        AddContact addContact = new AddContact();
        addContact.setFirstName(fname);
        addContact.setLastName(lname);
        addContact.setBirthdate(birthDate);
        addContact.setEmail(email);
        addContact.setPhone(phone);
        addContact.setStreet1(street1);
        addContact.setStreet2(street2);
        addContact.setCity(city);
        addContact.setStateProvince(state);
        addContact.setPostalCode(postalCode);
        addContact.setCountry(country);
        return addContact;
    }

    public PatchContact patchContactData(String fname, String lname, String birthdate){
        PatchContact patchContact = new PatchContact();
        patchContact.setFirstName(fname);
        patchContact.setLastName(lname);
        patchContact.setBirthdate(birthdate);
        return patchContact;
    }
}
