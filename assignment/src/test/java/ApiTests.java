import extensions.RetryAnalyzer;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Timestamp;

public class ApiTests {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Test() throws IOException {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        PetBaseClass petBase = new PetBaseClass();
        StoreBaseClass storeBase = new StoreBaseClass();
        UserBaseClass userBase = new UserBaseClass();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long id = timestamp.getTime();
        String username = "testuser" + String.valueOf(id);

        petBase.createPet(id);
        petBase.checkPet(id);
        petBase.updatePet(id, "deniz", "name");
        petBase.checkPetUpdate(id, "deniz", "name");
        petBase.deletePet(id);
        petBase.validatePetDeletion(id);
        storeBase.createOrder(id);
        storeBase.checkOrder(id);
        storeBase.deleteOrder(id);
        storeBase.validateOrderDeletion(id);
        userBase.createUser(id);
        userBase.checkUser(id);
        userBase.loginUser(username, "Asdf1234");
        userBase.updateUser(id, "kübra", "firstName");
        userBase.checkUserUpdate(id,"kübra","firstName");
        userBase.deleteUser(username);
        userBase.validateUserDeletion(username);


    }
}
