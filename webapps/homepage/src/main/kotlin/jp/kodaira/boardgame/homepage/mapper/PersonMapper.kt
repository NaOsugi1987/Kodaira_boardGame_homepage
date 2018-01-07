package jp.kodaira.boardgame.homepage.mapper

import jp.kodaira.boardgame.homepage.domain.Person
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select

@Mapper
interface PersonMapper {
    @Insert("""
        INSERT INTO person
            (name, encrypted_password)
        VALUES
            (#{name}, #{encryptedPassword})
    """)
    @Options(useGeneratedKeys = true)
    fun insert(person: Person)

    /**
     * idからpersonを探す。通常はこちらを使う
     */
    @Select("""
        SELECT
            id, name
        FROM
            person
        WHERE
            id = #{id}
    """)
    fun select(id: Int): Person

    /**
     * 名前からPersonを探す。これはログイン時のみ使う(パスワードも持ってくるので通常は使わない)
     */
    @Select("""
        SELECT
            *
        FROM
            person
        WHERE
            name = #{name}
    """)
    fun selectByName(name: String): Person

    /**
     * personを1000個取得する(1000を超えて必要ならその時考える)
     */
    @Select("""
        SELECT
            *
        FROM
            person
        LIMIT
            1000
    """)
    fun selectThousand(): List<Person>
}