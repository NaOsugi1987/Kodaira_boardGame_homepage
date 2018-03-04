package jp.kodaira.boardgame.homepage.service

import jp.kodaira.boardgame.homepage.domain.GoogleCalendarModel
import jp.kodaira.boardgame.homepage.domain.ScheduleModel
import jp.kodaira.boardgame.homepage.mapper.ScheduleMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ScheduleService(private val scheduleMapper: ScheduleMapper) {

    /**
     * GoogleCalendarModelをScheduleModelに変換してDBにupsertする
     */
    fun upsert(googleCalendarModel: GoogleCalendarModel?) =
            googleCalendarModel?.items?.map {
                val newScheduleModel = ScheduleModel(
                        startTime = it.start.dateTime,
                        endTime = it.end.dateTime,
                        summary = it.summary,
                        location = it.location)
                scheduleMapper.upsert(newScheduleModel)
                newScheduleModel
            } ?: listOf()

    /**
     * スケジュール情報をすべて取得する
     */
    fun selectAll() = scheduleMapper.selectAll()
}