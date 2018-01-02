package jp.kodaira.boardgame.homepage.security

import org.slf4j.LoggerFactory
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
　ログイン失敗時のハンドラー
 */
class CustomAuthenticationFailureHandler : AuthenticationFailureHandler {
    companion object {
        private val logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler::class.java)
    }

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationFailure(request: HttpServletRequest?,
                                         response: HttpServletResponse?,
                                         exception: org.springframework.security.core.AuthenticationException?) {
        // ログ出力してログイン画面にリダイレクト
        logger.info("onAuthenticationFailure", exception)
        request?.run {
            response?.sendRedirect(request.contextPath + "/login.html?error")
        }
    }
}