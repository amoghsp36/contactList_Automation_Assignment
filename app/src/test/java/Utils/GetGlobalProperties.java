package Utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

import static FeatureDefinitions.stepDefinitions.token;

public class GetGlobalProperties {
    public static RequestSpecification req;
    public static String token;

    public RequestSpecification getReq() throws IOException {
        PrintStream printStream = new PrintStream(new FileOutputStream("logs.txt"));
        if(req==null){
            req = new RequestSpecBuilder()
                    .setBaseUri(getProperty("baseUrl"))
                    .setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                    .build();
            return req;
        }
        return req;
    }
    public RequestSpecification getLoginReq() throws IOException {
        PrintStream printStream = new PrintStream(new FileOutputStream("logs.txt"));
        if(req==null){
            req = new RequestSpecBuilder()
                    .setBaseUri(getProperty("baseUrl"))
                    .setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                    .addHeader("Authorization", "Bearer "+token)
                    .build();
            return req;
        }
        return req;
    }

    public String getProperty(String key) throws IOException {
        Properties properties = new Properties();
        String propertyPath = "/Users/testvagrant/Desktop/contactlist_apiautomation_assignment/app/src/test/java/Utils/global.properties";
        FileInputStream fis = new FileInputStream(new File(propertyPath));
        properties.load(fis);
        return properties.getProperty(key);
    }

    public String getJsonPath(Response response, String key){
        String resp = response.asString();
        JsonPath jsonPath = new JsonPath(resp);
        return jsonPath.get(key).toString();
    }
}
