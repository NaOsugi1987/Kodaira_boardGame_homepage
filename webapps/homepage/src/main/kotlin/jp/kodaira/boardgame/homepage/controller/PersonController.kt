package jp.kodaira.boardgame.homepage.controller

import jp.kodaira.boardgame.homepage.service.PersonService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.security.Principal

@Controller
class PersonController(private val personService: PersonService) {
    companion object {
        private val logger = LoggerFactory.getLogger(PersonController::class.java)
        const val ALL_URI = "/private/person/all"
        const val ADD_URI = "/private/person/add"
        const val ADD_URI_PARAM = "name"
        const val UPDATE_PASSWORD_URI = "/private/person/update/password"
        const val UPDATE_PASSWORD_URI_PARAM = "pass"

        const val PERSONS_VIEW_MODEL_KEY = "persons"
        const val PERSONS_VIEW_NAME = "persons"

        const val PERSON_VIEW_MODEL_KEY_ID = "id"
        const val PERSON_VIEW_MODEL_KEY_NAME = "name"
        const val PERSON_VIEW_NAME = "person"
    }

    /**
     * ユーザー一覧を表示する
     */
    @GetMapping(ALL_URI)
    fun all(model: Model): String {
        logger.info("persons.")
        val personList = personService.getPersonListLimitThousand()
        logger.info("size:${personList.size}")
        model.addAttribute(PERSONS_VIEW_MODEL_KEY, personList)
        return PERSONS_VIEW_NAME
    }

    /**
     * ユーザーを追加する
     */
    @GetMapping("/private/person/add")
    fun add(model: Model,
            @RequestParam(name = ADD_URI_PARAM,
                    required = true) name: String): String {
        val newPerson = personService.addPerson(name, "test")
        logger.info("addPerson. newPerson:$newPerson")
        model.addAttribute(PERSON_VIEW_MODEL_KEY_ID, newPerson.id)
        model.addAttribute(PERSON_VIEW_MODEL_KEY_NAME, newPerson.name)
        return PERSON_VIEW_NAME
    }

    /**
     * 自分自身のpasswordを更新する
     */
    @GetMapping(UPDATE_PASSWORD_URI)
    fun updatePassword(model: Model,
                       principal: Principal,
                       @RequestParam(name = UPDATE_PASSWORD_URI_PARAM,
                               required = true) pass: String): String {
        logger.info("updatePassword.")
        personService.updatePerson(principal.name, pass)
        model.addAttribute(PERSON_VIEW_MODEL_KEY_ID, 0)
        model.addAttribute(PERSON_VIEW_MODEL_KEY_NAME, principal.name)
        return PERSON_VIEW_NAME
    }
}