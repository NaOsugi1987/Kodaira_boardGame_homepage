package jp.kodaira.boardgame.homepage.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "spring.h2.console")
data class H2ConsoleProperties(var path: String = "")