package jp.kodaira.boardgame.homepage

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class HomepageApplicationTests {

	@Test
	fun contextLoads() {
        val test = "start"
        Assert.assertEquals("ok", "start", test)
	}

}
