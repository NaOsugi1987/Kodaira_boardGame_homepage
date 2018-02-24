package jp.kodaira.boardgame.homepage.controller

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
@Nested
class PersonControllerTest(@Autowired private val mvc: MockMvc) {
    companion object {
        private const val ADD_USER_NAME = "test"
    }

    @Test
    fun unauthenticatedTestAll() {
        mvc.perform(get(PersonController.ALL_URI))
                // 認証されていない
                .andExpect(unauthenticated())
                // ログインページにリダイレクトされる
                .andExpect(status().isFound)
    }

    @Test
    fun unauthenticatedTestAdd() {
        mvc.perform(get(PersonController.ADD_URI))
                // 認証されていない
                .andExpect(unauthenticated())
                // ログインページにリダイレクトされる
                .andExpect(status().isFound)
    }

    @Test
    fun unauthenticatedTestUpdatePassword() {
        mvc.perform(get(PersonController.UPDATE_PASSWORD_URI))
                // 認証されていない
                .andExpect(unauthenticated())
                // ログインページにリダイレクトされる
                .andExpect(status().isFound)
    }

    @Test
    @WithMockUser
    fun allTest() {
        val result = mvc.perform(get(PersonController.ALL_URI))
                // 認証されている
                .andExpect(authenticated())
                // 200が返る
                .andExpect(status().isOk)
                .andReturn()

        val viewModel = result.modelAndView
        val modelMap = viewModel?.modelMap
        assertEquals("test view model",
                true,
                modelMap?.contains(PersonController.PERSONS_VIEW_MODEL_KEY))
        assertEquals("test view name",
                PersonController.PERSONS_VIEW_NAME,
                viewModel?.viewName)
    }

    @Test
    @WithMockUser
    fun add() {
        val result = mvc.perform(get(PersonController.ADD_URI)
                .param(PersonController.ADD_URI_PARAM, ADD_USER_NAME))
                // 認証されている
                .andExpect(authenticated())
                // 200が返る
                .andExpect(status().isOk)
                .andReturn()

        val viewModel = result.modelAndView
        val modelMap = viewModel?.modelMap
        assertEquals("test view model:id",
                true, modelMap?.contains(PersonController.PERSON_VIEW_MODEL_KEY_ID))
        assertEquals("test view model:name",
                ADD_USER_NAME, modelMap?.get(PersonController.PERSON_VIEW_MODEL_KEY_NAME))
        assertEquals("test view name",
                PersonController.PERSON_VIEW_NAME, viewModel?.viewName)
    }

    @Nested
    inner class UpdateUser {
        @Test
        @WithMockUser(username = ADD_USER_NAME)
        fun updatePassword() {
            val result = mvc.perform(get(PersonController.UPDATE_PASSWORD_URI)
                    .param(PersonController.UPDATE_PASSWORD_URI_PARAM, "update_pass"))
                    // 認証されている
                    .andExpect(authenticated())
                    // 200が返る
                    .andExpect(status().isOk)
                    .andReturn()

            val viewModel = result.modelAndView
            val modelMap = viewModel?.modelMap
            assertEquals("test view model:id",
                    true, modelMap?.contains(PersonController.PERSON_VIEW_MODEL_KEY_ID))
            assertEquals("test view model:name",
                    ADD_USER_NAME, modelMap?.get(PersonController.PERSON_VIEW_MODEL_KEY_NAME))
            assertEquals("test view name",
                    PersonController.PERSON_VIEW_NAME, viewModel?.viewName)
        }
    }

}