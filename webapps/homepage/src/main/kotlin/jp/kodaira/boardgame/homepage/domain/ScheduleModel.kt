package jp.kodaira.boardgame.homepage.domain

import java.time.OffsetDateTime

/**
 * scheduleモデル
 */
data class ScheduleModel(
        val startTime: OffsetDateTime = OffsetDateTime.now(),
        val endTime: OffsetDateTime = OffsetDateTime.now(),
        val summary: String = "",
        val location: String = "")