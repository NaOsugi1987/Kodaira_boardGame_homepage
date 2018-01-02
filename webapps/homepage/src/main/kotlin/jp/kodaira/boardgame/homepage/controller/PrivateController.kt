package jp.kodaira.boardgame.homepage.controller

import jp.kodaira.boardgame.homepage.service.PersonService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class PrivateController(private val personService: PersonService) {
    companion object {
        private val logger = LoggerFactory.getLogger(PrivateController::class.java)
    }

    @Transactional
    @GetMapping("/private/add_person")
    fun addPerson(model: Model,
                  @RequestParam("name") name: String = ""): String {
        val newPerson = personService.addPerson(name)

        logger.info("name:$name,,newPerson:$newPerson")

        model.addAttribute("id", newPerson.id)
        model.addAttribute("name", newPerson.name)
        return "private"
    }
}
