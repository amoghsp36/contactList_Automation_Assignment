package contactlist_apiautomation_assignment;

import ResponsePojo.AddContactResp;
import ResponsePojo.LoginResp;
import Utils.ApiResources;
import Utils.DataProvider;
import Utils.GetGlobalProperties;
import Utils.TestBuildData;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class E2E_Contact {

    RequestSpecification req;
    TestBuildData buildData = new TestBuildData();
    GetGlobalProperties globalProperties = new GetGlobalProperties();
    public static String loginToken;
    ApiResources apiResources;
    Response response;
    public static String _id;

    @Test(dataProvider = "login_user_data", dataProviderClass = DataProvider.class,priority = 1)
    public void user_login(String email, String password) throws IOException {
        req = given().spec(globalProperties.getReq()).body(buildData.userLoginData(email, password));
        ApiResources apiResource = ApiResources.valueOf("UserLoginAPI");
        System.out.println(apiResource);
        response = req.when().post(apiResource.getResource()).then().extract().response();
        LoginResp loginResp = response.as(LoginResp.class);
        loginToken = loginResp.getToken();
        System.out.println(loginToken);
        long respTime = 2500;
        long actualRespTime = response.getTime();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertTrue(actualRespTime <= respTime);
        //Assert.assertEquals(loginResp.getUser().getEmail(),"contact_list25@gmail.com");
    }

    @Test(dataProvider = "add_contact_data", dataProviderClass = DataProvider.class, priority = 2)
    public void add_contact(String fname, String lname, String birthdate, String email, String phone, String street1, String street2, String city, String state, String postalCode, String country) throws IOException {

        req = given().spec(globalProperties.getReq()
                .header("Authorization","Bearer "+loginToken))
                .body(buildData.addContactData(fname,lname,birthdate,email,phone,street1,street2,city,state,postalCode,country));
        apiResources = ApiResources.valueOf("AddContactAPI");
        System.out.println(loginToken);
        System.out.println(apiResources.getResource());
        response = req.when().post(apiResources.getResource()).then().extract().response();
        AddContactResp addContactResp = response.as(AddContactResp.class);
        _id = addContactResp.getId();

        Assert.assertEquals(response.getStatusCode(),201);
    }

    @Test(priority = 3)
    public void get_contact() throws IOException {
        req = given().spec(globalProperties.getReq()
                .header("Authorization","Bearer "+loginToken));
        apiResources = ApiResources.valueOf("GetContactAPI");
        System.out.println(apiResources.getResource());
        response = req.when().get(apiResources.getResource()).then().extract().response();

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void get_contact_list() throws IOException {
        req = given().spec(globalProperties.getReq()
                .header("Authorization","Bearer "+loginToken));
        apiResources = ApiResources.valueOf("GetContactListAPI");
        System.out.println(apiResources.getResource());
        response = req.when().get(apiResources.getResource()).then().extract().response();

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(dataProvider = "update_contact_data", dataProviderClass = DataProvider.class, priority = 4)
    public void update_contact(String fname, String lname, String birthdate, String email, String phone, String street1, String street2, String city, String state, String postalCode, String country) throws IOException {

        req = given().spec(globalProperties.getReq()
                .header("Authorization", "Bearer "+loginToken))
                .body(buildData.addContactData(fname,lname,birthdate,email,phone,street1,street2,city,state,postalCode,country));
        apiResources = ApiResources.valueOf("UpdateContactAPI");

        System.out.println(apiResources.getResource());
        response = req.when().put(apiResources.getResource()+_id).then().extract().response();

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(dataProvider = "patch_contact_data", dataProviderClass = DataProvider.class, priority = 5)
    public void update_patch_contact(String fname, String lname, String birthdate) throws IOException {
        req = given().spec(globalProperties.getReq()
                .header("Authorization", "Bearer "+loginToken))
                .body(buildData.patchContactData(fname, lname, birthdate));
        apiResources = ApiResources.valueOf("UpdateContactPatchAPI");
        System.out.println(apiResources.getResource());
        response = req.when().patch(apiResources.getResource()+_id).then().extract().response();

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 6)
    public void delete_contact() throws IOException {
        req = given().spec(globalProperties.getReq()
                .header("Authorization", "Bearer "+loginToken));
        apiResources = ApiResources.valueOf("DeleteContactAPI");
        System.out.println(apiResources.getResource());
        response = req.delete(apiResources.getResource()+_id).then().extract().response();
        System.out.println(response.asString());

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.asString(),"Contact deleted");
    }
}
