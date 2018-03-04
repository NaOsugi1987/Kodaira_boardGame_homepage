package jp.kodaira.boardgame.homepage.service

import jp.kodaira.boardgame.homepage.client.GoogleCalendarClient
import jp.kodaira.boardgame.homepage.domain.ScheduleModel
import jp.kodaira.boardgame.homepage.mapper.ScheduleMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ScheduleService(
        private val googleCalendarClient: GoogleCalendarClient,
        private val scheduleMapper: ScheduleMapper) {
    companion object {
        private val logger = LoggerFactory.getLogger(ScheduleService::class.java)
    }

    /**
     * スケジュール情報を更新して更新後のスケジュール情報を返す
     */
    fun update(): List<ScheduleModel> {
        // Google Calendarから予定を取得する
        val googleCalendarModel = googleCalendarClient.getGoogleCalendar()
        if (googleCalendarModel == null) {
            logger.warn("googleCalendarModel is null")
            return selectAll()
        }
        // スケジュール情報に変換してDBに保存する
        googleCalendarModel.items.map {
            val newScheduleModel = ScheduleModel(
                    startTime = it.start.dateTime,
                    endTime = it.end.dateTime,
                    summary = it.summary,
                    location = it.location)
            scheduleMapper.upsert(newScheduleModel)
            newScheduleModel
        }
        // 更新したスケジュール情報を返す
        return selectAll()
    }

    /**
     * スケジュール情報をすべて取得する
     */
    fun selectAll() = scheduleMapper.selectAll()
}