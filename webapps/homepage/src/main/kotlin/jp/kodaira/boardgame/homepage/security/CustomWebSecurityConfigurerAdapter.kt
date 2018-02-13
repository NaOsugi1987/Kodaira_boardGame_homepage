package jp.kodaira.boardgame.homepage.security

import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class CustomWebSecurityConfigurerAdapter(val h2ConsoleProperties: H2ConsoleProperties)
    : WebSecurityConfigurerAdapter() {

    companion object {
        private const val PROTECTED_PATH = "/private"
    }

    override fun configure(http: HttpSecurity) {
        // private以下のみ認証を要求しておく
        http.authorizeRequests()
                .mvcMatchers("$PROTECTED_PATH/**").authenticated()
                .anyRequest().permitAll()

        // h2dbコンソールはcsrfとFrame-Optionsヘッダーを無効にする
        http.csrf().ignoringAntMatchers("${h2ConsoleProperties.path}/**")
        http.antMatcher("${h2ConsoleProperties.path}/**").headers().frameOptions().disable()

        // ログイン設定
        http.formLogin()
                .loginProcessingUrl("/manage/form_login")
                .loginPage("/login.html")
                .failureHandler(CustomAuthenticationFailureHandler())
                .defaultSuccessUrl("/index.html")
                .usernameParameter("name")
                .passwordParameter("encrypted_password")
    }
}