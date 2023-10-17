package contactlist_apiautomation_assignment;

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

    @Test(dataProvider = "login_user_data", dataProviderClass = DataProvider.class,priority = 1)
    public void user_login(String email, String password) throws IOException {
        req = given().spec(globalProperties.getReq()).body(buildData.userLoginData(email, password));
        ApiResources apiResource = ApiResources.valueOf("UserLoginAPI");
        System.out.println(apiResource);
        response = req.when().post(apiResource.getResource()).then().extract().response();
        LoginResp loginResp = response.as(LoginResp.class);
        loginToken = loginResp.getToken();
        System.out.println(loginToken);
        Assert.assertEquals(response.getStatusCode(),200);
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

}