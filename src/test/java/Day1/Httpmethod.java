package Day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Httpmethod {
    /* Precondition --given() -->content type,set cookies,add auth,add param,set header info etc..
    action step -->when() --> get,post,put,patch,delete
    Validation --> then() --> validate status code,extract response ,header,cookies & response body
    validation
    status code
    response time
    response body
    content type
    response string
    * */

    int id =0;

        @Test(priority = 1)
        void getuser ()
        {
            given()
                    .when()
                    .get("https://reqres.in/api/users?page=2")
                    .then()
                    .statusCode(200)
                    .body("page", equalTo(2));


        }
        @Test(priority = 2)
        void createuser(){
            HashMap<String,String>data = new HashMap<>();
            data.put("name","akash");
            data.put("job","trainer");


          id=  given()
                    .contentType("application/json")
                    .body(data)
                    .when()
                    .post("https://reqres.in/api/users")
                    .jsonPath().getInt("id");
//                    .then()
//                    .statusCode(201)
//                    .log().all();

        }
        @Test(priority = 3)
        void updateuser(){
            HashMap<String,String>data = new HashMap<>();
            data.put("name","akash");
            data.put("job","learner");
            given()
                    .contentType("application/json")
                    .body(data)
                    .when()
                    .put("https://reqres.in/api/users/"+id)
                    .then()
                    .statusCode(200)
                    .log().all();
        }
        @Test(priority = 4)
        void deleteuser(){
            given()
                    .when()
                    .delete("https://reqres.in/api/users/"+id)
                    .then()
                    .statusCode(204)
                    .log().all();

        }

    }

