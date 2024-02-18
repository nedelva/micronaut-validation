package org.acme.validation;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;

//import io.quarkus.test.junit.QuarkusTest;

//@QuarkusTest
@MicronautTest
public class BookResourceTest {

    @Test
    public void testHelloEndpoint(RequestSpecification spec) {
        spec
                .when().get("/books")
                .then()
                .statusCode(200)
                .body(is("hello"));
    }

    @Test
    public void testValidBook(RequestSpecification spec) {
        spec
                .body("{\"title\": \"some book\", \"author\": \"me\", \"pages\":5}")
                .header("Content-Type", "application/json")
                .when()
                .post("/books/manual-validation")
                .then()
                .statusCode(200)
                .body("success", is(true), "message", containsString("Book is valid!"));
    }

    @Test
    public void testBookWithoutTitle(RequestSpecification spec) {
        spec
                .body("{\"author\": \"me\", \"pages\":5}")
                .header("Content-Type", "application/json")
                .when()
                .post("/books/manual-validation")
                .then()
                .statusCode(200)
                .body("success", is(false), "message", containsString("Title"));
    }

    @Test
    public void testBookWithoutAuthor(RequestSpecification spec) {
        spec
                .body("{\"title\": \"catchy\", \"pages\":5}")
                .header("Content-Type", "application/json")
                .when()
                .post("/books/manual-validation")
                .then()
                .statusCode(200)
                .body("success", is(false), "message", containsString("Author"));
    }

    @Test
    public void testBookWithNegativePage(RequestSpecification spec) {
        spec
                .body("{\"title\": \"some book\", \"author\": \"me\", \"pages\":-25}")
                .header("Content-Type", "application/json")
                .when()
                .post("/books/manual-validation")
                .then()
                .statusCode(200)
                .body("success", is(false), "message", containsString("lazy"));
    }

    @Test
    public void testValidBookEndPointValidation(RequestSpecification spec) {
        spec
                .body("{\"title\": \"some book\", \"author\": \"me\", \"pages\":5}")
                .header("Content-Type", "application/json")
                .when()
                .post("/books/end-point-method-validation")
                .then()
                .statusCode(200)
                .body("success", is(true), "message", containsString("Book is valid!"));
    }

    @Test
    public void testBookWithoutTitleEndPointValidation(RequestSpecification spec) {
        spec
                .body("{\"author\": \"me\", \"pages\":5}")
                .header("Content-Type", "application/json")
                .when()
                .post("/books/end-point-method-validation")
                .prettyPeek()
                .then()
                .statusCode(400)
              //  .body("violations.message", hasItem("Title may not be blank"));
                .body("_embedded.errors.message", hasItem("book.title: Title cannot be blank"));
    }

    @Test
    public void testValidBookServiceValidation(RequestSpecification spec) {
        spec
                .body("{\"title\": \"some book\", \"author\": \"me\", \"pages\":5}")
                .header("Content-Type", "application/json")
                .when()
                .post("/books/service-method-validation")
                .then()
                .statusCode(200)
                .body("success", is(true), "message", containsString("Book is valid!"));
    }

    @Test
    public void testBookWithoutTitleServiceValidation(RequestSpecification spec) {
        spec
                .body("{\"author\": \"me\", \"pages\":5}")
                .header("Content-Type", "application/json")
                .when()
                .post("/books/service-method-validation")
                .then()
                .body("success", is(false), "message", containsString("Title"));
    }
}