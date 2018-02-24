package jp.kodaira.boardgame.homepage.security

import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
class WebSecurityConfiguration {

    @Configuration
    class CustomWebSecurityConfigurerAdapter : WebSecurityConfigurerAdapter() {
        companion object {
            private const val PROTECTED_PATH = "/private"
        }

        override fun configure(http: HttpSecurity) {
            http
                    // private以下のみ認証を要求しておく
                    .authorizeRequests()
                    .mvcMatchers("$PROTECTED_PATH/**").authenticated()
                    .anyRequest().permitAll()
                    // ログイン設定
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/manage/form_login")
                    .loginPage("/login.html")
                    .failureHandler(CustomAuthenticationFailureHandler())
                    .defaultSuccessUrl("/index.html")
                    .usernameParameter("name")
                    .passwordParameter("encrypted_password")
        }
    }

    /**
     * h2dbのコンソール用の設定
     * コンソールURLのみframeOptionsの設定を外すため、独立させる
     * https://stackoverflow.com/questions/42257402/disable-x-frameoptions-response-header-for-a-url-spring-security-java-config
     */
    @Configuration
    @Order(1)
    @EnableConfigurationProperties(H2ConsoleProperties::class)
    class H2ConsoleWebSecurityConfigurerAdapter(private val h2ConsoleProperties: H2ConsoleProperties)
        : CustomWebSecurityConfigurerAdapter() {
        override fun configure(http: HttpSecurity) {
            // セキュリティー設定を継承しておく
            super.configure(http)
            // h2dbコンソールはcsrfとFrame-Optionsヘッダーを無効にする
            val consolePath = "${h2ConsoleProperties.path}/**"
            http.csrf()
                    .ignoringAntMatchers(consolePath)
                    .and()
                    .antMatcher(consolePath)
                    .headers().frameOptions().disable()
                    // このフィルターはconsolePathのみに適用されるので、
                    // consolePath以下全てのパスを認証必須にする
                    .and()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
        }
    }
}