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
                .antMatchers("/private/**").authenticated()
                .anyRequest().permitAll()
    }
}