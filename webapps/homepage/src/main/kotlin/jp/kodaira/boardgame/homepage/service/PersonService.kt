package jp.kodaira.boardgame.homepage.service

import jp.kodaira.boardgame.homepage.domain.Person
import jp.kodaira.boardgame.homepage.mapper.PersonMapper
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PersonService(private val personMapper: PersonMapper,
                    private val passwordEncoder: PasswordEncoder) {

    /**
     * 名前からPersonを取得する
     */
    fun findByName(name: String): Person? =
            personMapper.selectByName(name)

    /**
     * personを追加する。
     * 追加したpersonを返す。(idセット済み)
     */
    fun addPerson(name: String, password: String): Person {
        val newPerson = Person(name = name,
                encryptedPassword = passwordEncoder.encode(password))
        personMapper.insert(newPerson)
        return newPerson
    }

    /**
     * personのpasswordを更新する。
     */
    fun updatePerson(name: String, password: String) {
        val person = Person(name = name,
                encryptedPassword = passwordEncoder.encode(password))
        personMapper.update(person)
    }

    /**
     * personを1000個まで取得する
     */
    fun getPersonListLimitThousand() =
            personMapper.selectThousand()
}