package jp.kodaira.boardgame.homepage.service


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension


@SpringBootTest
@ExtendWith(SpringExtension::class)
class PersonServiceTest(@Autowired private var personService: PersonService) {

    @Test
    fun insertAndSelectTest() {
        println("start insertAndSelectTest")

        val name = "testPerson"
        val person = personService.addPerson(name, "test")
        assertEquals(name, person.name, "insert結果のテスト")

        val personList = personService.getPersonListLimitThousand()
        assertFalse(personList.isEmpty(), "空でないことをチェック")
        val find = personList.find { it.name == name }
        assertEquals(name, find?.name, "insertしたデータをselect出来ているかチェック")

        println("end insertAndSelectTest")
    }

}