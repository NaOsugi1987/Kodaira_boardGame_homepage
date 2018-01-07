package jp.kodaira.boardgame.homepage.controller

import jp.kodaira.boardgame.homepage.service.PersonService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PublicController(private val personService: PersonService) {
    companion object {
        private val logger = LoggerFactory.getLogger(PublicController::class.java)
    }

    @Transactional
    @GetMapping("/public")
    fun public(model: Model): String {
        val personList = personService.getPersonListLimitThousand()

        logger.info("size:${personList.size}")
        model.addAttribute("personList", personList)
        return "public"
    }
}