import io.restassured.http.ContentType;
import model.Post;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class AddPostTest {

    @Test
    public void addPost(){
        String newPost = "{\n" +
                "        \"title\": \"Pierwszy post\",\n" +
                "        \"author\": \"Rafal\"\n" +
                "    }";

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("http://localhost:3000/posts")
                .then().log().all();
    }

    @Test
    public void addPostFromFile(){
        File newPost = new File("src/test/resources/post.json");

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("http://localhost:3000/posts")
                .then().log().all();
    }

    @Test
    public void addPostMap(){
        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title", "tytuł z mapy");
        newPost.put("author", "Rafalll");

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("http://localhost:3000/posts")
                .then().log().all();
    }

    @Test
    public void addPostObject(){
        Post newPost = new Post();
        newPost.setTitle("Tytul obiektowy");
        newPost.setAuthor("Autor obiektowy");

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("http://localhost:3000/posts")
                .then().log().all();
    }
}
