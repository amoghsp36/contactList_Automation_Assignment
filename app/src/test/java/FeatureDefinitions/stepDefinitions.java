package FeatureDefinitions;

import RequestPojo.AddUser;
import ResponsePojo.AddUserResp;
import Utils.ApiResources;
import Utils.GetGlobalProperties;
import Utils.TestBuildData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class stepDefinitions {

    RequestSpecification req;
    Response response;
    TestBuildData buildData = new TestBuildData();
    GetGlobalProperties globalProperties = new GetGlobalProperties();
    public static String token;

    @Given("user payload with {string} {string} {string} {string}")
    public void user_payload_with(String fname, String lname, String email, String password) throws IOException {
        req = given()
                .spec(globalProperties.getReq())
                .body(buildData.addUserData(fname, lname, email, password));
    }

    @When("calls the {string} with http {string} request")
    public void calls_the_with_http_request(String resource, String method) {
        // Write code here that turns the phrase above into concrete actions
        ApiResources apiResource = ApiResources.valueOf(resource);
        System.out.println(apiResource.getResource());

        if(method.equalsIgnoreCase("POST")){
            response = req.when().post(apiResource.getResource()).then().extract().response();
//            AddUserResp bearerToken = response.as(AddUserResp.class);
//            token = bearerToken.getToken();
//            System.out.println(token);
            System.out.println(response.prettyPrint());
        }
        else if(method.equalsIgnoreCase("GET")){
            response = req.when().get(apiResource.getResource()).then().extract().response();
            System.out.println(response.prettyPrint());
        }else if(method.equalsIgnoreCase("PATCH")){
            response = req.when().patch(apiResource.getResource()).then().extract().response();
            System.out.println(response.prettyPrint());
        }
    }

    @Then("API call was successful with status code {int}")
    public void api_call_was_successful_with_status_code(int statusCode) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    @Then("response body has {string} equal to {string}")
    public void response_body_has_equal_to(String key, String value) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(globalProperties.getJsonPath(response,key),value);
    }

    @Given("user payload {string} {string}")
    public void userPayload(String email, String password) throws IOException {
        req = given().spec(globalProperties.getReq()).body(buildData.userLoginData(email,password));
    }
}
