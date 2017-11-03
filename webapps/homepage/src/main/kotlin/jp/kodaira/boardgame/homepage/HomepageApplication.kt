package jp.kodaira.boardgame.homepage

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class HomepageApplication

fun main(args: Array<String>) {
    SpringApplication.run(HomepageApplication::class.java, *args)
}
