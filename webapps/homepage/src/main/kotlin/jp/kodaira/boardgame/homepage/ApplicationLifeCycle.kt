package jp.kodaira.boardgame.homepage

import jp.kodaira.boardgame.homepage.properties.AdminProperties
import jp.kodaira.boardgame.homepage.service.PersonService
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class ApplicationLifeCycle(
        private val personService: PersonService,
        private val adminProperties: AdminProperties) {

    @PostConstruct
    fun init() {
        // 管理者ユーザーが追加されていなかったら追加する
        val adminUser = personService.findByName(adminProperties.name)
        if (adminUser == null) {
            personService.addPerson(adminProperties.name, adminProperties.pass)
        }
    }
}