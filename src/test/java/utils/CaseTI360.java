package utils;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaseTI360 {


    @Before
    public void setUp() {
        RestAssured.baseURI = "https://ti360.herokuapp.com/";
    }

    @Test
    public void postStudents() {

        String json = "{\n" +
                "  \"address\": {\n" +
                "    \"city\": \"ATeste\",\n" +
                "    \"id\": 12,\n" +
                "    \"state\": \"striassaasng\",\n" +
                "    \"street\": \"3213213\"\n" +
                "  },\n" +
                "  \"id\": 21,\n" +
                "  \"name\": \"2131\",\n" +
                "  \"phone\": \"31313\"\n" +
                "}";

        Response response = RestAssured.given()
                .log().all()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("students")
                .thenReturn();

        assertEquals(201, response.statusCode());

    }

    @Test
    public void getStudents() {

        Response response = RestAssured.given()
                .log().all()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .when()
                .get("students")
                .thenReturn();
        System.out.println(response.prettyPrint());
        assertEquals(200, response.statusCode());

    }

    @Test
    public void getStudentsName() {

        Response response = RestAssured.given()
                .log().all()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .when()
                .get("/students/Jardo")
                .thenReturn();
        System.out.println(response.prettyPrint());

        assertEquals(200, response.jsonPath().get().toString());

    }

    @Test
    public void putStudents() {


        String json = "{\n" +
                "  \"address\": {\n" +
               // "    \"city\": \"Macare\",\n" +
                "    \"state\": \"Para\",\n" +
                "    \"street\": \"Rua PUT TEST\"\n" +
                "  },\n" +
                "  \"name\": \"JOSE PUT\",\n" +
                "  \"phone\": \"1111111\"\n" +
                "}";

        Response response = RestAssured.given()
                .log().all()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .put("students/18")
                .thenReturn();


        assertEquals(200, response.statusCode());

    }

    @Test
    public void deleteStudents() {
        String id = "36";

        Response response = RestAssured.given()
                .log().all()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .when()
                .delete("students/"+id)
                .thenReturn();

        System.out.println(response.prettyPrint());
        assertEquals(204, response.statusCode());

    }
}

