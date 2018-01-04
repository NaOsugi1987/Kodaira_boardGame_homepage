package jp.kodaira.boardgame.homepage.service

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class PersonServiceTest {

    /**
     * テストクラスはコンストラクターでDIできないのでlateinitを使う
     * https://stackoverflow.com/questions/39178817/getting-rid-of-lateinit-when-converting-spring-java8-junit-test-to-kotlin?rq=1
     */
    @Autowired
    private lateinit var personService: PersonService

    @Test
    fun insertAndSelectTest() {
        println("start insertAndSelectTest")

        val name = "testPerson"
        val person = personService.addPerson(name, "test")
        Assert.assertEquals("insert結果のテスト", name, person.name)

        val personList = personService.getPersonListLimitThousand()
        Assert.assertFalse("空でないことをチェック", personList.isEmpty())
        val find = personList.find { it.name == name }
        Assert.assertEquals("insertしたデータをselect出来ているかチェック", name, find?.name)

        println("end insertAndSelectTest")
    }

}