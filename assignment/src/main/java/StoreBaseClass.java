import com.google.common.io.Resources;
import io.restassured.RestAssured;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import static io.restassured.RestAssured.given;

public class StoreBaseClass {
    public void createOrder(Long id) throws IOException {

        URL file = Resources.getResource("order.json");//jsonı okunabilir hale getiriyor
        String orderJson = Resources.toString(file, Charset.defaultCharset());//içidenkileri stringe ceviriyor.
        JSONObject json = new JSONObject(orderJson);// jsonobject metodları için cevirdik.

        json.put("id", id);
        json.put("petId", id);

        given() //belirli şartlar burada verilir
                .contentType("application/json")
                .body(json.toString())
                .when()
                .post("/store/order")
                .then()
                .statusCode(200);
    }
    public  void checkOrder(Long id) throws IOException {
        given()
                .contentType("application/json")
                .when()
                .get("/store/order/{orderId}", id)
                .then()
                .statusCode(200);

    }
    public  void deleteOrder(Long id) throws IOException {

        given()
                .contentType("application/json")
                .when()
                .delete("/store/order/{orderId}", id)
                .then()
                .statusCode(200);
    }

    public void getOrderThatDoesNotExist(long id) throws  IOException{
        given()
                .contentType("application/json; charset=UTF-8")
                .when()
                .get("/store/order/{orderId}", id)
                .then()
                .statusCode(404);
    }
    public void validateOrderDeletion(long id) throws  IOException{
        try{
            getOrderThatDoesNotExist(id);
        }
        catch (HttpResponseException ex)
        {
            assert ex.getStatusCode() == 404;
        }
    }



}
