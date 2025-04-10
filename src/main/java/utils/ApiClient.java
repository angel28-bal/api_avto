      package utils;

      import io.restassured.response.Response;

      import static io.restassured.RestAssured.given;

      public class ApiClient {

          private static final String BASE_URL = "https://petstore.swagger.io/v2";

          public static Response get(String endpoint) {
              return given().baseUri(BASE_URL).get(endpoint);
          }

          public static Response post(String endpoint, Object body) {
              return given().baseUri(BASE_URL).body(body).post(endpoint);
          }

          public static Response put(String endpoint, Object body) {
              return given().baseUri(BASE_URL).body(body).put(endpoint);
          }

          public static Response delete(String endpoint) {
              return given().baseUri(BASE_URL).delete(endpoint);
          }
      }