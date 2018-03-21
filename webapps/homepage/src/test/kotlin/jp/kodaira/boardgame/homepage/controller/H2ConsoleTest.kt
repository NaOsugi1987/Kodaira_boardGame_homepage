package jp.kodaira.boardgame.homepage.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
@EnableConfigurationProperties(H2ConsoleProperties::class)
class H2ConsoleTest(@Autowired private val mvc: MockMvc,
                    @Autowired private val h2ConsoleProperties: H2ConsoleProperties) {

    @Test
    fun unauthenticatedTestRoot() {
        mvc.perform(get(h2ConsoleProperties.path))
                // 認証されていない
                .andExpect(unauthenticated())
                // ログインページにリダイレクトされる
                .andExpect(status().isFound)
    }

    @Test
    fun unauthenticatedTestLogin() {
        mvc.perform(get(h2ConsoleProperties.path + "/login.jsp"))
                // 認証されていない
                .andExpect(unauthenticated())
                // ログインページにリダイレクトされる
                .andExpect(status().isFound)
    }
}