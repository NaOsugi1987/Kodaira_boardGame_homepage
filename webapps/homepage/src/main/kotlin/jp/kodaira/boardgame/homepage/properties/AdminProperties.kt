package jp.kodaira.boardgame.homepage.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jp.kodaira.boardgame.admin")
class AdminProperties(
        var name: String = "",
        var pass: String = ""
)