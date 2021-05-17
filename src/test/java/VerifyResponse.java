import io.restassured.http.ContentType;
import model.Post;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerifyResponse {

    @Test
    public void getPost() {

        String expected = "{\n" +
                " \"author\": \"Roman\",\n" +
                " \"id\": 1,\n" +
                " \"title\": \"Nowy tytuł Romana\" \n" +
                "}";


        given().log().all()
                .when().get("http://localhost:3000/posts/{postId}", 1)
                .then().log().all().body(Matchers.equalTo(expected));
    }

    @Test
    public void getPostContains() {

        given().log().all()
                .when().get("http://localhost:3000/posts/{postId}", 1)
                .then().log().all().body(Matchers.containsStringIgnoringCase("Roman"));
    }

    @Test
    public void checkSpecificField() {

        given().log().all().when().get("http://localhost:3000/posts/{postId}", 1)
        .then().log().all().body("title", Matchers.equalTo("Nowy tytuł Romana"))
                .and().body("author", Matchers.equalTo("Roman"));
    }

    @Test
    public void checkPostObject() {

        Integer id = 1;

        Post newPost =
        given().log().all()

                .when()
                .get("http://localhost:3000/posts/{postId}", 1)

                .then().log().all()
                .body("title", Matchers.equalTo("Nowy tytuł Romana")).and()
                .body("author", Matchers.equalTo("Roman")).extract().body().as(Post.class);

        Assert.assertEquals(newPost.getAuthor(), "Roman");
        Assert.assertEquals(newPost.getTitle(), "Nowy tytuł Romana");
        Assert.assertEquals(newPost.getId(), id);

    }

    @Test
    public void addPostObject(){
        Post newPost = new Post();
        newPost.setTitle("Tytul obiektowy");
        newPost.setAuthor("Autor obiektowy");

        Post createdPost = given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("http://localhost:3000/posts")
                .then().log().all().extract().body().as(Post.class);

        Assert.assertEquals(newPost, createdPost);
    }
}
