import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class FilterPosts {

    @Test
    public void filterPosts() {
        given().log().all().queryParam("author", "Roman")
                .when().get("http://localhost:3000/posts")
                .then().log().all();
    }

    @Test
    public void filterPostsById() {
        given().log().all().queryParam("id", "1", "2")
                .when().get("http://localhost:3000/posts")
                .then().log().all();
    }

    @Test
    public void filterPostsByAuthorTitle() {

        Map<String, Object> params = new HashMap<>();
        params.put("author", "Rafal");
        params.put("title", "Pierwszy post");

        given().log().all().queryParams(params)
                .when().get("http://localhost:3000/posts")
//                .then().log().all().statusCode(Matchers.equalTo(200));
                .then().log().all().statusLine(Matchers.containsString("OK"));
    }
}
