package jp.kodaira.boardgame.homepage.client

import jp.kodaira.boardgame.homepage.domain.GoogleCalendarModel
import jp.kodaira.boardgame.homepage.properties.GoogleCalendarProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class GoogleCalendarClient(private val googleCalendarProperties: GoogleCalendarProperties,
                           restTemplateBuilder: RestTemplateBuilder) {
    val restTemplate: RestTemplate = restTemplateBuilder.build()

    /**
     * Google Calendarから予定を取得して返す
     */
    fun getGoogleCalendar(): GoogleCalendarModel? {
        // urlを生成
        val urlBuilder = UriComponentsBuilder
                .fromHttpUrl(googleCalendarProperties.calendarUrl)
                .queryParam("key", googleCalendarProperties.apiKey)
                .build()
        // Google Calendarから予定を取得する
        return restTemplate.getForObject(urlBuilder.toUri(), GoogleCalendarModel::class.java)
    }
}