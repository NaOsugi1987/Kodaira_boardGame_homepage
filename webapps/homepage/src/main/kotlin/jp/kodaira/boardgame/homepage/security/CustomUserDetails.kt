package jp.kodaira.boardgame.homepage.security

import jp.kodaira.boardgame.homepage.domain.Person
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User

/**
 * Spring Securityでユーザー認証で使うクラス
 */
class CustomUserDetails(person: Person)
    : User(person.name, person.encryptedPassword,
        AuthorityUtils.createAuthorityList("ROLE_USER")) {
    // 認証後はパスワードは必要ないので空にしておく(うっかりjson化してログ出力しちゃわないために空にする)
    val person = Person(id = person.id, name = person.name, encryptedPassword = "*hidden*")
}