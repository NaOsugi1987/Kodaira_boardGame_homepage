package jp.kodaira.boardgame.homepage.controller

import jp.kodaira.boardgame.homepage.domain.GoogleCalendarModel
import jp.kodaira.boardgame.homepage.domain.ScheduleModel
import jp.kodaira.boardgame.homepage.properties.GoogleCalendarProperties
import jp.kodaira.boardgame.homepage.service.ScheduleService
import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestOperations
import org.springframework.web.util.UriComponentsBuilder

@RestController
class ScheduleController(private val scheduleService: ScheduleService,
                         private val googleCalendarProperties: GoogleCalendarProperties,
                         private val restTemplateBuilder: RestTemplateBuilder) {
    companion object {
        private val logger = LoggerFactory.getLogger(ScheduleController::class.java)
    }

    @Transactional
    @GetMapping("/schedule/update")
    fun scheduleUpdate(): List<ScheduleModel> {
        logger.info("scheduleUpdate")
        // urlを生成
        val urlBuilder = UriComponentsBuilder
                .fromHttpUrl(googleCalendarProperties.calendarUrl)
                .queryParam("key", googleCalendarProperties.apiKey)
                .build()
        // Google Calendarから予定を取得する
        val restOperations: RestOperations = restTemplateBuilder.build()
        val googleCalendarModel = restOperations.getForObject(urlBuilder.toUri(), GoogleCalendarModel::class.java)

        // スケジュール情報に変換してDBに保存する
        scheduleService.upsert(googleCalendarModel)
        return scheduleService.selectAll()
    }
}