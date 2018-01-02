package jp.kodaira.boardgame.homepage.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * パスワードをハッシュ化するEncoder
 */
@Component
class CustomPasswordEncoder : PasswordEncoder by BCryptPasswordEncoder()