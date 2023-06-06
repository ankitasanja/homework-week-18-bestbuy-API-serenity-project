package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductTagsTest extends TestBase {

    @WithTags({@WithTag("productfeature:SMOKE"),
            @WithTag("productfeature:POSITIVE")})
    @Title("This test will verify if a status code of 200 is returned for GET request")
    @Test
    public void verifyIfTheStatusCodeIs200() {
        SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then()
                .statusCode(200)
                .log().all();
    }

    @WithTag("productfeature:NEGATIVE")

    @Title("Provide a 405 status code when incorrect HTTP method is used to access resource")
    @Test
    public void invalidMethod() {
        SerenityRest.rest()
                .given()
                .when()
                .post(EndPoints.GET_ALL_PRODUCTS)
                .then()
                .statusCode(500)
                .log().all();
    }

    @WithTags({@WithTag("productfeature:SMOKE"),
            @WithTag("productfeature:NEGATIVE")})
    @Title("This test will provide an error code of 400 when user tries to access an invalid resource")
    @Test
    public void inCorrectResource() {
        SerenityRest.rest()
                .given()
                .when()
                .get("/products123")
                .then()
                .statusCode(500)
                .log().all();
    }

}
