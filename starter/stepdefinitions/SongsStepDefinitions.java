package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class SongsStepDefinitions {

    private static final String BASE_URL = "https://shazam.p.rapidapi.com";
    private String endpoint;
    private final String API_KEY = "3459daf20dmsh8ca466a9f8cc84ap190ba4jsn4a9abc9a830e";
    private Response response;

    @Given("el usuario realiza una petición GET al endpoint {string}")
    public void elUsuarioRealizaUnaPeticionGET(String endpoint) {
        response = SerenityRest.given()
                .header("X-Rapidapi-Key", API_KEY)
                .header("X-Rapidapi-Host", "shazam.p.rapidapi.com")
                .when()
                .get(BASE_URL + endpoint);
    }

    @Given("el usuario realiza una petición POST al endpoint {string} con un payload válido")
    public void elUsuarioRealizaUnaPeticionPOST(String endpoint) {
        String payload = "{ \"data\": \"test_audio_data\" }"; // Simulación de payload válido

        response = SerenityRest.given()
                .header("X-Rapidapi-Key", API_KEY)
                .header("X-Rapidapi-Host", "shazam.p.rapidapi.com")
                .contentType("text/plain")
                .body(payload)
                .when()
                .post(BASE_URL + endpoint);
    }

    @When("la petición es exitosa")
    public void laPeticionEsExitosa() {
        response.then().statusCode(200);
    }

    @When("la petición es procesada correctamente")
    public void laPeticionEsProcesadaCorrectamente() {
        response.then().statusCode(204);
    }

    @Then("el código de respuesta es {int}")
    public void elCodigoDeRespuestaEs(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("el JSON de respuesta contiene el campo {string}")
    public void elJsonDeRespuestaContieneElCampo(String campo) {
        response.then().body(campo, notNullValue());
    }
}