package jp.kodaira.boardgame.homepage.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jp.kodaira.boardgame.google")
data class GoogleCalendarProperties(
        var calendarUrl: String = "",
        var apiKey: String = ""
)