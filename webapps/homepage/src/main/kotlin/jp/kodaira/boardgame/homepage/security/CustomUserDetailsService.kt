package jp.kodaira.boardgame.homepage.security

import jp.kodaira.boardgame.homepage.domain.Person
import jp.kodaira.boardgame.homepage.service.PersonService
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

/**
 * Spring Securityでユーザー認証時にユーザーを取得するservice
 */
@Component
class CustomUserDetailsService(private val personService: PersonService)
    : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        val logger = LoggerFactory.getLogger(CustomUserDetailsService::class.java)
        logger.info("start loadUserByUsername")

        var person: Person?
        try {
            person = personService.findByName(username)
        } catch (e: Exception) {
            logger.error("Exception", e)
            // 取得時にExceptionが発生した場合
            throw UsernameNotFoundException("It can not be acquired Person")
        }
        if (person == null) {
            logger.info("person is not found")
            throw UsernameNotFoundException("Person not found for login name: " + username)
        }

        val userDetails = CustomUserDetails(person)

        logger.info("end loadUserByUsername. person:${userDetails.person}")
        return userDetails
    }
}
