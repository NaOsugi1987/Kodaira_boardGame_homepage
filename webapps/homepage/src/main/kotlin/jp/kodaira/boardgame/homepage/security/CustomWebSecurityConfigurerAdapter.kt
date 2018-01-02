package jp.kodaira.boardgame.homepage.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class CustomWebSecurityConfigurerAdapter : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        // private以下のみ認証を要求しておく
        http.authorizeRequests()
                .mvcMatchers("/private/**").authenticated()
                .anyRequest().permitAll()

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