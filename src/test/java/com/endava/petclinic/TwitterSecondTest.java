package com.endava.petclinic;

import com.endava.petclinic.models.Pet;
import com.endava.petclinic.utils.EnvReader;
import com.github.scribejava.core.model.Response;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TwitterSecondTest {
    String ConsumerKey = "xDpvjjIfui8Su72Ti1AR1CKzi";
    String ConsumerSecret = "HobgaN3k3jn0vk11ItpV1CSWhyzpIm0Nf2FXbF01wZmDNxmyxl";
    String Token = "278136668-zJNcE6VzUubCamfnS29EEw1S0HyRUDufFl42AbUk";
    String TokenSecret = "2Q5WhderaUy2Tk8tL7n400L4ddoXm6STWtasc6yKpM3Wf";
    String Tweetid, id1;

    @Test
    public void CreateTweet() {
        RestAssured.baseURI = "https://api.twitter.com/1.1/statuses";

        Response Resp = (Response) given().
                auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).
                queryParam("status", "Bogdan").log().all().
                when().
                post("/update.json").
                then().assertThat().statusCode(200).log().all().
                extract().response();

        String CreateTwe = Resp.toString();
        JsonPath js = new JsonPath(CreateTwe);

        given().baseUri("https://api.twitter.com/1.1/statuses")
                .auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret).
                queryParam("status", "Bogdan").log().all().
                when()
                .get("/update.json")
                .then()
                .statusCode(HttpStatus.SC_OK);

        System.out.println("id is" + js.get("id"));

        Tweetid = (js.get("id")).toString();

        System.out.println("Id of newly Created Tweet is \t" + Tweetid);
    }
}

