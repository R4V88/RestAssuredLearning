import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetSpecificPostTest {

    @Test
    public void getPost() {
//        given().log().all().pathParam("postId", 1)
//                .when().get("http://localhost:3000/posts/{postId}")
//                .then().log().all();

//        lub

        given().log().all()
                .when().get("http://localhost:3000/posts/{postId}", 1)
                .then().log().all();
    }
}
