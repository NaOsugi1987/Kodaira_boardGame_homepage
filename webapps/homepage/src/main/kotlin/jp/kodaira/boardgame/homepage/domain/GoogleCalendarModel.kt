package jp.kodaira.boardgame.homepage.domain

import java.time.OffsetDateTime

/**
 * Google Calendar APIのレスポンスモデル
 */
data class GoogleCalendarModel(val items: List<Item> = listOf()) {
    data class Item(
            val updated: OffsetDateTime = OffsetDateTime.now(),
            val summary: String = "",
            val location: String = "",
            val start: DateTime = DateTime(),
            val end: DateTime = DateTime())

    data class DateTime(
            val dateTime: OffsetDateTime = OffsetDateTime.now())
}