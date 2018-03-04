package jp.kodaira.boardgame.homepage.client

import com.fasterxml.jackson.databind.ObjectMapper
import jp.kodaira.boardgame.homepage.domain.GoogleCalendarModel
import jp.kodaira.boardgame.homepage.properties.GoogleCalendarProperties
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.method
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.util.UriComponentsBuilder
import java.io.FileReader


@SpringBootTest
@ExtendWith(SpringExtension::class)
@RestClientTest(GoogleCalendarClient::class)
@EnableConfigurationProperties(GoogleCalendarProperties::class)
class GoogleCalendarClientTest(
        @Autowired private val mockServer: MockRestServiceServer,
        @Autowired private val client: GoogleCalendarClient,
        @Autowired private val googleCalendarProperties: GoogleCalendarProperties,
        @Autowired private val objectMapper: ObjectMapper) {

    @Test
    fun getCalendarTest() {
        val uri = UriComponentsBuilder
                .fromHttpUrl(googleCalendarProperties.calendarUrl)
                .queryParam("key", googleCalendarProperties.apiKey)
                .build().toUri()
        val json = FileReader(ClassPathResource("google_calendar_test.json").file)
                .readText()

        mockServer
                .expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON))

        val expected = objectMapper.readValue(
                json,
                GoogleCalendarModel::class.java)

        val actual = client.getGoogleCalendar()

        Assertions.assertEquals(expected, actual)
    }
}