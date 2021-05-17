import io.restassured.http.ContentType;
import model.Post;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdatePost {

//    post zmienia czesc zasobu
    @Test
    public void updatePost() {
            Map<String, Object> newPost = new HashMap<>();
            newPost.put("title", "tytuł po aktualizacji 2");
            newPost.put("author", "Rafalll");

            given().log().all().contentType(ContentType.JSON).body(newPost)
                    .when().patch("http://localhost:3000/posts/1")
                    .then().log().all();
    }

//    patch zmienia caly zasobu
    @Test
    public void replacePost() {
        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title", "tytuł po aktualizacji 2");
        newPost.put("author", "Rafalll");

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().put("http://localhost:3000/posts/1")
                .then().log().all();
    }

//    Należy nad klasą post dodań adnotację: @JsonInclude(JsonInclude.Include.NON_NULL)
    @Test
    public void updatePostObject() {
        Post newPost = new Post();
//        newPost.setAuthor("Roman");
        newPost.setTitle("Nowy tytuł Romana");

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().patch("http://localhost:3000/posts/1")
                .then().log().all();
    }

    @Test
    public void replacePostObject() {
        Post newPost = new Post();
        newPost.setAuthor("Roman");
//        newPost.setTitle("Tytuł Romana");

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("http://localhost:3000/posts/1")
                .then().log().all();
    }
}
