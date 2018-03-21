package jp.kodaira.boardgame.homepage.controller

import jp.kodaira.boardgame.homepage.domain.ScheduleModel
import jp.kodaira.boardgame.homepage.service.ScheduleService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ScheduleController(private val scheduleService: ScheduleService) {
    companion object {
        private val logger = LoggerFactory.getLogger(ScheduleController::class.java)
    }

    @GetMapping("/schedule/update")
    fun scheduleUpdate(): List<ScheduleModel> {
        logger.info("scheduleUpdate")
        return scheduleService.update()
    }

    @GetMapping("/schedule")
    fun schedule(): List<ScheduleModel> {
        logger.info("schedule")
        return scheduleService.selectAll()
    }
}