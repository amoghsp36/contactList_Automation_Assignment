package contactlist_apiautomation_assignment;

import ResponsePojo.AddUserResp;
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

import static FeatureDefinitions.stepDefinitions.token;
import static Utils.ApiResources.AddUserAPI;
import static io.restassured.RestAssured.given;

public class E2E_user {
    RequestSpecification req;
    Response response;
    TestBuildData buildData = new TestBuildData();
    GetGlobalProperties globalProperties = new GetGlobalProperties();
    ApiResources apiResources;
    public static String token;
    public static String loginToken;

    @Test(dataProvider = "user_data", dataProviderClass = DataProvider.class,priority = 1)
    public void should_add_user(String fname, String lname, String email, String password,String method) throws IOException {
        //Arrange
        req = given()
                .spec(globalProperties.getReq())
                .body(buildData.addUserData(fname, lname, email, password));
        ApiResources apiResource = ApiResources.valueOf("AddUserAPI");
        System.out.println(apiResource.getResource());

        //Act
        if(method.equalsIgnoreCase("POST")){
            response = req.when().post(apiResource.getResource()).then().extract().response();
            AddUserResp bearerToken = response.as(AddUserResp.class);
            token = bearerToken.getToken();
            System.out.println(token);
            System.out.println(response.prettyPrint());
        }
        else if(method.equalsIgnoreCase("GET")){
            response = req.when().get(apiResource.getResource()).then().extract().response();
            System.out.println(response.prettyPrint());
        }else if(method.equalsIgnoreCase("PATCH")){
            response = req.when().patch(apiResource.getResource()).then().extract().response();
            System.out.println(response.prettyPrint());
        }

        //Assert
        Assert.assertEquals(response.getStatusCode(),201);
    }

    @Test(dataProvider = "login_user_data", dataProviderClass = DataProvider.class,priority = 2)
    public void should_user_login(String email, String password) throws IOException {
        //Arrange
        req = given().spec(globalProperties.getReq()).body(buildData.userLoginData(email, password));
        ApiResources apiResource = ApiResources.valueOf("UserLoginAPI");
        System.out.println(apiResource);

        //Act
        response = req.when().post(apiResource.getResource()).then().extract().response();
        LoginResp resp = response.as(LoginResp.class);
        loginToken = resp.getToken();

        //Assert
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 3)
    public void should_get_user() throws IOException {
        //Arrange
        System.out.println(token);
        req = given().spec(globalProperties.getReq().header("Authorization","Bearer "+token));
        ApiResources apiResource = ApiResources.valueOf("GetUserAPI");
        System.out.println(apiResource);

        //Act
        response = req.when().get(apiResource.getResource()).then().extract().response();

        //assert
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(dataProvider = "update_user_data",dataProviderClass = DataProvider.class,priority = 4)
    public void should_update_user(String fname, String lname, String email, String password,String method) throws IOException {
        //Arrange
        System.out.println(token);
        req = given().spec(globalProperties.getReq().header("Authorization","Bearer "+token))
                .body(buildData.addUserData(fname, lname, email, password));
        ApiResources apiResource = ApiResources.valueOf("UpdateUserAPI");
        System.out.println(apiResource);

        //Act
        if(method.equalsIgnoreCase("PATCH")){
            response = req.when().patch(apiResource.getResource()).then().extract().response();
        }
        //Assert
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 6, enabled = false)
    public void should_logout_user() throws IOException {
        //Arrange
        req = given().spec(globalProperties.getReq().header("Authorization","Bearer "+token));
        ApiResources apiResources = ApiResources.valueOf("UserLogout");
        System.out.println(apiResources.getResource());
        //Act
        response = req.when().post(apiResources.getResource()).then().extract().response();

        //Assert
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(dataProvider = "login_user_data", dataProviderClass = DataProvider.class, priority = 5)
    public void should_delete_user(String email, String password) throws IOException {
        //Arrange
        should_user_login(email,password);
        req = given().spec(globalProperties.getReq()
                .header("Authorization", "Bearer "+loginToken));
        System.out.println(loginToken);
        apiResources = ApiResources.valueOf("DeleteUserAPI");
        System.out.println(apiResources.getResource());
        //Act
        response = req.when().delete(apiResources.getResource()).then().extract().response();

        //Assert
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
