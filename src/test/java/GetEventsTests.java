import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetEventsTests extends BassClass {

    private String eventsID = "/coopletest83@gmail.com/events/0jlvkhgliqlg113gk1kulqape7";
    private String ID = "0jlvkhgliqlg113gk1kulqape7";
    private String status = "Confirmed";
    private String htmlLink = "https://www.google.com/calendar/event?eid=MGpsdmtoZ2xpcWxnMTEzZ2sxa3VscWFwZTcgY29vcGxldGVzdDgzQG0";
    private String createdDate = "2019-02-16T12:48:28.000Z";
    private String modifiedDate = "2019-02-16T12:52:08.464Z";

    @Test
    public void testGetEvents()throws IOException, GeneralSecurityException {

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        String accessToken = BassClass.getCredentials(HTTP_TRANSPORT).getAccessToken();

        given().
                baseUri(calendarUrl).
                auth().oauth2(accessToken).
                when().get(eventsID).then()
                .log().all()
                .assertThat().statusCode(200).and().contentType(ContentType.JSON);
       given().
                baseUri(calendarUrl).
                auth().oauth2(accessToken).
                 when().get(eventsID).then()
                .assertThat().body("kind", equalTo("calendar#event"));

        given().
                baseUri(calendarUrl).
                auth().oauth2(accessToken).
                when().get(eventsID).then()
                .assertThat().body("id", equalTo("0jlvkhgliqlg113gk1kulqape7"));

        given().
                baseUri(calendarUrl).
                auth().oauth2(accessToken).
                when().get(eventsID).then()
                .assertThat().body("status", equalTo(status));

        given().
                baseUri(calendarUrl).
                auth().oauth2(accessToken).
                when().get(eventsID).then()
                .assertThat().body("htmlLink", equalTo(htmlLink));

        given().
                baseUri(calendarUrl).
                auth().oauth2(accessToken).
                when().get(eventsID).then()
                .assertThat().body("created", equalTo(createdDate));

        given().
                baseUri(calendarUrl).
                auth().oauth2(accessToken).
                when().get(eventsID).then()
                .assertThat().body("updated", equalTo(modifiedDate));

        given().
                baseUri(calendarUrl).
                auth().oauth2(accessToken).
                when().get(eventsID).then()
                .assertThat().body("summary", equalTo("Home Time"));

    }

}
