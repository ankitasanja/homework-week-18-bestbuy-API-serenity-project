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
public class StoresCRUDTestWithSteps extends TestBase {

    static String name = "PrimUser" + TestUtils.getRandomValue();
    static String type = "BigBox" + TestUtils.getRandomValue();
    static String address = TestUtils.getRandomValue() +"Random Street" ;
    static String address2 = "Roaming Street";
    static String city =  "London" ;
    static String state = "England";
    static String zip = "234567";
    static double lat = 45.65874;
    static double lng = -95.56235;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId ;

    @Steps
    StoresSteps storesSteps;

    @Title("This will create a new store")
    @Test
    public void test001() {
        ValidatableResponse response = storesSteps.createANewStore(name,type,address,address2,city,state,zip,lat,lng,hours,storeId);
        response.log().all().statusCode(201);
        storeId = response.log().all().extract().path("id");
        System.out.println(storeId);

    }

    @Title("Verify if the store was added to the application ")
    @Test
    public void test002() {
        HashMap<String, Object> storeMap = storesSteps.getStoreInfoById(storeId);
        Assert.assertThat(storeMap, hasValue(storeId));
        System.out.println(storeMap);

    }

    @Title("Update the store information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        storesSteps.updatingStores(storeId,name,type,address,address2,city,state,zip,lat,lng,hours).statusCode(200);
        HashMap<String, Object> storeMap = storesSteps.getStoreInfoById(storeId);
        Assert.assertThat(storeMap, hasValue(storeId));
    }
    @Title("Deleting store information and verify if the product is deleted")
    @Test
    public void test004() {
        storesSteps.deleteAStore(storeId)
                .statusCode(200);
    }

}
