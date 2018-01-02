package jp.kodaira.boardgame.homepage.service

import jp.kodaira.boardgame.homepage.domain.Person
import jp.kodaira.boardgame.homepage.mapper.PersonMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PersonService(private val personMapper: PersonMapper) {
    /**
     * personを追加する。
     * 追加したpersonを返す。(idセット済み)
     */
    fun addPerson(name: String): Person {
        val newPerson = Person(name = name)
        personMapper.insert(newPerson)
        return newPerson
    }

    /**
     * personを1000個まで取得する
     */
    fun getPersonListLimitThousand() =
            personMapper.selectThousand()
}