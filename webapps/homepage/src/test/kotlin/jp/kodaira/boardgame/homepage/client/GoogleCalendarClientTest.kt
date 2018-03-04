package jp.kodaira.boardgame.homepage.client

import jp.kodaira.boardgame.homepage.domain.GoogleCalendarModel
import jp.kodaira.boardgame.homepage.domain.GoogleCalendarModel.DateTime
import jp.kodaira.boardgame.homepage.domain.GoogleCalendarModel.Item
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
import java.time.OffsetDateTime
import java.time.ZoneOffset


@SpringBootTest
@ExtendWith(SpringExtension::class)
@RestClientTest(GoogleCalendarClient::class)
@EnableConfigurationProperties(GoogleCalendarProperties::class)
class GoogleCalendarClientTest(
        @Autowired private val mockServer: MockRestServiceServer,
        @Autowired private val client: GoogleCalendarClient,
        @Autowired private val googleCalendarProperties: GoogleCalendarProperties) {

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

        val expected = GoogleCalendarModel(items =
        listOf(Item(updated = OffsetDateTime.parse("2018-01-07T09:28:54.991Z"),
                summary = "みんなで遊ぼうドイツゲーム",
                location = "小平市中央公民館",
                start = DateTime(OffsetDateTime.parse("2018-01-27T13:30:00+09:00")
                        .withOffsetSameInstant(ZoneOffset.UTC)),
                end = DateTime(OffsetDateTime.parse("2018-01-27T15:30:00+09:00")
                        .withOffsetSameInstant(ZoneOffset.UTC)))))

        val actual = client.getGoogleCalendar()

        Assertions.assertEquals(expected, actual)
    }
}