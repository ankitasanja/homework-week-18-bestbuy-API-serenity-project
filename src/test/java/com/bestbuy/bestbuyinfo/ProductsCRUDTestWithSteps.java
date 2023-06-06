package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTestWithSteps extends TestBase {

    static String name = "Duracell - AAA Batteries (4-Pack)" + TestUtils.getRandomValue();
    static String type = "HardGood" + TestUtils.getRandomValue();
    static Double price = 5.49;
    static String upc = "041333424019";
    static Double shipping = 0.0;
    static String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
    static String manufacturer = "Duracell";
    static String model = "MN2400B4Z";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static int productId;

    @Steps
    ProductSteps productSteps;

    @Title("This will create a new product")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createANewProduct(name, type, price, upc, shipping, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productId = response.log().all().extract().path("id");
        System.out.println(productId);

    }

    @Title("Verify if the product was added to the application ")
    @Test
    public void test002() {
        HashMap<String, Object> productMap = productSteps.getProductInfoById(productId);
        Assert.assertThat(productMap, hasValue(productId));
        System.out.println(productMap);

    }

    @Title("Update the product information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        productSteps.updatingProduct(productId, name, type, price, upc, shipping, description, manufacturer, model, url, image).statusCode(200);
        HashMap<String, Object> productMap = productSteps.getProductInfoById(productId);
        Assert.assertThat(productMap, hasValue(productId));
    }

    @Title("Deleting product information and verify if the product is deleted")
    @Test
    public void test004() {
        productSteps.deleteAProduct(productId)
                .statusCode(200);

    }
}
