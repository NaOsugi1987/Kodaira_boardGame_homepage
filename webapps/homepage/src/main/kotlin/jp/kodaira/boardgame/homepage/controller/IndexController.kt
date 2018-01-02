package jp.kodaira.boardgame.homepage.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {
    @GetMapping("/index")
    fun index() = "index"
}