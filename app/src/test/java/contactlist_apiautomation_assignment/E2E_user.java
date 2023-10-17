package contactlist_apiautomation_assignment;

import ResponsePojo.AddUserResp;
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

    @Test(dataProvider = "user_data", dataProviderClass = DataProvider.class)
    public void add_user(String fname, String lname, String email, String password,String method) throws IOException {
        req = given()
                .spec(globalProperties.getReq())
                .body(buildData.addUserData(fname, lname, email, password));
        ApiResources apiResource = ApiResources.valueOf("AddUserAPI");
        System.out.println(apiResource.getResource());

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
        Assert.assertEquals(response.getStatusCode(),201);
        //Assert.assertEquals(globalProperties.getJsonPath(response,"status"),"Created");

    }
}
